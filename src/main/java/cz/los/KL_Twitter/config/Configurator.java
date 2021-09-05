package cz.los.KL_Twitter.config;

import cz.los.KL_Twitter.app.App;
import cz.los.KL_Twitter.app.AppContext;
import cz.los.KL_Twitter.persistence.factory.DaoAbstractFactory;
import cz.los.KL_Twitter.persistence.jdbc.DbUtils;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cz.los.KL_Twitter.config.DaoType.JDBC;

public class Configurator {

    private static Logger log = LogManager.getLogger(Configurator.class);

    public static final String DAO_TYPE = "daoType";
    public static final String INIT_DB = "initDb";
    public static final String POPULATE_DB = "populateDb";

    public static void initApp(String[] args) {
        Configuration config = createConfig(args);
        AppContext.AppContextBuilder builder = AppContext.builder();

        DaoAbstractFactory factory = new DaoAbstractFactory();

        builder.configuration(config);
        builder.userDao(factory.createUserDao(config.getDaoType()));
        builder.tweetDao(factory.createTweetDao(config.getDaoType()));

        initDab(config);

        //ToDo: create all handlers and put into AppContext;

        System.out.println(config);
    }

    private static void initDab(Configuration config) {
        if (config.initDb()) {
            if (config.getDaoType() == JDBC) {
                DbUtils.initDb();
            }
        }
        if (config.populateDb()) {
            // populate db
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
            return config;
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
