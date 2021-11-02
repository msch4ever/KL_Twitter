package cz.los.KL_Twitter.persistence.impl;

import cz.los.KL_Twitter.db.Storage;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.persistence.TweetDao;

import java.util.Set;

public class TweetDaoInMemImpl implements TweetDao {

    @Override
    public Long saveTweet(Tweet tweet) {
        return Storage.putTweetToStorage(tweet);
    }

    @Override
    public void deleteTweetById(Long tweetId) {
        Storage.deleteTweet(tweetId);
    }

    @Override
    public Tweet getTweetById(Long tweetId) {
        return Storage.getTweetById(tweetId);
    }

    @Override
    public void updateTweet(Tweet tweet) {
        Storage.putTweetToStorage(tweet);
    }

    @Override
    public Set<Tweet> getAllTweets() {
        return Storage.getTweets();
    }
}
