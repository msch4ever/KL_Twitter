package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;

import java.util.List;

public interface TweetService {

    List<Tweet> findTweetsFromFollowing(long userId);

    long getLikesCount(Long id);

    long getReplyCount(Long id);

    List<Tweet> findTweetsByUserId(Long id);

    List<Tweet> findTweetReplies(Long id);

    Tweet createTweet(String rawContent, User user) throws TweetLengthExceededException ;

    void save(Tweet tweet);
}
