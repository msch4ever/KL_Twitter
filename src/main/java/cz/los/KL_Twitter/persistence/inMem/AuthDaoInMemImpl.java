package cz.los.KL_Twitter.persistence.inMem;

import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.persistence.AuthDao;
import cz.los.KL_Twitter.storage.Storage;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class AuthDaoInMemImpl implements AuthDao {

    private final Storage storage = Storage.getInstance();

    @Override
    public void save(UserAuthentication authentication) {
        storage.getAuthStorage().put(authentication.getLogin(), createAuthState(authentication));
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
}
