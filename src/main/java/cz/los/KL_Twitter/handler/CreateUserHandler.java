package cz.los.KL_Twitter.handler;

import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class CreateUserHandler extends AbstractHandler {

    private static Logger log = LogManager.getLogger(CreateUserHandler.class);

    private final UserService userService;

    public CreateUserHandler(Handler nextHandler, UserService userService) {
        super(Command.CREATE_USER, nextHandler);
        this.userService = userService;
    }

    @Override
    public Response handleCommand() {
        log.info("Enter user's login...");
        String login = null;
        String nickName = null;
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

        while (nickName == null) {
            input = scanner.nextLine();
            if (input == null) {
                log.warn("Could not parse input:[" + input + "].");
                continue;
            }
            nickName = input;
        }
        User user = userService.createUser(login, nickName);
        return new Response(true, command, user.getNickname() + " has been created.");
    }

}
