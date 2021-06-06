package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.model.Tweet;

import java.util.Set;

public interface TweetDao {

    Long saveTweet(Tweet tweet);

    void deleteTweetById(Long tweetId);

    Tweet getTweetById(Long tweetId);

    void updateTweet(Tweet tweet);

    Set<Tweet> getAllTweets();

}
