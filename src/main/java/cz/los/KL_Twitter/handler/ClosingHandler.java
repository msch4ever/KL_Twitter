package cz.los.KL_Twitter.handler;

import cz.los.KL_Twitter.app.AppContextHolder;
import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.views.AbstractView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClosingHandler implements Handler {

    @Override
    public Response handle(Command command) {
        Session session = AppContextHolder.getSecurityContext().getSession();
        AbstractView defaultView;
        if (session != null && session.isActive()) {
            defaultView = AppContextHolder.getAppContext().getFeedView();
        } else {
            defaultView = AppContextHolder.getAppContext().getWelcomeView();
        }
        log.warn("SAD! Could not handle the request.");
        return new Response(false, command, defaultView);
    }
}
