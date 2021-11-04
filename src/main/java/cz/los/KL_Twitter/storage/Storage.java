package cz.los.KL_Twitter.storage;

import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private final Map<Long, User> userStorage = new HashMap<>();
    private final Map<String, UserAuthentication> authStorage = new HashMap<>();
    private final Map<Long, Tweet> tweetStorage = new HashMap<>();
    private long userIdSequence = 0;
    private long tweetIdSequence = 0;

    private Storage() {
    }

    public Map<Long, User> getUserStorage() {
        return userStorage;
    }

    public Map<String, UserAuthentication> getAuthStorage() {
        return authStorage;
    }

    public Map<Long, Tweet> getTweetStorage() {
        return tweetStorage;
    }

    public long getNewUserIdSequence() {
        return ++userIdSequence;
    }

    public long getNewTweetIdSequence() {
        return ++tweetIdSequence;
    }

    public static Storage getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final Storage instance = new Storage();
    }
}
