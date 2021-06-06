package cz.los.KL_Twitter.persistence.impl;

import cz.los.KL_Twitter.db.Storage;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.persistence.UserDao;

import java.util.Set;

public class UserDaoInMemImpl implements UserDao {

    @Override
    public Long saveUser(User user) {
        return Storage.putUserToStorage(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        Storage.deleteUser(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return Storage.getUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        Storage.putUserToStorage(user);
    }

    @Override
    public Set<User> getAllUsers() {
        return Storage.getUsers();
    }
}
