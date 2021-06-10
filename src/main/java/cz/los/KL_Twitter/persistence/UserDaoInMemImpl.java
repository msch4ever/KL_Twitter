package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.storage.Storage;

import java.util.Optional;
import java.util.Set;

public class UserDaoInMemImpl implements UserDao {

    @Override
    public Long save(User model) {
        return Storage.putUserInStorage(model);
    }

    @Override
    public Set<User> getAll() {
        return Storage.getUsers();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(Storage.getUserByKey(id));
    }

    @Override
    public void updateById(Long id, User model) {
        model.setId(id);
        Storage.putUserInStorage(model);
    }

    @Override
    public void deleteById(Long id) {
        Storage.removeUserByKey(id);
    }
}
