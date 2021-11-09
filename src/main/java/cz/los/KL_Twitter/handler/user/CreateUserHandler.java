package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.AppContext;
import cz.los.KL_Twitter.app.AppContextHolder;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class CreateUserHandler extends AbstractHandler {

    private final UserService userService;

    public CreateUserHandler(UserService userService) {
        super(Command.SIGN_UP);
        this.userService = userService;
    }

 /*   @Override
    public Response handleCommand() {
        log.info("Enter user's login...");
        String login = null;
        String password = null;
        String input = null;
        Scanner scanner = new Scanner(System.in);
        while (login == null) {
            input = scanner.nextLine();
            if (input == null) {
                log.warn("Could not parse input:[" + input + "].");
                continue;
            }
            login = input;
        }
        log.info("Enter user's nickName...");

        while (password == null) {
            input = scanner.nextLine();
            if (input == null) {
                log.warn("Could not parse input:[" + input + "].");
                continue;
            }
            password = input;
        }
        User user = userService.createUser(login, password);
        return new Response(true, command, AppContextHolder.getAppContext().getWelcomeView());
    }
*/
    @Override
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
            if (passwordChars == null || passwordChars.length == 0) {
                log.warn("Could not parse input.");
                continue;
            }
            System.out.println(passwordChars);
            password = new String(passwordChars);
            Arrays.fill(passwordChars, 's');
            passwordChars = null;
        }
        System.out.println(password);
        userService.createUser(login, password);
        return new Response(true, command, AppContextHolder.getAppContext().getWelcomeView());
    }

}
