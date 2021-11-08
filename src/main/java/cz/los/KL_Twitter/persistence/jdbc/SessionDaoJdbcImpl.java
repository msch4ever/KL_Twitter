package cz.los.KL_Twitter.persistence.jdbc;

import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.persistence.SessionDao;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class SessionDaoJdbcImpl implements SessionDao {

    @Override
    public Set<Session> getAll() {
        return null;
    }

    @Override
    public Optional<Session> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Session model) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Long save(Session session) {
        return null;
    }

    @Override
    public void updateEnd(Long id, LocalDateTime end) {

    }
}
