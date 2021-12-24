package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.persistence.UserDao;

import java.util.List;
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
        authService.createAuth(user.getUserId(), login, password);
        return user;
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return userDao.findByNickname(nickname);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAllByIdInList(List<Long> ids) {
        return userDao.findAllByIdInList(ids);
    }

    @Override
    public int countFollowers(Long id) {
        return userDao.countFollowers(id);
    }

    @Override
    public int countFollowing(Long id) {
        return userDao.countFollowing(id);
    }

    @Override
    public boolean userIsFollowingOther(Long first, Long second) {
        return userDao.userIsFollowingOther(first, second);
    }

    @Override
    public void unfollow(Long first, Long second) {
        userDao.unfollow(first, second);
    }

    @Override
    public void follow(Long first, Long second) {
        userDao.follow(first, second);
    }
}
