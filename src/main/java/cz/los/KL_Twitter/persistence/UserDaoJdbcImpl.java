package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.model.User;

import java.sql.*;
import java.util.Optional;
import java.util.Set;

public class UserDaoJdbcImpl implements UserDao {

    private static final String getLastId = "SELECT MAX(userId) FROM USER";

    @Override
    public Long save(User model) {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String dbUrl = "jdbc:h2:file:C:/Users/msch4/IdeaProjects/School/KL_Twitter/dataBase/new_db";

        Connection conn = null;
        PreparedStatement statement1 = null;
        Statement statement2 = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            String sql = "INSERT INTO USER (login\n" +
                    "                , nickname\n" +
                    "                , about)\n" +
                    "VALUES  (?, ?, ?)";
            statement1 = conn.prepareStatement(sql);
            statement1.setString(1, model.getLogin());
            int result = statement1.executeUpdate(sql);
            System.out.println(result);
            if (result == 1) {
                statement2 = conn.createStatement();
                rs = statement2.executeQuery(getLastId);
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                statement1.close();
                statement2.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return null;
        }
    }

        @Override
        public Set<User> getAll () {
            return null;
        }

        @Override
        public Optional<User> findById (Long id){
            return Optional.empty();
        }

        @Override
        public void updateById (Long id, User model){

        }

        @Override
        public void deleteById (Long id){

        }
    }
