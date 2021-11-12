package cz.los.KL_Twitter.persistence.inMem;

import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.persistence.AuthDao;
import cz.los.KL_Twitter.storage.Storage;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Set;

@Slf4j
public class AuthDaoInMemImpl implements AuthDao {

    private final Storage storage = Storage.getInstance();

    @Override
    public Long save(UserAuthentication authentication) {
        authentication.setId(storage.getAuthIdSequence());
        storage.getAuthStorage().put(authentication.getLogin(), createAuthState(authentication));
        return authentication.getId();
    }

    @Override
    public Optional<UserAuthentication> findByLogin(String login) {
        UserAuthentication authentication = storage.getAuthStorage().get(login);
        if (authentication == null) {
            return Optional.empty();
        }
        return Optional.of(createAuthState(authentication));
    }

    @Override
    public void delete(String login) {
        storage.getAuthStorage().remove(login);
    }

    @Override
    public void update(UserAuthentication authentication) {
        delete(authentication.getLogin());
        save(authentication);
    }

    private UserAuthentication createAuthState(UserAuthentication authentication) {
        return new UserAuthentication(authentication);
    }

    @Override
    public Set<UserAuthentication> getAll() {
        throw new UnsupportedOperationException("not supported");
    }

    @Override
    public Optional<UserAuthentication> findById(Long id) {
        return storage.getAuthStorage().values().stream().filter(it -> it.getId().equals(id)).findFirst();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("not supported");
    }
}
