package cz.los.KL_Twitter.persistence.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {

    private static final String URL = "jdbc:h2:file:C:/Users/msch4/IdeaProjects/School/KL_Twitter/dataBase/new_db";
    private static final String TWITTER_URL = "jdbc:h2:file:C:/Users/msch4/IdeaProjects/School/KL_Twitter/dataBase/new_db;SCHEMA=TWITTER";
    private static final String TWITTER = "TWITTER";
    public static final String USER = "USER";
    public static final String TWEET = "TWEET";
    private static Logger log = LogManager.getLogger(DbUtils.class);

    private DbUtils() {
    }

    public static Connection getConnection() {
        return getConnection(TWITTER_URL);
    }

    public static Connection getConnection(String url) {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initDb();
    }

    public static void initDb() {
        log.info("Checking if DB is initiated...");
        if (checkDbIsInitiated()) {
            log.warn("Data base was already initiated!");
            return;
        }
        log.info("Data base was not yet initiated...");
        log.info("Initiating Data Base...");
        Connection con = getConnection(URL);
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate("CREATE SCHEMA TWITTER;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(con);
        con = getConnection();
        String query = parseQuery("src/main/resources/initDb.sql");
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!checkDbIsInitiated()) {
            throw new RuntimeException("Initialization of the DB Failed after an attempt!");
        }
        log.info("GOOD! New Database has been created!");
    }

    private static boolean checkDbIsInitiated() {
        if (!checkIfSchemaInitiated()) {
            return false;
        }
        return checkTablesExist();
    }

    private static boolean checkTablesExist() {
        Connection con = getConnection();
        try (ResultSet tables = con.getMetaData().getTables(null, TWITTER, null, null)) {
            List<String> tablesCatalog = new ArrayList<>();
            while (tables.next()) {
                String table = tables.getString(3);
                if (table.equals(USER) || table.equals(TWEET)) {
                    tablesCatalog.add(table);
                }
            }
            if (!tablesCatalog.contains(USER)) {
                throw new RuntimeException("Table USER does not exist in the schema!");
            }
            if (!tablesCatalog.contains(TWEET)) {
                throw new RuntimeException("Table TWEET does not exist in the schema!");
            }
            closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static boolean checkIfSchemaInitiated() {
        Connection con = getConnection(URL);
        try (ResultSet schemas = con.getMetaData().getSchemas()) {
            while (schemas.next()) {
                if (schemas.getString(1).equals(TWITTER)) {
                    return true;
                }
            }
            closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
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

}
