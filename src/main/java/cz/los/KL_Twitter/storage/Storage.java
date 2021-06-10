package cz.los.KL_Twitter.storage;

import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Storage {

    private static final Map<Long, Tweet> tweetStorage = new HashMap<>();
    private static final Map<Long, User> userStorage = new HashMap<>();

    private static long tweetSequence = 0;
    private static long userSequence = 0;

    private Storage() { }

    public static Set<User> getUsers() {
        Set<User> result = new HashSet<>();
        for (User user : userStorage.values()) {
            User userCopy = createUserCopy(user);
            if (userCopy.getId() != null) {
                result.add(userCopy);
            }
        }
        return result;
    }

    private static User createUserCopy(User original) {
        if (original != null) {
            User copy = new User();
            copy.setDateRegistered(original.getDateRegistered());
            copy.setDateOfBirth(original.getDateOfBirth());
            copy.setFollowers(original.getFollowers());
            copy.setFollowing(original.getFollowing());
            copy.setNickname(original.getNickname());
            copy.setAbout(original.getAbout());
            copy.setLogin(original.getLogin());
            copy.setId(original.getId());
            return copy;
        }
        return null;
    }

    public static Set<Tweet> getTweets() {
        Set<Tweet> result = new HashSet<>();
        for (Tweet tweet : tweetStorage.values()) {
            Tweet tweetCopy = createTweetCopy(tweet);
            if (tweetCopy.getId() != null) {
                result.add(tweetCopy);
            }
        }
        return result;
    }

    private static Tweet createTweetCopy(Tweet original) {
        Tweet copy = new Tweet();
        if (original != null) {
            copy.setId(original.getId());
            copy.setContent(original.getContent());
            copy.setDatePosted(original.getDatePosted());
            copy.setReferenceTweetId(original.getReferenceTweetId());
            copy.setLikes(original.getLikes());
            copy.setUser(original.getUser());
            copy.setRetweets(original.getRetweets());
            return copy;
        }
        return copy;
    }

    public static User getUserByKey(Long key) {
        return createUserCopy(userStorage.get(key));
    }

    public static Tweet getTweetByKey(Long key) {
        return createTweetCopy(tweetStorage.get(key));
    }

    public static Long putUserInStorage(User user) {
        User toBeSaved = createUserCopy(user);
        if (toBeSaved.getId() == null) {
            toBeSaved.setId(++userSequence);
            user.setId(toBeSaved.getId());
        }
        userStorage.put(toBeSaved.getId(), toBeSaved);
        return toBeSaved.getId();
    }

    public static Long putTweetInStorage(Tweet tweet) {
        Tweet toBeSaved = createTweetCopy(tweet);
        if (toBeSaved.getId() == null) {
            toBeSaved.setId(++tweetSequence);
            tweet.setId(toBeSaved.getId());
        }
        tweetStorage.put(toBeSaved.getId(), toBeSaved);
        return toBeSaved.getId();
    }

    public static void removeUserByKey(Long key) {
        userStorage.remove(key);
    }

    public static void removeTweetByKey(Long key) {
        tweetStorage.remove(key);
    }

}