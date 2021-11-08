package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.config.Configuration;
import cz.los.KL_Twitter.handler.ClosingHandler;
import cz.los.KL_Twitter.handler.DispatcherHandler;
import cz.los.KL_Twitter.handler.Handler;
import cz.los.KL_Twitter.handler.global.ExitHandler;
import cz.los.KL_Twitter.handler.global.HelpHandler;
import cz.los.KL_Twitter.handler.user.CreateUserHandler;
import cz.los.KL_Twitter.persistence.AuthDao;
import cz.los.KL_Twitter.persistence.SessionDao;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.persistence.UserDao;
import cz.los.KL_Twitter.service.AuthService;
import cz.los.KL_Twitter.service.UserService;
import lombok.Getter;
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
    DispatcherHandler dispatcherHandler;
    CreateUserHandler createUserHandler;
    ClosingHandler closingHandler;
    HelpHandler helpHandler;
    ExitHandler exitHandler;

    private AppContext(AppContextBuilder builder) {
        this.configuration = builder.configuration;
        this.sessionDao = builder.sessionDao;
        this.tweetDao = builder.tweetDao;
        this.userDao = builder.userDao;
        this.authDao = builder.authDao;
        this.userService = builder.userService;
        this.authService = builder.authService;
        this.dispatcherHandler = builder.dispatcherHandler;
        this.createUserHandler = builder.createUserHandler;
        this.closingHandler = builder.closingHandler;
        this.helpHandler = builder.helpHandler;
        this.exitHandler = builder.exitHandler;
    }

    public static AppContextBuilder builder() {
        return new AppContextBuilder();
    }

    @Getter
    public static class AppContextBuilder {

        private Configuration configuration;
        private SessionDao sessionDao;
        private TweetDao tweetDao;
        private UserDao userDao;
        private AuthDao authDao;
        private UserService userService;
        private AuthService authService;
        private DispatcherHandler dispatcherHandler;
        private CreateUserHandler createUserHandler;
        private ClosingHandler closingHandler;
        private HelpHandler helpHandler;
        private ExitHandler exitHandler;

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

        public AppContextBuilder dispatcherHandler(DispatcherHandler dispatcherHandler) {
            if (dispatcherHandler == null) {
                throw new AppContextException("Provided dispatcherHandler is null!");
            }
            this.dispatcherHandler = dispatcherHandler;
            return this;
        }

        public AppContextBuilder createUserHandler(CreateUserHandler createUserHandler) {
            if (createUserHandler == null) {
                throw new AppContextException("Provided createUserHandler is null!");
            }
            this.createUserHandler = createUserHandler;
            return this;
        }

        public AppContextBuilder helpHandler(HelpHandler helpHandler) {
            if (helpHandler == null) {
                throw new AppContextException("Provided helpHandler is null!");
            }
            this.helpHandler = helpHandler;
            return this;
        }

        public AppContextBuilder closingHandler(ClosingHandler closingHandler) {
            if (closingHandler == null) {
                throw new AppContextException("Provided closingHandler is null!");
            }
            this.closingHandler = closingHandler;
            return this;
        }

        public AppContextBuilder exitHandler(ExitHandler exitHandler) {
            if (exitHandler == null) {
                throw new AppContextException("Provided exitHandler is null!");
            }
            this.exitHandler = exitHandler;
            return this;
        }
    }
}
