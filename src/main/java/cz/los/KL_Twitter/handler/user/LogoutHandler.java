package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.service.AuthService;

public class LogoutHandler extends AbstractHandler {

    private final AuthService authService;

    public LogoutHandler(AuthService authService) {
        super(Command.SIGN_OUT);
        this.authService = authService;
    }

    @Override
    public Response handleCommand() {
        authService.endSession(ContextHolder.getSecurityContext().getSession());
        return new Response(true, Command.SIGN_OUT, ContextHolder.getAppContext().getWelcomeView());
    }
}
