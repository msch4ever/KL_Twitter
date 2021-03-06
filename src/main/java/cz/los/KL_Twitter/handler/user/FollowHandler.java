package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.UserService;
import cz.los.KL_Twitter.views.ProfileView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FollowHandler extends AbstractHandler {

    private final UserService userService;
    private final ProfileView profileView;

    public FollowHandler(UserService userService, ProfileView profileView) {
        super(Command.FOLLOW);
        this.userService = userService;
        this.profileView = profileView;
    }

    @Override
    public Response handleCommand() {
        User loggedInUser = ContextHolder.getSecurityContext().getSession().getLoggedInUser();
        userService.follow(loggedInUser.getUserId(), profileView.getUserId());
        return new Response(true, command, profileView);
    }

}
