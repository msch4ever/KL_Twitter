package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.model.User;

import java.util.Set;

public interface UserDao {

    Long saveUser(User user);

    void deleteUserById(Long userId);

    User getUserById(Long userId);

    void updateUser(User user);

    Set<User> getAllUsers();

}
