package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.service.AuthService;
import cz.los.KL_Twitter.views.WelcomeView;
import org.springframework.stereotype.Component;

@Component
public class LogoutHandler extends AbstractHandler {

    private final AuthService authService;
    private final WelcomeView welcomeView;

    public LogoutHandler(AuthService authService, WelcomeView welcomeView) {
        super(Command.SIGN_OUT);
        this.authService = authService;
        this.welcomeView = welcomeView;
    }

    @Override
    public Response handleCommand() {
        authService.endSession();
        return new Response(true, Command.SIGN_OUT, welcomeView);
    }
}
