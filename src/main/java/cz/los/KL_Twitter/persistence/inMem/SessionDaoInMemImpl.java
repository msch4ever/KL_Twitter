package cz.los.KL_Twitter.persistence.inMem;

import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.persistence.SessionDao;
import cz.los.KL_Twitter.storage.Storage;

import java.time.LocalDateTime;

public class SessionDaoInMemImpl implements SessionDao {

    private final Storage storage = Storage.getInstance();

    @Override
    public Long save(Session session) {
        long newSessionIdSequence = storage.getNewSessionIdSequence();
        session = createSessionState(session);
        session.setSessionId(newSessionIdSequence);
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
}
