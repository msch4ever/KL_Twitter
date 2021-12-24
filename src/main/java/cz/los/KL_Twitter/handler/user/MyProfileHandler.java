package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.SecurityContext;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.views.ProfileView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyProfileHandler extends AbstractHandler {

    private final ProfileView profileView;
    private final SecurityContext securityContext;

    public MyProfileHandler(ProfileView profileView, SecurityContext securityContext) {
        super(Command.MY_PROFILE);
        this.profileView = profileView;
        this.securityContext = securityContext;
    }

    @Override
    public Response handleCommand() {
        profileView.setUserId(securityContext.getSession().getLoggedInUser().getUserId());
        return new Response(true, command, profileView);
    }

}
