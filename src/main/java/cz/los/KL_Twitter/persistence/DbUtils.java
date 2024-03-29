package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.app.AppContext;
import cz.los.KL_Twitter.app.ContextHolder;
import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.model.Following;
import cz.los.KL_Twitter.model.Like;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.service.AuthService;
import cz.los.KL_Twitter.storage.Storage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DbUtils {

    //private static final String TWITTER_DB_URL = "jdbc:sqlite:C:\\STORAGE\\kl_twitter\\twitter_DB";
    private static final String TWITTER_DB_URL = "jdbc:h2:file:C:/DEVELOPMENT/REPO/KL_Twitter/dataBase/new_db";
    public static final String USER = "TWITTER_USER";
    public static final String TWEET = "TWEET";
    public static final String LIKES = "LIKES";
    public static final String FOLLOWERS = "FOLLOWERS";
    public static final String SESSION = "SESSION";
    public static final String USER_AUTHENTICATION = "USER_AUTHENTICATION";
    public static final String QUERY_DELIMITER = "--NEXT--";

    private static final List<String> TABLES =
            Arrays.asList(USER, TWEET, LIKES, FOLLOWERS, SESSION, USER_AUTHENTICATION);

    public static Connection getConnection() {
        return getConnection(TWITTER_DB_URL);
    }

    @SneakyThrows
    public static void closeResource(AutoCloseable closeable) {
        Objects.requireNonNull(closeable);
        closeable.close();
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
            while (tables.next()) {
                String table = tables.getString(3);
                if (TABLES.contains(table)) {
                    log.debug("Table '{}' was found..", table);
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

        Tweet tweet1 = new Tweet(1L, null, "We will never forget this moment! America will never settle!");
        Tweet tweet2 = new Tweet(1L, null, "I was always wondering why it is so cool to be awesome!");
        Tweet tweet3 = new Tweet(2L, 1L, "Oh my god! What happened!?");
        Tweet tweet4 = new Tweet(2L, null, "Hi chat! Is Mayo an instrument?");
        Tweet tweet5 = new Tweet(3L, 3L, "They attacked US!");
        Tweet tweet6 = new Tweet(3L, null, "I just love COCK! Cant imagine my life without it!");
        Tweet tweet7 = new Tweet(4L, 1L, "Very Sad!");
        Tweet tweet8 = new Tweet(4L, 6L, "That's so terrible we can not edit tweets XD");
        Tweet tweet9 = new Tweet(5L, null, "Hi, That will be my first tweet!");
        Tweet tweet10 = new Tweet(1L, 4L, "No Kostia, mayo isn't an instrument!");
        Tweet tweet11 = new Tweet(1L, 8L, "Ahahahahha LOL!");
        Tweet tweet12 = new Tweet(4L, 10L, "@rip212 Why would you be so angry at mayo!");
        context.getTweetDao().save(tweet1);
        context.getTweetDao().save(tweet2);
        context.getTweetDao().save(tweet3);
        context.getTweetDao().save(tweet4);
        context.getTweetDao().save(tweet5);
        context.getTweetDao().save(tweet6);
        context.getTweetDao().save(tweet7);
        context.getTweetDao().save(tweet8);
        context.getTweetDao().save(tweet9);
        context.getTweetDao().save(tweet10);
        context.getTweetDao().save(tweet11);
        context.getTweetDao().save(tweet12);
        AuthService authService = context.getAuthService();
        List<Following> followingStorage = Storage.getInstance().getFollowingStorage();

        createSubs(context, msch4ever, Arrays.asList(rip, dardevil, mkbhd, boo), authService, followingStorage);
        createSubs(context, rip, Arrays.asList(msch4ever, dardevil, mkbhd, boo), authService, followingStorage);
        createSubs(context, dardevil, Arrays.asList(msch4ever, rip), authService, followingStorage);
        createSubs(context, mkbhd, Arrays.asList(msch4ever, rip), authService, followingStorage);
        createSubs(context, boo, Arrays.asList(msch4ever, rip, dardevil), authService, followingStorage);

        List<Like> likeStorage = Storage.getInstance().getLikeStorage();
        createLikes(tweet1, Arrays.asList(msch4ever, dardevil, boo), likeStorage);
        createLikes(tweet2, Arrays.asList(msch4ever, rip), likeStorage);
        createLikes(tweet4, Arrays.asList(rip, dardevil, mkbhd, boo), likeStorage);
        createLikes(tweet6, Arrays.asList(rip, msch4ever, boo), likeStorage);
        createLikes(tweet8, Arrays.asList(rip, msch4ever, boo), likeStorage);
        createLikes(tweet11, Arrays.asList(mkbhd), likeStorage);
        createLikes(tweet12, Arrays.asList(msch4ever, rip, boo), likeStorage);

        log.debug("GOOD! Database has been populated with fake data!");
    }

    private static void createSubs(AppContext context, User user, List<User> users, AuthService authService, List<Following> followingStorage) {
        byte[] ripSalt = authService.generateSalt();
        context.getAuthDao()
                .save(new UserAuthentication(user.getUserId(), user.getLogin(), ripSalt, authService.encodePassword(ripSalt, "lol")));
        for (User sub : users) {
            followingStorage.add(new Following(user.getUserId(), sub.getUserId()));
        }
    }

    private static void createLikes(Tweet tweet1, List<User> liked, List<Like> likeStorage) {
        for (User user : liked) {
            likeStorage.add(new Like(user.getUserId(), tweet1.getTweetId()));
        }
    }
}
