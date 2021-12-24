package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.app.SecurityContext;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.UserService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProfileView extends AbstractView {

    private static final String CONTENT =
            "                                       GRANNY BENCH                                       \n" +
                    "${nickname} | @{login}\n" +
                    "born: ${dob}\n" +
                    "registered since: ${registered}\n" +
                    "followers: ${followers}\n" +
                    "following: ${following}\n" +
                    "about: ${about}\n" +
                    "\n" +
                    "[1] Sign Out    [2] Tweet    [3] Find User    [4] ${follow/unfollow/edit}    [5] Exit\n" +
                    "\n";

    private final UserService userService;
    private final SecurityContext securityContext;
    @Getter
    private Long userId;


    public ProfileView(UserService userService, SecurityContext securityContext) {
        super(initCommands());
        this.userService = userService;
        this.securityContext = securityContext;
    }

    private static Map<Integer, Command> initCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.SIGN_OUT);
        commands.put(2, Command.TWEET);
        commands.put(3, Command.FIND_USER);
        commands.put(4, null);
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
        User user = userService.findById(userId).orElseGet(() -> securityContext.getSession().getLoggedInUser());
        content = content.replace("${nickname}", user.getNickname());
        content = content.replace("${login}", user.getLogin());
        LocalDate dateOfBirth = user.getDateOfBirth();
        content = dateOfBirth != null ? content.replace("${dob}", dateOfBirth.toString()) : content.replace("${dob}", "none");
        content = content.replace("${registered}", user.getDateRegistered().toString());
        content = content.replace("${followers}", String.valueOf(userService.countFollowers(userId)));
        content = content.replace("${following}", String.valueOf(userService.countFollowing(userId)));
        String about = user.getAbout();
        content = about != null ? content.replace("${about}", user.getAbout()) : content.replace("${about}", "none");

        Long loggedInUserId = securityContext.getSession().getLoggedInUser().getUserId();
        if (this.userId.equals(loggedInUserId)) {
            content = content.replace("${follow/unfollow/edit}", "Edit");
            commands.put(4, Command.EDIT);
        } else if (userService.userIsFollowingOther(loggedInUserId, userId)) {
            content = content.replace("${follow/unfollow/edit}", "Unfollow");
            commands.put(4, Command.UNFOLLOW);
        } else {
            content = content.replace("${follow/unfollow/edit}", "Follow");
            commands.put(4, Command.FOLLOW);
        }
        return content;

    }

    public void setUserId(Long userId) {
        if (userId == null) {
            userId = securityContext.getSession().getLoggedInUser().getUserId();
        }
        this.userId = userId;
    }

}
