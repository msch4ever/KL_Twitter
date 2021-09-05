package cz.los.KL_Twitter.persistence.factory;

import cz.los.KL_Twitter.config.DaoType;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.persistence.UserDao;

public class DaoAbstractFactory {

    private final DaoFactory userDaoFactory;
    private final DaoFactory tweetDaoFactory;

    public DaoAbstractFactory() {
        this.userDaoFactory = new UserDaoFactory();
        this.tweetDaoFactory = new TweetDaoFactory();
    }

    public UserDao createUserDao(DaoType daoType) {
        return (UserDao) userDaoFactory.createDao(daoType);
    }

    public TweetDao createTweetDao(DaoType daoType) {
        return (TweetDao) tweetDaoFactory.createDao(daoType);
    }

}
