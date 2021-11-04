package cz.los.KL_Twitter.persistence.inMem;

import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.persistence.UserDao;
import cz.los.KL_Twitter.persistence.UserDoesNotExistException;
import cz.los.KL_Twitter.storage.Storage;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class UserDaoInMemImpl implements UserDao {

    private final Storage storage = Storage.getInstance();

    @Override
    public Long save(User user) {
        long newUserId = storage.getNewUserIdSequence();
        user.setUserId(newUserId);
        storage.getUserStorage().put(user.getUserId(), createUserState(user));
        log.info("User was persisted in the DB: {}", user);
        return newUserId;
    }

    @Override
    public Optional<User> findById(Long userId) {
        final User persistedUser = storage.getUserStorage().get(userId);
        if (persistedUser != null) {
            User resultUser = createUserState(persistedUser);
            return Optional.of(resultUser);
        }
        log.warn("Could not find user by id:{}", userId);
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Optional<User> persistedUser = storage.getUserStorage().values().stream()
                .filter(it -> it.getLogin().equals(login))
                .findFirst();
        if (!persistedUser.isPresent()) {
            log.warn("Could not find user by login:{}", login);
            return Optional.empty();
        }
        return Optional.of(createUserState(persistedUser.get()));
    }

    @Override
    public Set<User> getAll() {
        Set<User> userList = new HashSet<>();
        for (final User user : storage.getUserStorage().values()) {
            userList.add(createUserState(user));
        }
        log.debug("{} user were fetched from the DB.", userList.size());
        return userList;
    }

    @Override
    public void update(User user) {
        Optional<User> userOptional = findById(user.getUserId());
        if (userOptional.isPresent()) {
            storage.getUserStorage().put(user.getUserId(), createUserState(user));
        } else {
            log.error("Could not find user by id:{}", user.getUserId());
            throw new UserDoesNotExistException("Updated user does not exist:" + user);
        }
    }

    @Override
    public void deleteById(Long userId) {
        Map<Long, User> userStorage = storage.getUserStorage();
        if (userStorage.containsKey(userId)) {
            userStorage.remove(userId);
            log.info("User has been deleted. UserId:{}", userId);
        } else {
            log.warn("Could not find user by id:{}", userId);
        }
    }

    private User createUserState(User userOriginal) {
        return new User(userOriginal);
    }

}
