package cz.los.KL_Twitter.handler.tweet;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.app.SecurityContext;
import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.TweetLengthExceededException;
import cz.los.KL_Twitter.service.TweetService;
import cz.los.KL_Twitter.service.UserService;
import cz.los.KL_Twitter.views.FeedView;
import cz.los.KL_Twitter.views.WelcomeView;
import cz.los.KL_Twitter.views.WriteTweetView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class WriteTweetHandler extends AbstractHandler {

    private final SecurityContext securityContext;
    private final WriteTweetView writeTweetView;
    private final TweetService tweetService;
    private final FeedView feedView;

    public WriteTweetHandler(SecurityContext securityContext,WriteTweetView writeTweetView, TweetService tweetService, FeedView feedView) {
        super(Command.TWEET);
        this.securityContext = securityContext;
        this.writeTweetView = writeTweetView;
        this.tweetService = tweetService;
        this.feedView = feedView;

    }

    @Override
    public Response handleCommand() {
        writeTweetView.render();
        String input;
        String rawContent;
        User user = securityContext.getSession().getLoggedInUser();
        Tweet tweet = null;
        Scanner scanner = new Scanner(System.in);
        while (tweet == null) {
            System.out.println("Write your tweet:");
            input = scanner.nextLine();
            if (input.isEmpty()) {
                log.warn("Could not parse input.");
                continue;
            }
            rawContent = input.trim();
            try {
                tweet = tweetService.createTweet(rawContent, user);
            } catch (TweetLengthExceededException e) {
                log.warn(e.getMessage());
                System.out.println("Tweet length is too long.");
            }
        }
        tweetService.save(tweet);
        feedView.setHomeMode();
        return new Response(true, command, feedView);
    }

}
