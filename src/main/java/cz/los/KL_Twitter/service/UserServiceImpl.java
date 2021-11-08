package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.persistence.UserDao;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final AuthService authService;
    private final UserDao userDao;

    public UserServiceImpl(AuthService authService, UserDao userDao) {
        this.authService = authService;
        this.userDao = userDao;
    }

    @Override
    public User createUser(String login, String password) {
        User user = new User(login);
        user.setId(userDao.save(user));
        authService.createAuth(login, password);
        return user;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
