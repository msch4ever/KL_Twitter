package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.UserService;
import cz.los.KL_Twitter.views.WelcomeView;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class CreateUserHandler extends AbstractHandler {

    private final UserService userService;

    public CreateUserHandler(UserService userService) {
        super(Command.SIGN_UP);
        this.userService = userService;
    }

    @Override
    public Response handleCommand() {
        String login = null;
        String password = null;
        String input = null;
        Scanner scanner = new Scanner(System.in);
        while (login == null) {
            System.out.println("Login:");
            input = scanner.nextLine();
            if (input.isEmpty()) {
                log.warn("Could not parse input.");
                continue;
            }
            login = input;
        }
        log.info("Enter user's nickName...");

        while (password == null) {
            System.out.println("Password:");
            input = scanner.nextLine();
            if (input.isEmpty()) {
                log.warn("Could not parse input");
                continue;
            }
            password = input;
        }
        User user = userService.createUser(login, password);
        WelcomeView welcomeView = ContextHolder.getAppContext().getWelcomeView();
        welcomeView.setMessage("User " + user.getLogin() + " has been created.");
        return new Response(true, command, welcomeView);
    }
    /*@Override
    public Response handleCommand() {
        String login = null;
        String password = null;
        char[] passwordChars = null;
        String input = null;
        Console console = System.console();
        while (login == null) {
            input = console.readLine("Login: ");
            if (input == null) {
                log.warn("Could not parse input:[" + input + "]. Try again..");
                continue;
            }
            login = input;
        }

        while (passwordChars == null) {
            passwordChars = console.readPassword("Password: ");
            System.out.println(passwordChars);
            if (passwordChars.length == 0) {
                log.warn("Could not parse input.");
                continue;
            }
            password = new String(passwordChars);
            Arrays.fill(passwordChars, 's');
        }
        passwordChars = null;
        userService.createUser(login, password);
        console.flush();
        return new Response(true, command, AppContextHolder.getAppContext().getWelcomeView());
    }*/

}
