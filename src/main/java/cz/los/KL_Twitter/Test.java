package cz.los.KL_Twitter;

import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.model.UserFeed;
import cz.los.KL_Twitter.persistence.UserDao;
import cz.los.KL_Twitter.persistence.UserDaoInMemImpl;
import cz.los.KL_Twitter.persistence.UserDaoJdbcImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        User user1 = new User("Will69420", "RealWill");
        user1.setId(1L);

        User user2 = new User("Bill6000", "RealBill");
        user2.setId(2L);

        Tweet tweet1 = new Tweet(user1, "Hello World", null);
        Tweet tweet2 = new Tweet(user2, "Hello World @Will69420", new HashSet<>(Arrays.asList(user1)));

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(tweet1);
        System.out.println(tweet2);

        UserFeed user1Feed = new UserFeed(new HashSet<>(Arrays.asList(tweet1, tweet2)), user1.getId(), false);
        UserFeed user2Feed = new UserFeed(new HashSet<>(Arrays.asList(tweet2)), user2.getId(), false);

        System.out.println(user1Feed);
        System.out.println(user2Feed);

        ////////////////////////////////////////////////////////////////////////
        UserDao userDao = new UserDaoJdbcImpl();
        ////////////////////////////////////////////////////////////////////////
        userDao.save(user1);
        userDao.save(user2);

        /*Set<User> users = userDao.getAll();

        User user2FromDB = userDao.findById(user2.getId()).orElseThrow(NullPointerException::new);

        User user2FromMain = new User("Bill6000", "HonestlyRealBill");

        userDao.updateById(user2.getId(), user2FromMain);

        userDao.deleteById(user1.getId());

        System.out.println("lol");*/

    }

}
