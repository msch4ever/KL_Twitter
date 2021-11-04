package cz.los.KL_Twitter.config;

import cz.los.KL_Twitter.app.AppContext;
import cz.los.KL_Twitter.app.AppContextHolder;
import cz.los.KL_Twitter.persistence.factory.DaoAbstractFactory;
import cz.los.KL_Twitter.persistence.factory.DaoFactoryException;
import cz.los.KL_Twitter.persistence.jdbc.DbUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.Arrays;

import static cz.los.KL_Twitter.config.DaoType.IN_MEM;
import static cz.los.KL_Twitter.config.DaoType.JDBC;

@Slf4j
public class Configurator {

    public static final String DAO_TYPE = "daoType";
    public static final String INIT_DB = "initDb";
    public static final String POPULATE_DB = "populateDb";

    public static void initApp(String[] args) {
        long start = System.currentTimeMillis();
        log.info("Initializing application..");
        log.debug("CMD params:{}", Arrays.toString(args));
        Configuration config = createConfig(args);
        log.debug("Creating Application context..");
        DaoAbstractFactory factory = new DaoAbstractFactory();
        AppContext.AppContextBuilder builder = AppContext.builder();
        builder.configuration(config);
        builder.userDao(factory.createUserDao(config.getDaoType()));
        builder.tweetDao(factory.createTweetDao(config.getDaoType()));
        //ToDo: create all handlers and put into AppContext;
        AppContextHolder.initAppContext(builder.build());
        log.debug("Application context created. {}", AppContextHolder.getAppContext());
        log.info("Preparing database according to configuration..");
        initDB(config);
        log.info("Database prepared.");
        long end = System.currentTimeMillis();
        log.info("Application initialized successfully in {} seconds!", (end - start) / 1000.0D);
    }

    private static void initDB(Configuration config) {
        if (config.initDb() && config.getDaoType() == JDBC) {
            log.debug("Creating new database..");
            DbUtils.initDb();
            log.debug("New database created.");
        }
        if (config.populateDb()) {
            log.debug("Populating database with sandbox data..");
            if (config.getDaoType() == JDBC) {
                DbUtils.populateJDBC();
            } else if (config.getDaoType() == IN_MEM) {
                DbUtils.populateInMem();
            } else {
                throw new DaoFactoryException("Could not recognize dao type!");
            }
            log.debug("Database populated.");
        }
    }

    private static Configuration createConfig(String[] args) {
        Options options = new Options();
        options.addRequiredOption("dt", DAO_TYPE, true, "choose dao type for this run.");
        options.addOption(INIT_DB, false, "flag for initial creation of the DB.");
        options.addOption(POPULATE_DB, false, "flag for population the DB with fake data for a showdown..");
        try {
            CommandLine cmd = new DefaultParser().parse(options, args);
            Configuration config = Configuration.builder()
                    .daoType(cmd.getOptionValue(DAO_TYPE))
                    .initDb(cmd.hasOption(INIT_DB))
                    .populateDb(cmd.hasOption(POPULATE_DB))
                    .build();
            log.debug("Configuration building finished. {}", config);
            return config;
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
