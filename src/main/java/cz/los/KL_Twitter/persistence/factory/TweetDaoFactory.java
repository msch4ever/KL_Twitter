package cz.los.KL_Twitter.persistence.factory;

import cz.los.KL_Twitter.model.PersistenceEntity;
import cz.los.KL_Twitter.persistence.GenericDao;
import cz.los.KL_Twitter.persistence.inMem.TweetDaoInMemImpl;
import cz.los.KL_Twitter.persistence.jdbc.TweetDaoJdbcImpl;
import org.springframework.stereotype.Component;

@Component
public class TweetDaoFactory implements DaoFactory {

    @Override
    public GenericDao<? extends PersistenceEntity> createInMem() {
        return new TweetDaoInMemImpl();
    }

    @Override
    public GenericDao<? extends PersistenceEntity> createJdbc() {
        return new TweetDaoJdbcImpl();
    }
}
