package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.auth.Session;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityContext {

    private Session session;

    public void initSession(Session session) {
        if (this.session != null) {
            throw new SecurityException("Could not set session. The session is still active!");
        }
        this.session = session;
        log.info("Session for user {} initiated.", session.getUserAuthentication().getLogin());
    }

    public void destroyCurrentSession() {
        log.info("Session for user {} destroyed.", session.getUserAuthentication().getLogin());
        this.session = null;
    }

}
