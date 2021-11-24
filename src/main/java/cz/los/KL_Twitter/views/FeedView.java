package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.model.Feed;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.FeedService;
import cz.los.KL_Twitter.service.TweetService;
import cz.los.KL_Twitter.service.UserService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedView extends AbstractView {

    private static final String CONTENT =
            "                                       GRANNY BENCH                                       \n" +
                    "${login}\n" +
                    "${feedMode}\n" +
                    "\n" +
                    "${tweets}\n" +
                    "\n" +
                    "[1] Sign Out    [2] Tweet    [3] Find User    [4] Profile    [5] Exit\n" +
                    "\n";
    public static final String TEN_SPACES = "          ";

    private final TweetService tweetService;
    private final UserService userService;
    private final FeedService feedService;
    private Mode feedMode;
    private Long entityId;


    public FeedView(TweetService tweetService, UserService userService, FeedService feedService) {
        super(initCommands());
        this.tweetService = tweetService;
        this.userService = userService;
        this.feedService = feedService;
        this.feedMode = Mode.HOME;
    }

    private static Map<Integer, Command> initCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.SIGN_OUT);
        commands.put(2, Command.TWEET);
        commands.put(3, Command.FIND_USER);
        commands.put(4, Command.MY_PROFILE);
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
        Feed feed = feedService.createFeed(feedMode, entityId);
        StringBuilder sb = new StringBuilder();
        for (Tweet tweet : feed.getTweets()) {
            sb.append(SEPARATOR)
                    .append(System.lineSeparator())
                    .append(createTweetMessage(tweet, feed.getAuthors().get(tweet.getUserId())));
        }
        sb.append(SEPARATOR);
        return sb.toString();
    }

    private String createTweetMessage(Tweet tweet, User user) {
        String passed = getTimePassedText(tweet);
        String firstLine = String.format("%90s", passed);
        String userInfo = getUserInfoText(user);
        firstLine = userInfo + firstLine.substring(userInfo.length());
        StringBuilder tweetText = new StringBuilder(firstLine + System.lineSeparator());
        List<String> lines = getContentLines(tweet.getContent());
        for (String line : lines) {
            tweetText.append(line);
        }
        String repliesText = String.format("%1$-20s", "replies:" + tweetService.getReplyCount(tweet.getTweetId()));
        String statsLine = repliesText + "likes:" + tweetService.getLikesCount(tweet.getTweetId()) + System.lineSeparator();
        tweetText.append(statsLine);
        return tweetText.toString();
    }

    private String getTimePassedText(Tweet tweet) {
        long between = ChronoUnit.MINUTES.between(tweet.getDatePosted(), LocalDateTime.now());
        if (between < 60L) {
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
        int end = 0;
        while (end < content.length()) {
            while (content.charAt(start) == ' ') {
                start++;
            }
            end = start + 50;
            String current;
            if (end >= content.length()) {
                current = content.substring(start);
            } else {
                while (content.charAt(end) != ' ') {
                    end--;
                }
                current = content.substring(start, end).trim();
            }
            lines.add(TEN_SPACES + current + System.lineSeparator());
            start = end;
        }
        return lines;
    }

    public void setUserMode(Long userId) {
        this.feedMode = Mode.USER;
        this.entityId = userId;
    }

    public void setHomeMode() {
        this.feedMode = Mode.HOME;
        this.entityId = ContextHolder.getSecurityContext().getSession().getLoggedInUser().getUserId();
    }

    public void setTweetMode(Long tweetId) {
        this.feedMode = Mode.TWEET;
        this.entityId = tweetId;
    }

    public enum Mode {
        HOME, USER, TWEET;
    }
}
