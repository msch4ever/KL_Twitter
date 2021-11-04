package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.config.Configuration;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.persistence.UserDao;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class AppContext {

    Configuration configuration;
    TweetDao tweetDao;
    UserDao userDao;

    private AppContext(AppContextBuilder builder) {
        this.configuration = builder.configuration;
        this.tweetDao = builder.tweetDao;
        this.userDao = builder.userDao;
    }

    public static AppContextBuilder builder() {
        return new AppContextBuilder();
    }

    public static class AppContextBuilder {

        private Configuration configuration;
        private TweetDao tweetDao;
        private UserDao userDao;

        public AppContext build() {
            return new AppContext(this);
        }

        public AppContextBuilder configuration(Configuration configuration) {
            if (configuration == null) {
                throw new AppContextException("Provided configuration is null!");
            }
            this.configuration = configuration;
            return this;
        }

        public AppContextBuilder userDao(UserDao userDao) {
            if (userDao == null) {
                throw new AppContextException("Provided userDao is null!");
            }
            this.userDao = userDao;
            return this;
        }

        public AppContextBuilder tweetDao(TweetDao tweetDao) {
            if (tweetDao == null) {
                throw new AppContextException("Provided tweetDao is null!");
            }
            this.tweetDao = tweetDao;
            return this;
        }
    }
}
