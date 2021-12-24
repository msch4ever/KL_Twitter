package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.SecurityContext;
import cz.los.KL_Twitter.handler.*;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.UserService;
import cz.los.KL_Twitter.views.EditProfileView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

@Slf4j
@Component
public class EditUserHandler extends AbstractHandler {

    private final SecurityContext securityContext;
    private final EditProfileView editProfileView;
    private final UserService userService;
    private final DispatcherHandler dispatcherHandler;

    public EditUserHandler(SecurityContext securityContext, EditProfileView editProfileView, UserService userService, DispatcherHandler dispatcherHandler) {
        super(Command.EDIT);
        this.securityContext = securityContext;
        this.editProfileView = editProfileView;
        this.userService = userService;
        this.dispatcherHandler = dispatcherHandler;
    }

    @Override
    protected Response handleCommand() {
        User currentUser = securityContext.getSession().getLoggedInUser();
        String input = null;
        Scanner scanner = new Scanner(System.in);
        try {
            while (input == null) {
                System.out.println("What field you want to edit? Pick one of following options:");
                input = scanner.nextLine();
                if (input.isEmpty()) {
                    log.warn("Could not parse input.");
                    continue;
                }
                input = input.toUpperCase().trim();
                if (input.equalsIgnoreCase(EditCommands.N.name())) {
                    String newNicknameInput = getNewFieldValue(scanner, EditCommands.N);
                    currentUser.setNickname(newNicknameInput);
                } else if (input.equalsIgnoreCase(EditCommands.B.name())) {
                    String newDobInput = getNewFieldValue(scanner, EditCommands.B);
                    currentUser.setDateOfBirth(LocalDate.parse(newDobInput));
                } else if (input.equalsIgnoreCase(EditCommands.A.name())) {
                    String newAboutInput = getNewFieldValue(scanner, EditCommands.A);
                    currentUser.setAbout(newAboutInput);
                } else if (input.length() == 1
                        && Pattern.compile("-?\\d+(\\.\\d+)?").matcher(input).matches()
                        && Arrays.asList(1, 2, 3, 4, 5).contains(Integer.parseInt(input))) {
                    throw new HandlerException(input);
                }
            }
        } catch (HandlerException e) {
            System.out.println(input);
            return dispatcherHandler.handle(editProfileView.getCommand(Integer.parseInt(e.getMessage())));
        }
        userService.update(currentUser);
        return new Response(true, null, editProfileView);
    }

    private String getNewFieldValue(Scanner scanner, EditCommands command) {
        String input = null;
        while (input == null) {
            String message = createMessage(command);
            System.out.println(message);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                log.warn("Could not parse input.");
            }
        }
        return input;
    }

    private String createMessage(EditCommands command) {
        String message = "Type in new value for ${field}:";

        switch (command) {
            case A:
                message = message.replace("${field", "about");
                break;
            case N:
                message = message.replace("${field", "nickname");
                break;
            case B:
                message = message.replace("${field", "date of birth in format 'dd-MM-yyyy':");
                break;
        }
        return message;
    }

    public enum EditCommands {
        N, B, A
    }
}
