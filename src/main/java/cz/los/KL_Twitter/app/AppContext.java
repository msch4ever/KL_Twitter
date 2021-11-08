package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.config.Configuration;
import cz.los.KL_Twitter.persistence.AuthDao;
import cz.los.KL_Twitter.persistence.SessionDao;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.persistence.UserDao;
import cz.los.KL_Twitter.service.AuthService;
import cz.los.KL_Twitter.service.UserService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class AppContext {

    Configuration configuration;
    SessionDao sessionDao;
    TweetDao tweetDao;
    UserDao userDao;
    AuthDao authDao;
    UserService userService;
    AuthService authService;

    private AppContext(AppContextBuilder builder) {
        this.configuration = builder.configuration;
        this.sessionDao = builder.sessionDao;
        this.tweetDao = builder.tweetDao;
        this.userDao = builder.userDao;
        this.authDao = builder.authDao;
        this.userService = builder.userService;
        this.authService = builder.authService;
    }

    public static AppContextBuilder builder() {
        return new AppContextBuilder();
    }

    public static class AppContextBuilder {

        private Configuration configuration;
        private SessionDao sessionDao;
        private TweetDao tweetDao;
        private UserDao userDao;
        private AuthDao authDao;
        private UserService userService;
        private AuthService authService;

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

        public AppContextBuilder sessionDao(SessionDao sessionDao) {
            if (sessionDao == null) {
                throw new AppContextException("Provided sessionDao is null!");
            }
            this.sessionDao = sessionDao;
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

        public AppContextBuilder authDao(AuthDao authDao) {
            if (authDao == null) {
                throw new AppContextException("Provided authDao is null!");
            }
            this.authDao = authDao;
            return this;
        }

        public AppContextBuilder authService(AuthService authService) {
            if (authService == null) {
                throw new AppContextException("Provided authService is null!");
            }
            this.authService = authService;
            return this;
        }

        public AppContextBuilder userService(UserService userService) {
            if (userService == null) {
                throw new AppContextException("Provided userService is null!");
            }
            this.userService = userService;
            return this;
        }
    }
}
