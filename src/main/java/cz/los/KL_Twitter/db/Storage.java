package cz.los.KL_Twitter.db;

import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Storage {

    private static final Map<Long, User> userStorage = new HashMap<>();
    private static final Map<Long, Tweet> tweetStorage = new HashMap<>();

    private static long userSequence = 0;
    private static long tweetSequence = 0;


    public static Set<User> getUsers() {
        return userStorage.values().stream().map(Storage::createUserCopy).collect(Collectors.toSet());
    }

    public static Set<Tweet> getTweets() {
        return tweetStorage.values().stream().map(Storage::createTweetCopy).collect(Collectors.toSet());
    }

    public static Long putUserToStorage(User user) {
        User persisted = createUserCopy(user);
        if (persisted.getUserId() == null) {
            persisted.setUserId(++userSequence);
        }
        userStorage.put(persisted.getUserId(), persisted);
        return persisted.getUserId();
    }

    public static Long putTweetToStorage(Tweet tweet) {
        Tweet persisted = createTweetCopy(tweet);
        if (persisted.getTweetId() == null) {
            persisted.setTweetId(++tweetSequence);
        }
        tweetStorage.put(persisted.getTweetId(), persisted);
        return persisted.getTweetId();
    }

    public static User getUserById(long id) {
        User storedUser = userStorage.get(id);
        return createUserCopy(storedUser);
    }

    private static User createUserCopy(User originalUser) {
        User copy = new User();

        copy.setUserId(originalUser.getUserId());
        copy.setLogin(originalUser.getLogin());
        copy.setNickname(originalUser.getNickname());
        copy.setAbout(originalUser.getAbout());
        copy.setDateRegistered(originalUser.getDateRegistered());
        copy.setDateOfBirth(originalUser.getDateOfBirth());
        copy.setFollowers(originalUser.getFollowers());
        copy.setFollowing(originalUser.getFollowing());

        return copy;
    }

    public static Tweet getTweetById(long id) {
        Tweet storedTweet = tweetStorage.get(id);
        return createTweetCopy(storedTweet);
    }

    private static Tweet createTweetCopy(Tweet originalTweet) {
        Tweet copy = new Tweet();

        copy.setTweetId(originalTweet.getTweetId());
        copy.setUser(originalTweet.getUser());
        copy.setReferenceTweetId(originalTweet.getReferenceTweetId());
        copy.setContent(originalTweet.getContent());
        copy.setDatePosted(originalTweet.getDatePosted());
        copy.setLikes(originalTweet.getLikes());
        copy.setRetweets(originalTweet.getRetweets());

        return copy;
    }

    public static void deleteUser(Long id) {
        userStorage.remove(id);
    }

    public static void deleteTweet(Long id) {
        tweetStorage.remove(id);
    }

}
