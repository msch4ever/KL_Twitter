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

    private static final String getLastId = "SELECT MAX(userId) FROM TWITTER_USER";

    @Override
    public Long save(User model) {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement statement1 = null;
        Statement statement2 = null;
        ResultSet rs = null;
        Long resultId = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "INSERT INTO TWITTER_USER (login\n" +
                    "                , nickname\n" +
                    "                , about)\n" +
                    "VALUES  (?, ?, ?)";
            statement1 = conn.prepareStatement(sql);
            statement1.setString(1, model.getLogin());
            statement1.setString(2, model.getNickname());
            statement1.setString(3, model.getAbout());
            int result = statement1.executeUpdate();
            System.out.println(result);
            if (result == 1) {
                statement2 = conn.createStatement();
                rs = statement2.executeQuery(getLastId);
                if (rs.next()) {
                    resultId = rs.getLong(1);
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

            return resultId;
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
            ResultSet rs = statement.executeQuery("SELECT * FROM TWITTER_USER WHERE userId = " + id);
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
    @SneakyThrows
    public Optional<User> findByLogin(String login) {
        User user = null;
        Connection connection = DbUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE login = '" + login + "'");
        while (rs.next()) {
            Long userId = rs.getLong("userId");
            String nick = rs.getString("nickname");
            String log_in = rs.getString("login");
            LocalDateTime dateRegistered =
                    new Timestamp(rs.getDate("dateRegistered").getTime()).toLocalDateTime();
            LocalDate dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));
            String about = rs.getString("about");
            user = new User(userId, nick, log_in, dateRegistered, dateOfBirth, about, new ArrayList<>(), new ArrayList<>());
        }
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    @SneakyThrows
    public Optional<User> findByNickname(String nickname) {
        User user = null;
        Connection connection = DbUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE nickname = '" + nickname + "'");
        while (rs.next()) {
            Long userId = rs.getLong("userId");
            String nick = rs.getString("nickname");
            String login = rs.getString("login");
            LocalDateTime dateRegistered =
                    new Timestamp(rs.getDate("dateRegistered").getTime()).toLocalDateTime();
            LocalDate dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));
            String about = rs.getString("about");
            user = new User(userId, nick, login, dateRegistered, dateOfBirth, about, new ArrayList<>(), new ArrayList<>());
        }
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllByIdInList(List<Long> ids) {
        return null;
    }

    @Override
    public int countFollowers(Long id) {
        return 0;
    }

    @Override
    public int countFollowing(Long id) {
        return 0;
    }

    @Override
    public boolean userIsFollowingOther(Long first, Long second) {
        return false;
    }

    @Override
    public void follow(Long first, Long second) {

    }

    @Override
    public void unfollow(Long first, Long second) {

    }
}
