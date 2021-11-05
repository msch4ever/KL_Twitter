package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.persistence.AuthDao;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthDao authDao;

    public AuthServiceImpl(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Override
    public Optional<UserAuthentication> getAuthByLogin(String login) {
        return authDao.findByLogin(login);
    }

    @Override
    public void create(final String login, final String password) {
        final byte[] salt = generateSalt();
        authDao.save(new UserAuthentication(login, salt, encodePassword(salt, password)));
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
