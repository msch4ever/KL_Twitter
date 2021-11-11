package cz.los.KL_Twitter.persistence.inMem;

import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.persistence.SessionDao;
import cz.los.KL_Twitter.storage.Storage;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class SessionDaoInMemImpl implements SessionDao {

    private final Storage storage = Storage.getInstance();

    @Override
    public Long save(Session session) {
        long newSessionIdSequence = storage.getNewSessionIdSequence();
        session.setId(newSessionIdSequence);
        session = createSessionState(session);
        storage.getSessionStorage().put(newSessionIdSequence, session);
        return newSessionIdSequence;
    }

    @Override
    public void updateEnd(Long id, LocalDateTime end) {
        storage.getSessionStorage().get(id).setEnd(end);
    }

    private Session createSessionState(Session session) {
        return new Session(session);
    }

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
}
