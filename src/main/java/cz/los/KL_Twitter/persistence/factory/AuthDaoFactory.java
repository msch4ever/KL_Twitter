package cz.los.KL_Twitter.persistence.factory;

import cz.los.KL_Twitter.model.PersistenceEntity;
import cz.los.KL_Twitter.persistence.GenericDao;
import cz.los.KL_Twitter.persistence.inMem.AuthDaoInMemImpl;
import cz.los.KL_Twitter.persistence.inMem.TweetDaoInMemImpl;
import cz.los.KL_Twitter.persistence.jdbc.AuthDaoJdbcImpl;
import cz.los.KL_Twitter.persistence.jdbc.TweetDaoJdbcImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class AuthDaoFactory implements DaoFactory {

    @Override
    public GenericDao<? extends PersistenceEntity> createInMem() {
        return new AuthDaoInMemImpl();
    }

    @Override
    public GenericDao<? extends PersistenceEntity> createJdbc() {
        return new AuthDaoJdbcImpl();
    }
}
