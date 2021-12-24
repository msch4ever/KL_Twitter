package cz.los.KL_Twitter.persistence.factory;

import cz.los.KL_Twitter.config.DaoType;
import cz.los.KL_Twitter.persistence.AuthDao;
import cz.los.KL_Twitter.persistence.SessionDao;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.persistence.UserDao;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Value
@Component
public class DaoAbstractFactory {

    DaoType daoType;

    DaoFactory userDaoFactory;
    DaoFactory tweetDaoFactory;
    DaoFactory authDaoFactory;
    DaoFactory sessionDaoFactory;

    public DaoAbstractFactory(DaoType daoType,
                              UserDaoFactory userDaoFactory,
                              TweetDaoFactory tweetDaoFactory,
                              AuthDaoFactory authDaoFactory,
                              SessionDaoFactory sessionDaoFactory) {
        this.daoType = daoType;
        this.userDaoFactory = userDaoFactory;
        this.tweetDaoFactory = tweetDaoFactory;
        this.authDaoFactory = authDaoFactory;
        this.sessionDaoFactory = sessionDaoFactory;
    }

    public UserDao createUserDao() {
        log.debug("Creating User Dao of type:{}", daoType);
        return (UserDao) userDaoFactory.createDao(daoType);
    }

    public TweetDao createTweetDao() {
        log.debug("Creating Tweet Dao of type:{}", daoType);
        return (TweetDao) tweetDaoFactory.createDao(daoType);
    }

    public AuthDao createAuthDao() {
        log.debug("Creating Auth Dao of type:{}", daoType);
        return (AuthDao) authDaoFactory.createDao(daoType);
    }

    public SessionDao createSessionDao() {
        log.debug("Creating Session Dao of type:{}", daoType);
        return (SessionDao) sessionDaoFactory.createDao(daoType);
    }

}
