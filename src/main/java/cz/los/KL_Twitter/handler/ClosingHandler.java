package cz.los.KL_Twitter.handler;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.views.AbstractView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClosingHandler implements Handler {

    @Override
    public Response handle(Command command) {
        Session session = ContextHolder.getSecurityContext().getSession();
        AbstractView defaultView;
        if (session != null && session.isActive()) {
            defaultView = ContextHolder.getAppContext().getFeedView();
        } else {
            defaultView = ContextHolder.getAppContext().getWelcomeView();
        }
        log.warn("SAD! Could not handle the request.");
        return new Response(false, command, defaultView);
    }
}
