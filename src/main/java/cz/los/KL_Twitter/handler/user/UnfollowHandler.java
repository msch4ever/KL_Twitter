package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.SecurityContext;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.UserService;
import cz.los.KL_Twitter.views.ProfileView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnfollowHandler extends AbstractHandler {

    private final UserService userService;
    private final ProfileView profileView;
    private final SecurityContext securityContext;

    public UnfollowHandler(UserService userService, ProfileView profileView, SecurityContext securityContext) {
        super(Command.UNFOLLOW);
        this.userService = userService;
        this.profileView = profileView;
        this.securityContext = securityContext;
    }

    @Override
    public Response handleCommand() {
        User loggedInUser = securityContext.getSession().getLoggedInUser();
        userService.unfollow(loggedInUser.getUserId(), profileView.getUserId());
        return new Response(true, command, profileView);
    }

}
