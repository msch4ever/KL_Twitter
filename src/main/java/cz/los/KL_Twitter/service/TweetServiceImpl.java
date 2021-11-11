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
}
