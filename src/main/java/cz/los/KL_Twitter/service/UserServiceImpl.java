package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.persistence.UserDao;

public class UserServiceImpl {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(String login, String nickName) {
        User user = new User(login, nickName);
        user.setId(userDao.save(user));
        return user;
    }
}
