package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.app.AppContext;
import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.model.Following;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.AuthService;
import cz.los.KL_Twitter.storage.Storage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DbUtils {

    private static final String TWITTER_DB_URL = "jdbc:sqlite:C:\\STORAGE\\kl_twitter\\twitter_DB";
    public static final String USER = "user";
    public static final String TWEET = "tweet";
    public static final String LIKES = "likes";
    public static final String FOLLOWERS = "followers";
    public static final String SESSION = "session";
    public static final String USER_AUTHENTICATION = "user_authentication";
    public static final String QUERY_DELIMITER = "--NEXT--";

    private static final List<String> TABLES =
            Arrays.asList(USER, TWEET, LIKES, FOLLOWERS, SESSION, USER_AUTHENTICATION);

    public static Connection getConnection() {
        return getConnection(TWITTER_DB_URL);
    }

    public static Connection getConnection(String url) {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initDb();
    }

    public static void initDb() {
        log.debug("Checking if DB is initiated...");
        if (checkDbIsInitiated()) {
            return;
        }
        log.debug("Data base was not yet initiated...");
        log.debug("Initiating Data Base...");
        Connection con = getConnection();
        String[] queries = parseQuery("src/main/resources/initDb.sql");
        runQuerySet(con, queries);
        if (!checkDbIsInitiated()) {
            throw new RuntimeException("Initialization of the DB Failed after an attempt!");
        }
        log.debug("GOOD! New Database has been created!");
    }

    private static boolean checkDbIsInitiated() {
        Map<String, Boolean> initiatedTables = TABLES.stream().collect(Collectors.toMap(Function.identity(), (value) -> false));
        boolean initiated = true;
        Connection con = getConnection();
        try (ResultSet tables = con.getMetaData().getTables(null, null, null, null)) {
            List<String> tablesCatalog = new ArrayList<>();
            while (tables.next()) {
                String table = tables.getString(3);
                if (TABLES.contains(table)) {
                    log.debug("Table '{}' was found..", table);
                    tablesCatalog.add(table);
                    initiatedTables.put(table, true);
                }
            }

            if (initiatedTables.values().stream().anyMatch(it -> !it)) {
                initiatedTables.entrySet().stream()
                        .filter(it -> !it.getValue())
                        .forEach(it -> log.warn("Table '{}' does not exist in the schema!", it.getKey()));
                initiated = false;
            }
            closeConnection(con);
        } catch (SQLException e) {
            initiated = false;
            e.printStackTrace();
        }
        if (initiated) {
            log.debug("Database has all needed Tables.");
        } else {
            log.debug("Database is not initiated.");
        }
        return initiated;
    }

    private static String[] parseQuery(String path) {
        String[] query = null;
        try {
            query = String.join(System.lineSeparator(), Files.readAllLines(Paths.get(path))).split(QUERY_DELIMITER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    public static void populateJDBC() {
        log.debug("Checking if DB is initiated...");
        if (!checkDbIsInitiated()) {
            throw new RuntimeException("The DB is not initialized!");
        }
        log.info("Population SQL Data Base with sandbox data...");
        Connection con = getConnection();
        String[] queries = parseQuery("src/main/resources/sandBoxDataPopulation.sql");
        runQuerySet(con, queries);
        log.debug("GOOD! Database has been populated with fake data!");
    }

    private static void runQuerySet(Connection con, String[] queries) {
        for (String query : queries) {
            try (Statement statement = con.createStatement()) {
                log.debug("Running query: [{}]", query);
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void populateInMem() {
        log.info("Population in memory Data Base with sandbox data...");
        AppContext context = ContextHolder.getAppContext();
        User rip = new User("rip212", LocalDate.of(1993, 5, 27), "Hi, i''m Andrew. JS evangelista");
        User msch4ever = new User("msch4ever", LocalDate.of(1991, 1, 7), "Hi, i''m Kostia. I love Java");
        User dardevil = new User("dardevil", LocalDate.of(1993, 6, 13), "Hi, i''m Olezhna. I love doing nothing and complain");
        User mkbhd = new User("mkbhd", LocalDate.of(1995, 11, 11), "Hi, i''m Marquess Brownlee. I love making tech videos");
        User boo = new User("boo88", LocalDate.of(1988, 4, 11), "Hi, i''m Dima. I love selling things");
        context.getUserDao().save(rip);
        context.getUserDao().save(msch4ever);
        context.getUserDao().save(dardevil);
        context.getUserDao().save(mkbhd);
        context.getUserDao().save(boo);

        context.getTweetDao().save(new Tweet(1L, null, "We will never forget this moment! America will never settle!"));
        context.getTweetDao().save(new Tweet(1L, null, "I was always wondering why it is so cool to be awesome!"));
        context.getTweetDao().save(new Tweet(2L, 1L, "Oh my god! What happened!?"));
        context.getTweetDao().save(new Tweet(2L, null, "Hi chat! Is Mayo an instrument?"));
        context.getTweetDao().save(new Tweet(3L, 3L, "They attacked US!"));
        context.getTweetDao().save(new Tweet(3L, null, "I just love COCK! Cant imagine my life without it!"));
        context.getTweetDao().save(new Tweet(4L, 1L, "Very Sad!"));
        context.getTweetDao().save(new Tweet(4L, 6L, "That's so terrible we can not edit tweets XD"));
        context.getTweetDao().save(new Tweet(5L, null, "Hi, That will be my first tweet!"));
        context.getTweetDao().save(new Tweet(1L, 4L, "No Kostia, mayo isn't and instrument!"));
        context.getTweetDao().save(new Tweet(1L, 8L, "Ahahahahha LOL!"));
        context.getTweetDao().save(new Tweet(4L, 10L, "@rip212 Why would you be so angry at mayo!"));

        AuthService authService = context.getAuthService();
        byte[] msch4everSalt = authService.generateSalt();
        context.getAuthDao()
                .save(new UserAuthentication(msch4ever.getUserId(), msch4ever.getLogin(), msch4everSalt, authService.encodePassword(msch4everSalt, "lol")));
        List<Following> followingStorage = Storage.getInstance().getFollowingStorage();
        followingStorage.add(new Following(msch4ever.getUserId(), rip.getUserId()));
        followingStorage.add(new Following(msch4ever.getUserId(), dardevil.getUserId()));
        followingStorage.add(new Following(msch4ever.getUserId(), mkbhd.getUserId()));
        followingStorage.add(new Following(msch4ever.getUserId(), boo.getUserId()));

        log.debug("GOOD! Database has been populated with fake data!");
    }
}
