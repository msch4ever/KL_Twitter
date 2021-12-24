package cz.los.KL_Twitter.config;

import cz.los.KL_Twitter.persistence.DbUtils;
import cz.los.KL_Twitter.persistence.factory.DaoFactoryException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static cz.los.KL_Twitter.config.DaoType.IN_MEM;
import static cz.los.KL_Twitter.config.DaoType.JDBC;

@Slf4j
@Value
@Component
public class Configurator {

    DaoType daoType;
    Boolean initDb;
    Boolean populateDb;
    ApplicationContext context;

    public void initDB() {
        if (initDb && daoType == JDBC) {
            log.debug("Creating new database..");
            DbUtils.initDb();
            log.debug("New database created.");
        }
        if (populateDb) {
            log.debug("Populating database with sandbox data..");
            if (daoType == JDBC) {
                DbUtils.populateJDBC();
            } else if (daoType == IN_MEM) {
                DbUtils.populateInMem(context);
            } else {
                throw new DaoFactoryException("Could not recognize dao type!");
            }
            log.debug("Database populated.");
        }
    }

}
