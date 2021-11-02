package cz.los.KL_Twitter.persistence.factory;

import cz.los.KL_Twitter.config.DaoType;
import cz.los.KL_Twitter.model.PersistenceEntity;
import cz.los.KL_Twitter.persistence.GenericDao;

public interface DaoFactory {

    default GenericDao<? extends PersistenceEntity> createDao(DaoType type) {
        if (DaoType.IN_MEM == type) {
            return createInMem();
        }
        if (DaoType.JDBC == type) {
            return createJdbc();
        }
        throw new DaoFactoryException("Could not find DaoType:" + type);
    }

    GenericDao<? extends PersistenceEntity> createInMem();

    GenericDao<? extends PersistenceEntity> createJdbc();

}
