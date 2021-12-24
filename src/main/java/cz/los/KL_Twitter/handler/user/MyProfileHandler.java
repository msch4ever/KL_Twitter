package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.views.ProfileView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyProfileHandler extends AbstractHandler {

    private final ProfileView profileView;

    public MyProfileHandler(ProfileView profileView) {
        super(Command.MY_PROFILE);
        this.profileView = profileView;
    }

    @Override
    public Response handleCommand() {
        profileView.setUserId(ContextHolder.getSecurityContext().getSession().getLoggedInUser().getUserId());
        return new Response(true, command, profileView);
    }

}
