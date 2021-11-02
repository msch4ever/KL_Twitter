package cz.los.KL_Twitter.persistence.jdbc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DbUtils {

    private static final String TWITTER_DB_URL = "jdbc:sqlite:C:\\STORAGE\\kl_twitter\\twitter_DB";
    public static final String USER = "user";
    public static final String TWEET = "tweet";
    public static final String LIKES = "likes";
    public static final String FOLLOWERS = "followers";

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
            log.warn("Data base was already initiated!");
            return;
        }
        log.debug("Data base was not yet initiated...");
        log.debug("Initiating Data Base...");
        Connection con = getConnection();
        String query = parseQuery("src/main/resources/initDb.sql");
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!checkDbIsInitiated()) {
            throw new RuntimeException("Initialization of the DB Failed after an attempt!");
        }
        log.debug("GOOD! New Database has been created!");
    }

    private static boolean checkDbIsInitiated() {
        boolean initiated = true;
        Connection con = getConnection();
        try (ResultSet tables = con.getMetaData().getTables(null, null, null, null)) {
            List<String> tablesCatalog = new ArrayList<>();
            while (tables.next()) {
                String table = tables.getString(3);
                if (table.equals(USER) || table.equals(TWEET) || table.equals(LIKES) || table.equals(FOLLOWERS)) {
                    log.debug("Table '{}' was found..", table);
                    tablesCatalog.add(table);
                }
            }
            if (!tablesCatalog.contains(USER)) {
                initiated = false;
                log.warn("Table '{}' does not exist in the schema!", USER);
            }
            if (!tablesCatalog.contains(TWEET)) {
                initiated = false;
                log.warn("Table '{}' does not exist in the schema!", TWEET);
            }
            if (!tablesCatalog.contains(LIKES)) {
                initiated = false;
                log.warn("Table '{}' does not exist in the schema!", LIKES);
            }
            if (!tablesCatalog.contains(FOLLOWERS)) {
                initiated = false;
                log.warn("Table '{}' does not exist in the schema!", FOLLOWERS);
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

    private static String parseQuery(String path) {
        String query = null;
        try {
            query = String.join("", Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    public static void populateJDBC() {

    }

    public static void populateInMem() {

    }
}
