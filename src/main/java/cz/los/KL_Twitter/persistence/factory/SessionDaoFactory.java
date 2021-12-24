package cz.los.KL_Twitter.persistence.factory;

import cz.los.KL_Twitter.model.PersistenceEntity;
import cz.los.KL_Twitter.persistence.GenericDao;
import cz.los.KL_Twitter.persistence.inMem.SessionDaoInMemImpl;
import cz.los.KL_Twitter.persistence.jdbc.SessionDaoJdbcImpl;
import org.springframework.stereotype.Component;

@Component
public class SessionDaoFactory implements DaoFactory {

    @Override
    public GenericDao<? extends PersistenceEntity> createInMem() {
        return new SessionDaoInMemImpl();
    }

    @Override
    public GenericDao<? extends PersistenceEntity> createJdbc() {
        return new SessionDaoJdbcImpl();
    }
}
