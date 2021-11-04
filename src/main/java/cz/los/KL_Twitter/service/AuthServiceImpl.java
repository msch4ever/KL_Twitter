package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.persistence.AuthDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthDao authDao;

    public AuthServiceImpl(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Override
    public UserAuthentication getAuthByLogin(String login) {
        return authDao.findByLogin(login);
    }

    @Override
    public boolean authorize(String login, String password) {
        UserAuthentication authentication = authDao.findByLogin(login);
        boolean authorized = authentication.equals(new UserAuthentication(login, encodePassword(password)));
        if (!authorized) {
            log.debug("Could not find provided combination of login and password. Login:{}", login);
        } else {
            log.debug("User authorized. Login:{}", login);
        }
        return authorized;
    }

    @Override
    public void create(String login, String password) {
        authDao.save(new UserAuthentication(login, encodePassword(password)));
        log.debug("Authentication info saved for login:{}", login);
    }
}
