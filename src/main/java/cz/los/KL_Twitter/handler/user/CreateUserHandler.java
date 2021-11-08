package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Handler;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class CreateUserHandler extends AbstractHandler {

    private final UserService userService;

    public CreateUserHandler(UserService userService) {
        super(Command.CREATE_USER);
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
