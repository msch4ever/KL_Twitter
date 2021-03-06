package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.config.Configuration;
import cz.los.KL_Twitter.handler.ClosingHandler;
import cz.los.KL_Twitter.handler.DispatcherHandler;
import cz.los.KL_Twitter.handler.global.ExitHandler;
import cz.los.KL_Twitter.handler.global.HelpHandler;
import cz.los.KL_Twitter.handler.tweet.WriteTweetHandler;
import cz.los.KL_Twitter.handler.user.*;
import cz.los.KL_Twitter.persistence.AuthDao;
import cz.los.KL_Twitter.persistence.SessionDao;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.persistence.UserDao;
import cz.los.KL_Twitter.service.AuthService;
import cz.los.KL_Twitter.service.FeedService;
import cz.los.KL_Twitter.service.TweetService;
import cz.los.KL_Twitter.service.UserService;
import cz.los.KL_Twitter.views.*;
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
    TweetService tweetService;
    UserService userService;
    FeedService feedService;
    AuthService authService;
    DispatcherHandler dispatcherHandler;
    CreateUserHandler createUserHandler;
    WriteTweetHandler writeTweetHandler;
    MyProfileHandler myProfileHandler;
    EditUserHandler editUserHandler;
    UnfollowHandler unfollowHandler;
    FindUserHandler findUserHandler;
    ClosingHandler closingHandler;
    FollowHandler followHandler;
    LogoutHandler logoutHandler;
    LoginHandler loginHandler;
    HelpHandler helpHandler;
    ExitHandler exitHandler;
    WriteTweetView writeTweetView;
    EditProfileView editProfileView;
    ProfileView profileView;
    WelcomeView welcomeView;
    FeedView feedView;

    private AppContext(AppContextBuilder builder) {
        this.configuration = builder.configuration;
        this.sessionDao = builder.sessionDao;
        this.tweetDao = builder.tweetDao;
        this.userDao = builder.userDao;
        this.authDao = builder.authDao;
        this.tweetService = builder.tweetService;
        this.userService = builder.userService;
        this.feedService = builder.feedService;
        this.authService = builder.authService;
        this.dispatcherHandler = builder.dispatcherHandler;
        this.createUserHandler = builder.createUserHandler;
        this.writeTweetHandler = builder.writeTweetHandler;
        this.myProfileHandler = builder.myProfileHandler;
        this.editUserHandler = builder.editUserHandler;
        this.unfollowHandler = builder.unfollowHandler;
        this.findUserHandler = builder.findUserHandler;
        this.closingHandler = builder.closingHandler;
        this.followHandler = builder.followHandler;
        this.logoutHandler = builder.logoutHandler;
        this.loginHandler = builder.loginHandler;
        this.helpHandler = builder.helpHandler;
        this.exitHandler = builder.exitHandler;
        this.writeTweetView = builder.writeTweetView;
        this.editProfileView = builder.editProfileView;
        this.profileView = builder.profileView;
        this.welcomeView = builder.welcomeView;
        this.feedView = builder.feedView;
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
        private TweetService tweetService;
        private UserService userService;
        private FeedService feedService;
        private AuthService authService;
        private DispatcherHandler dispatcherHandler;
        private CreateUserHandler createUserHandler;
        private WriteTweetHandler writeTweetHandler;
        private MyProfileHandler myProfileHandler;
        private EditUserHandler editUserHandler;
        private UnfollowHandler unfollowHandler;
        private FindUserHandler findUserHandler;
        private ClosingHandler closingHandler;
        private WriteTweetView writeTweetView;
        private LogoutHandler logoutHandler;
        private FollowHandler followHandler;
        private LoginHandler loginHandler;
        private HelpHandler helpHandler;
        private ExitHandler exitHandler;
        private EditProfileView editProfileView;
        private ProfileView profileView;
        private WelcomeView welcomeView;
        private FeedView feedView;

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

        public AppContextBuilder tweetService(TweetService tweetService) {
            if (tweetService == null) {
                throw new AppContextException("Provided tweetService is null!");
            }
            this.tweetService = tweetService;
            return this;
        }

        public AppContextBuilder feedService(FeedService feedService) {
            if (feedService == null) {
                throw new AppContextException("Provided feedService is null!");
            }
            this.feedService = feedService;
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

        public AppContextBuilder unfollowHandler(UnfollowHandler unfollowHandler) {
            if (unfollowHandler == null) {
                throw new AppContextException("Provided unfollowHandler is null!");
            }
            this.unfollowHandler = unfollowHandler;
            return this;
        }

        public AppContextBuilder findUserHandler(FindUserHandler findUserHandler) {
            if (findUserHandler == null) {
                throw new AppContextException("Provided findUserHandler is null!");
            }
            this.findUserHandler = findUserHandler;
            return this;
        }

        public AppContextBuilder followHandler(FollowHandler followHandler) {
            if (followHandler == null) {
                throw new AppContextException("Provided followHandler is null!");
            }
            this.followHandler = followHandler;
            return this;
        }

        public AppContextBuilder writeTweetHandler(WriteTweetHandler writeTweetHandler) {
            if (writeTweetHandler == null) {
                throw new AppContextException("Provided writeTweetHandler is null!");
            }
            this.writeTweetHandler = writeTweetHandler;
            return this;
        }

        public AppContextBuilder myProfileHandler(MyProfileHandler myProfileHandler) {
            if (myProfileHandler == null) {
                throw new AppContextException("Provided myProfileHandler is null!");
            }
            this.myProfileHandler = myProfileHandler;
            return this;
        }

        public AppContextBuilder editUserHandler(EditUserHandler editUserHandler) {
            if (editUserHandler == null) {
                throw new AppContextException("Provided editUserHandler is null!");
            }
            this.editUserHandler = editUserHandler;
            return this;
        }

        public AppContextBuilder logoutHandler(LogoutHandler logoutHandler) {
            if (logoutHandler == null) {
                throw new AppContextException("Provided logoutHandler is null!");
            }
            this.logoutHandler = logoutHandler;
            return this;
        }

        public AppContextBuilder loginHandler(LoginHandler loginHandler) {
            if (loginHandler == null) {
                throw new AppContextException("Provided loginHandler is null!");
            }
            this.loginHandler = loginHandler;
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

        public AppContextBuilder editProfileView(EditProfileView editProfileView) {
            if (editProfileView == null) {
                throw new AppContextException("Provided editProfileView is null!");
            }
            this.editProfileView = editProfileView;
            return this;
        }

        public AppContextBuilder profileView(ProfileView profileView) {
            if (profileView == null) {
                throw new AppContextException("Provided profileView is null!");
            }
            this.profileView = profileView;
            return this;
        }

        public AppContextBuilder welcomeView(WelcomeView welcomeView) {
            if (welcomeView == null) {
                throw new AppContextException("Provided welcomeView is null!");
            }
            this.welcomeView = welcomeView;
            return this;
        }

        public AppContextBuilder feedView(FeedView feedView) {
            if (feedView == null) {
                throw new AppContextException("Provided feedView is null!");
            }
            this.feedView = feedView;
            return this;
        }

        public AppContextBuilder writeTweetView(WriteTweetView writeTweetView) {
            if (writeTweetView == null) {
                throw new AppContextException("Provided writeTweetView is null!");
            }
            this.writeTweetView = writeTweetView;
            return this;
        }
    }
}
