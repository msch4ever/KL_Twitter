package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.model.User;

public interface UserDao extends GenericDao<User> {

    Long saveUser(User user);

    void deleteUserById(Long userId);

    User getUserById(Long userId);

    void updateUser(User user);

    Set<User> getAllUsers();
}
