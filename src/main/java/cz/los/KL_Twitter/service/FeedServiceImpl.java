package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.model.*;
import cz.los.KL_Twitter.views.FeedView;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FeedServiceImpl implements FeedService {

    private final TweetService tweetService;
    private final UserService userService;

    public FeedServiceImpl(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @Override
    public Feed createFeed(FeedView.Mode mode, Long id) {
        List<Tweet> tweets;
        Map<Long, User> feedAuthors;
        if (mode == FeedView.Mode.HOME) {
            tweets = tweetService.findTweetsFromFollowing(id);
            feedAuthors = getFeedAuthors(tweets);
            return new UserFeed(tweets, feedAuthors, id, true);
        } else if (mode == FeedView.Mode.USER) {
            tweets = tweetService.findTweetsByUserId(id);
            feedAuthors = getFeedAuthors(tweets);
            return new UserFeed(tweets, feedAuthors, id, false);
        } else if (mode == FeedView.Mode.TWEET) {
            tweets = tweetService.findTweetReplies(id);
            feedAuthors = getFeedAuthors(tweets);
            return new TweetFeed(tweets, feedAuthors, id);
        } else {
            throw new IllegalArgumentException("Could not find feed mode [" + mode +"]!");
        }
    }

    @Override
    public Map<Long, User> getFeedAuthors(List<Tweet> tweets) {
        return userService.findAllByIdInList(tweets.stream()
                        .map(Tweet::getUserId)
                        .collect(Collectors.toList())).stream()
                .collect(Collectors.toMap(User::getUserId, Function.identity()));
    }
}
