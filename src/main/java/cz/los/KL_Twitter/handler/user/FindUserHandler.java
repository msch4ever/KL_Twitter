package cz.los.KL_Twitter.handler.user;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.TweetLengthExceededException;
import cz.los.KL_Twitter.service.UserService;
import cz.los.KL_Twitter.views.FeedView;
import cz.los.KL_Twitter.views.ProfileView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Slf4j
@Component
public class FindUserHandler extends AbstractHandler {

    private final ProfileView profileView;
    private final FeedView feedView;
    private final UserService userService;

    public FindUserHandler(ProfileView profileView, FeedView feedView, UserService userService) {
        super(Command.FIND_USER);
        this.profileView = profileView;
        this.feedView = feedView;
        this.userService = userService;
    }

    @Override
    protected Response handleCommand() {
        String input = null;
        Scanner scanner = new Scanner(System.in);
        while (input == null) {
            System.out.println("Type in user login or nickname:");
            input = scanner.nextLine();
            if (input.isEmpty()) {
                log.warn("Could not parse input.");
                continue;
            }
            input = input.trim();
        }
        Optional<User> user;
        if ((user = userService.findByLogin(input)).isPresent()) {
            profileView.setUserId(user.get().getUserId());
        } else if ((user = userService.findByNickname(input)).isPresent()) {
            profileView.setUserId(user.get().getUserId());
        } else {
            feedView.setHomeMode();
            return new Response(false, null, feedView);
        }
        return new Response(true, null, profileView);
    }
}
