package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.auth.Session;

import java.time.LocalDateTime;

public interface SessionDao extends GenericDao<Session> {

    Long save(Session session);

    void updateEnd(Long id, LocalDateTime end);

}
