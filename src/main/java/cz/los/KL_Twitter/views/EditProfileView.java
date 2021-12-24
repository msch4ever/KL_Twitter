package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.UserService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class EditProfileView extends AbstractView {

    private static final String CONTENT =
            "                                       GRANNY BENCH                                       \n" +
                    "EDIT USER PROFILE VIEW" +
                    "[N]    nickname: ${nickname}\n" +
                    "[B]    born:     ${dob}\n" +
                    "[A]    about:    ${about}\n" +
                    "\n" +
                    "[1] Sign Out    [2] Home    [3] Tweet    [4] Find User    [5] Exit\n" +
                    "\n";

    public EditProfileView() {
        super(initCommands());
    }

    private static Map<Integer, Command> initCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.SIGN_OUT);
        commands.put(2, Command.TWEET);
        commands.put(3, Command.HOME);
        commands.put(4, Command.FIND_USER);
        commands.put(5, Command.EXIT);
        return commands;
    }

    @Override
    public AbstractView render() {
        clearScreen();
        System.out.println(SEPARATOR);
        System.out.println(fillTheContent());
        System.out.println(SEPARATOR);
        return this;
    }

    private String fillTheContent() {
        String content = CONTENT;
        User user = ContextHolder.getSecurityContext().getSession().getLoggedInUser();
        content = content.replace("${nickname}", user.getNickname());
        content = content.replace("${login}", user.getLogin());
        LocalDate dateOfBirth = user.getDateOfBirth();
        content = dateOfBirth != null ? content.replace("${dob}", dateOfBirth.toString()) : content.replace("${dob}", "none");
        content = content.replace("${about}", user.getAbout());

        return content;

    }

}
