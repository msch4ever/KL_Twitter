package cz.los.KL_Twitter.persistence.jdbc;

import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.persistence.DbUtils;
import cz.los.KL_Twitter.persistence.UserDao;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
        @SneakyThrows
        public Optional<User> findById (Long id) {
            User user = null;
            Connection connection = DbUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE userId = " + id);
            while (rs.next()) {
                Long userId = rs.getLong("userId");
                String nickname = rs.getString("nickname");
                String login = rs.getString("login");
                LocalDateTime dateRegistered =
                        new Timestamp(rs.getDate("dateRegistered").getTime()).toLocalDateTime();
                LocalDate dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));
                String about = rs.getString("about");
                user = new User(userId, nickname, login, dateRegistered, dateOfBirth, about, new ArrayList<>(), new ArrayList<>());
            }
            if (user != null) {
                return Optional.of(user);
            }
            return Optional.empty();
        }

        @Override
        public void update(User model){

        }

        @Override
        public void deleteById (Long id){

        }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllByIdInList(List<Long> ids) {
        return null;
    }
}
