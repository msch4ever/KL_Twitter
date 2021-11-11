package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.TweetService;
import cz.los.KL_Twitter.service.UserService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FeedView extends AbstractView {

    private static final String CONTENT =
            "                                       GRANNY BENCH                                       \n" +
                    "${login}\n" +
                    "${feedMode}\n" +
                    "\n" +
                    "${tweets}\n" +
                    "\n" +
                    "[1] Sign Out    [2] Tweet    [3] Profile     [3] exit\n" +
                    "\n";

    private final TweetService tweetService;
    private final UserService userService;
    private Mode feedMode;


    public FeedView(TweetService tweetService, UserService userService) {
        super(initCommands());
        this.tweetService = tweetService;
        this.userService = userService;
        this.feedMode = Mode.HOME;
    }

    private static Map<Integer, Command> initCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.SIGN_OUT);
        commands.put(2, Command.TWEET);
        commands.put(3, Command.PROFILE);
        commands.put(4, Command.EXIT);
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
        content = content.replace("${login}", getLogin());
        content = content.replace("${feedMode}", createFeedModeLine());
        content = content.replace("${tweets}", createTweetsList());

        return content;
    }

    private String getLogin() {
        String login = ContextHolder.getSecurityContext().getSession().getUserAuthentication().getLogin();
        return String.format("%90s", "Welcome @" + login);
    }

    private CharSequence createFeedModeLine() {
        return feedMode.toString().substring(0, 1).concat(feedMode.toString().substring(1).concat(" feed"));
    }

    private CharSequence createTweetsList() {
        User user = ContextHolder.getSecurityContext().getSession().getLoggedInUser();
        List<Tweet> tweets = tweetService.findTweetsFromFollowing(user.getUserId());
        Map<Long, User> authors = userService
                .findAllByIdInList(tweets.stream().map(Tweet::getUserId).collect(Collectors.toList())).stream()
                .collect(Collectors.toMap(User::getUserId, Function.identity()));
        StringBuilder sb = new StringBuilder();
        for (Tweet tweet : tweets) {
            sb.append(SEPARATOR).append(createTweetMessage(tweet, authors.get(tweet.getUserId())));
        }
        sb.append(SEPARATOR);
        return sb.toString();
    }

    private String createTweetMessage(Tweet tweet, User user) {
        String passed = getTimePassedText(tweet);
        String firstLine = String.format("%90s", passed);
        String userInfo = getUserInfoText(user);
        firstLine = userInfo + firstLine.substring(userInfo.length());
        String tweetText = firstLine + System.lineSeparator();
        List<String> lines = getContentLines(tweet.getContent());
        for (String line : lines) {
            tweetText = tweetText + line;
        }
        return tweetText;
    }

    private String getTimePassedText(Tweet tweet) {
        long between = ChronoUnit.MINUTES.between(LocalDateTime.now(), tweet.getDatePosted());
        if (between < 60) {
            return between + " min ago";
        } else if (between > 60L && between < 1140L) {
            return between / 60L + " hrs ago";
        } else {
            return between / 1140L + " days ago";
        }
    }

    private String getUserInfoText(User user) {
        return user.getNickname() + " | @" + user.getLogin();
    }

    private List<String> getContentLines(String content) {
        List<String> lines = new ArrayList<>();
        int start = 0;
        while (true) {
            int end = start + 50;
            String current;
            if (end > content.length()) {
                current = content.substring(start);
            } else {
                current = content.substring(start, end);
            }
            lines.add("%35s" + current + System.lineSeparator());
            start = end;
            if (end > content.length()) {
                break;
            }
        }
        return lines;
    }

    public void setUserMode() {
        this.feedMode = Mode.USER;
    }

    public void setHomeMode() {
        this.feedMode = Mode.HOME;
    }

    public void setTweetMode() {
        this.feedMode = Mode.TWEET;
    }

    public enum Mode {
        HOME, USER, TWEET;
    }
}
