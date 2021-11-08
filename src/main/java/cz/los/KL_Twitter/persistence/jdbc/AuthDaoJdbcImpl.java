package cz.los.KL_Twitter.persistence.jdbc;

import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.persistence.AuthDao;

import java.util.Optional;
import java.util.Set;

public class AuthDaoJdbcImpl implements AuthDao {

    @Override
    public Long save(UserAuthentication authentication) {
        return null;
    }

    @Override
    public Optional<UserAuthentication> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public void delete(String login) {

    }

    @Override
    public void update(UserAuthentication authentication) {

    }

    @Override
    public Set<UserAuthentication> getAll() {
        return null;
    }

    @Override
    public Optional<UserAuthentication> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
