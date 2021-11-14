package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.persistence.TweetDao;

import java.util.List;

public class TweetServiceImpl implements TweetService {

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
}
