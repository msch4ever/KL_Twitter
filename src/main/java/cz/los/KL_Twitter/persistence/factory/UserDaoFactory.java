package cz.los.KL_Twitter.persistence.factory;

import cz.los.KL_Twitter.model.PersistenceEntity;
import cz.los.KL_Twitter.persistence.GenericDao;
import cz.los.KL_Twitter.persistence.inMem.UserDaoInMemImpl;
import cz.los.KL_Twitter.persistence.jdbc.UserDaoJdbcImpl;
import org.springframework.stereotype.Component;

@Component
public class UserDaoFactory implements DaoFactory {

    @Override
    public GenericDao<? extends PersistenceEntity> createInMem() {
        return new UserDaoInMemImpl();
    }

    @Override
    public GenericDao<? extends PersistenceEntity> createJdbc() {
        return new UserDaoJdbcImpl();
    }
}
