package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.config.Configuration;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.persistence.UserDao;

public final class AppContext {

    private Configuration configuration;
    private TweetDao tweetDao;
    private UserDao userDao;

    private AppContext() {
    }

    public static AppContextBuilder builder() {
        return new AppContextBuilder();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    private void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    private void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TweetDao getTweetDao() {
        return tweetDao;
    }

    private void setTweetDao(TweetDao tweetDao) {
        this.tweetDao = tweetDao;
    }

    public static class AppContextBuilder {

        private final AppContext appContext = new AppContext();

        public AppContext build() {
            return this.appContext;
        }

        public AppContextBuilder configuration(Configuration configuration) {
            if (configuration == null) {
                throw new AppContextException("Provided configuration is null!");
            }
            this.appContext.setConfiguration(configuration);
            return this;
        }

        public AppContextBuilder userDao(UserDao userDao) {
            if (userDao == null) {
                throw new AppContextException("Provided userDao is null!");
            }
            this.appContext.setUserDao(userDao);
            return this;
        }

        public AppContextBuilder tweetDao(TweetDao tweetDao) {
            if (tweetDao == null) {
                throw new AppContextException("Provided tweetDao is null!");
            }
            this.appContext.setTweetDao(tweetDao);
            return this;
        }
    }
}
