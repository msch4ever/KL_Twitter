package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.persistence.TweetDao;

import java.util.List;

public class TweetServiceImpl implements TweetService {

    private static final int MAX_UTILITY_CONTENT_LEN = 20;
    private static final int MAX_TWEET_CONTENT_LEN = 140;
    private static final int MAX_TWEET_LEN = 160;
    private final TweetDao tweetDao;

    public TweetServiceImpl(TweetDao tweetDao) {
        this.tweetDao = tweetDao;
    }

    @Override
    public List<Tweet> findTweetsFromFollowing(long userId) {
        return tweetDao.findTweetsFromFollowing(userId);
    }

    @Override
    public List<Tweet> findTweetsByUserId(Long id) {
        return tweetDao.findTweetsByUserId(id);
    }

    @Override
    public List<Tweet> findTweetReplies(Long id) {
        return tweetDao.findTweetReplies(id);
    }

    @Override
    public long getLikesCount(Long id) {
        return tweetDao.getLikesCount(id);
    }

    @Override
    public long getReplyCount(Long id) {
        return tweetDao.getReplyCount(id);
    }

    @Override
    public Tweet createTweet(String rawContent, User user) throws TweetLengthExceededException {
        String content = validateInput(rawContent); //ToDo: parse mentioned users
        return new Tweet(user.getUserId(), null, content);
    }

    private String validateInput(String rawContent) throws TweetLengthExceededException {
        if (rawContent.length() > MAX_TWEET_LEN) {
            throw new TweetLengthExceededException("Tweet length is greater then allowed. Provided:"
                    + rawContent.length()
                    + "; allowed:" + MAX_TWEET_LEN);
        }
        return rawContent;
    }

    @Override
    public void save(Tweet tweet) {
        tweetDao.save(tweet);
    }
}
