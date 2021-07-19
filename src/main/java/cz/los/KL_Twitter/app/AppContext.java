package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.config.Configuration;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.persistence.UserDao;

public class AppContext {

    private final Configuration configuration;
    private final UserDao userDao;
    private final TweetDao tweetDao;

    public AppContext(Configuration configuration, UserDao userDao, TweetDao tweetDao) {
        this.configuration = configuration;
        this.userDao = userDao;
        this.tweetDao = tweetDao;
    }

}
