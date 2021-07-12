package cz.los.KL_Twitter.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    private static final String URL = "jdbc:h2:file:C:/Users/msch4/IdeaProjects/School/KL_Twitter/dataBase/new_db";

    public DbUtils() {}

    public void initDriver(String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
