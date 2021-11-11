package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.service.AuthService;
import cz.los.KL_Twitter.views.FeedView;
import cz.los.KL_Twitter.views.WelcomeView;
import lombok.extern.slf4j.Slf4j;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class LoginHandler extends AbstractHandler {

    private final AuthService authService;

    public LoginHandler(AuthService authService) {
        super(Command.SIGN_IN);
        this.authService = authService;
    }

    @Override
    protected Response handleCommand() {
        Response response;
        String login = retrieveLogin();
        if (authService.authorize(login, retrievePassword())) {
            log.debug("{} has been authorized.", login);
            authService.startSession(login);
            FeedView feedView = ContextHolder.getAppContext().getFeedView();
            feedView.setHomeMode();
            response = new Response(true, Command.SIGN_IN, feedView);
        } else {
            log.warn("{} has not been authorized!", login);
            WelcomeView welcomeView = ContextHolder.getAppContext().getWelcomeView();
            welcomeView.setMessage("Incorrect login or password. Try again!");
            response = new Response(false, Command.SIGN_IN, welcomeView);
        }
        return response;
    }

    /*private String retrieveLogin() {
        String login = null;
        Scanner scanner = new Scanner(System.in);
        while (login == null) {
            System.out.println("Login:");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                log.warn("Could not parse input. Try again..");
                continue;
            }
            login = input;
        }
        return login;
    }*/

    private String retrieveLogin() {
        String login = null;
        Console console = System.console();
        while (login == null) {
            String input = console.readLine("Login: ");
            if (input.isEmpty()) {
                log.warn("Could not parse input. Try again..");
                continue;
            }
            login = input;
        }
        console.flush();
        return login;
    }

    /*private String retrievePassword() {
        String password = null;
        Scanner scanner = new Scanner(System.in);
        while (password == null) {
            System.out.println("Password:");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                log.warn("Could not parse input. Try again..");
                continue;
            }
            password = input;
        }
        return password;
    }*/

    private String retrievePassword() {
        char[] passwordChars = null;
        String password = null;
        Console console = System.console();
        while (passwordChars == null) {
            passwordChars = console.readPassword("Password: ");
            if (passwordChars.length == 0) {
                log.warn("Could not parse input.");
                continue;
            }
            password = new String(passwordChars);
            Arrays.fill(passwordChars, 's');
        }
        console.flush();
        return password;
    }
}
