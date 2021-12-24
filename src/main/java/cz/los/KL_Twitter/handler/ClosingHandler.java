package cz.los.KL_Twitter.handler;

import cz.los.KL_Twitter.app.SecurityContext;
import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.views.AbstractView;
import cz.los.KL_Twitter.views.FeedView;
import cz.los.KL_Twitter.views.WelcomeView;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Value
@Component
public class ClosingHandler implements Handler {

    SecurityContext securityContext;
    FeedView feedView;
    WelcomeView welcomeView;

    @Override
    public Response handle(Command command) {
        Session session = securityContext.getSession();
        AbstractView defaultView;
        if (session != null && session.isActive()) {
            defaultView = feedView;
        } else {
            defaultView = welcomeView;
        }
        log.warn("SAD! Could not handle the request.");
        return new Response(true, command, defaultView);
    }
}
