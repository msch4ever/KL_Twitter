package cz.los.KL_Twitter.config;

import cz.los.KL_Twitter.app.AppContext;
import cz.los.KL_Twitter.app.AppContextHolder;
import cz.los.KL_Twitter.app.SecurityContext;
import cz.los.KL_Twitter.handler.ClosingHandler;
import cz.los.KL_Twitter.handler.DispatcherHandler;
import cz.los.KL_Twitter.handler.global.ExitHandler;
import cz.los.KL_Twitter.handler.global.HelpHandler;
import cz.los.KL_Twitter.handler.user.CreateUserHandler;
import cz.los.KL_Twitter.persistence.*;
import cz.los.KL_Twitter.persistence.factory.DaoAbstractFactory;
import cz.los.KL_Twitter.persistence.factory.DaoFactoryException;
import cz.los.KL_Twitter.service.AuthService;
import cz.los.KL_Twitter.service.AuthServiceImpl;
import cz.los.KL_Twitter.service.UserService;
import cz.los.KL_Twitter.service.UserServiceImpl;
import cz.los.KL_Twitter.views.FeedView;
import cz.los.KL_Twitter.views.WelcomeView;
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
        DaoAbstractFactory factory = new DaoAbstractFactory(config.getDaoType());
        AppContext.AppContextBuilder builder = AppContext.builder();
        builder.configuration(config);
        initDaos(factory, builder);
        initServices(builder);
        initHandlers(builder);
        initViews(builder);
        AppContextHolder.initAppContext(builder.build());
        AppContextHolder.initSecurityContext(new SecurityContext());
        log.debug("Application context created. {}", AppContextHolder.getAppContext());
        log.info("Preparing database according to configuration..");
        initDB(config);
        log.info("Database prepared.");
        long end = System.currentTimeMillis();
        log.info("Application initialized successfully in {} seconds!", (end - start) / 1000.0D);
    }

    private static void initDaos(DaoAbstractFactory factory, AppContext.AppContextBuilder builder) {
        log.debug("Creating DAOs..");
        SessionDao sessionDao = factory.createSessionDao();
        TweetDao tweetDao = factory.createTweetDao();
        UserDao userDao = factory.createUserDao();
        AuthDao authDao = factory.createAuthDao();

        log.debug("Finalizing DAOs in AppContext..");
        builder.sessionDao(sessionDao);
        builder.tweetDao(tweetDao);
        builder.userDao(userDao);
        builder.authDao(authDao);

        log.debug("DAOs initialized!");
    }

    private static void initServices(AppContext.AppContextBuilder builder) {
        log.debug("Creating services..");
        AuthService authService = new AuthServiceImpl(builder.getSessionDao(), builder.getAuthDao());
        UserService userService = new UserServiceImpl(authService, builder.getUserDao());

        log.debug("Finalizing services in AppContext..");
        builder.authService(authService);
        builder.userService(userService);

        log.debug("Services initialized!");
    }

    private static void initHandlers(AppContext.AppContextBuilder builder) {
        log.debug("Creating handlers..");
        HelpHandler helpHandler = new HelpHandler();
        ExitHandler exitHandler = new ExitHandler();
        ClosingHandler closingHandler = new ClosingHandler();
        CreateUserHandler createUserHandler = new CreateUserHandler(builder.getUserService());

        log.debug("Establishing Chain of Responsibility..");
        helpHandler.setNextHandler(createUserHandler);
        createUserHandler.setNextHandler(exitHandler);
        exitHandler.setNextHandler(closingHandler);
        DispatcherHandler dispatcherHandler = new DispatcherHandler(helpHandler);

        log.debug("Finalizing handlers in AppContext..");
        builder.dispatcherHandler(dispatcherHandler);
        builder.createUserHandler(createUserHandler);
        builder.helpHandler(helpHandler);
        builder.exitHandler(exitHandler);
        builder.closingHandler(closingHandler);

        log.debug("Handlers initialized!");
    }

    private static void initViews(AppContext.AppContextBuilder builder) {
        builder.welcomeView(new WelcomeView());
        builder.feedView(new FeedView());
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
