package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.app.SecurityContext;
import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.persistence.AuthDao;
import cz.los.KL_Twitter.persistence.SessionDao;
import cz.los.KL_Twitter.persistence.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
//ToDo: write logs
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final SecurityContext securityContext;
    private final SessionDao sessionDao;
    private final UserDao userDao;
    private final AuthDao authDao;

    public AuthServiceImpl(SecurityContext securityContext, UserDao userDao, SessionDao sessionDao, AuthDao authDao) {
        this.securityContext = securityContext;
        this.sessionDao = sessionDao;
        this.userDao = userDao;
        this.authDao = authDao;
    }

    @Override
    public void startSession(String login) {
        User loggedInUser = userDao.findByLogin(login).orElseThrow(IllegalArgumentException::new);
        final Session session =
                new Session(loggedInUser, authDao.findByLogin(login).orElseThrow(SecurityException::new), LocalDateTime.now());
        sessionDao.save(session);
        securityContext.initSession(session);
    }

    @Override
    public void endSession() {
        LocalDateTime now = LocalDateTime.now();
        securityContext.getSession().setEnd(now);
        sessionDao.updateEnd(securityContext.getSession().getId(), now);
        securityContext.destroyCurrentSession();
    }

    @Override
    public Optional<UserAuthentication> getAuthByLogin(String login) {
        return authDao.findByLogin(login);
    }

    @Override
    public void createAuth(final Long userId, final String login, final String password) {
        final byte[] salt = generateSalt();
        authDao.save(new UserAuthentication(userId, login, salt, encodePassword(salt, password)));
        log.debug("Authentication info saved for login:{}", login);
    }

    @Override
    public boolean authorize(final String login, final String password) {
        boolean authorized = false;
        final Optional<UserAuthentication> authentication = authDao.findByLogin(login);
        if (authentication.isPresent()) {
            final byte[] salt = authentication.get().getSalt();
            final byte[] originalPassword = authentication.get().getSaltedPassword();
            final byte[] incomingPassword = encodePassword(salt, password);
            authorized = Arrays.equals(originalPassword, incomingPassword);
        }
        if (!authorized) {
            log.debug("Could not find provided combination of login and password. Login:{}", login);
        } else {
            log.debug("User authorized. Login:{}", login);
        }
        return authorized;
    }
}
