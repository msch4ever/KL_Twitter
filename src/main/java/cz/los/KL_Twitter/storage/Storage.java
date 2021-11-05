package cz.los.KL_Twitter.storage;

import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private final Map<String, UserAuthentication> authStorage = new HashMap<>();
    private final Map<Long, Session> sessionStorage = new HashMap<>();
    private final Map<Long, Tweet> tweetStorage = new HashMap<>();
    private final Map<Long, User> userStorage = new HashMap<>();
    private long sessionIdSequence = 0;
    private long tweetIdSequence = 0;
    private long userIdSequence = 0;

    private Storage() {
    }

    public Map<Long, User> getUserStorage() {
        return userStorage;
    }

    public Map<String, UserAuthentication> getAuthStorage() {
        return authStorage;
    }

    public Map<Long, Session> getSessionStorage() {
        return sessionStorage;
    }

    public Map<Long, Tweet> getTweetStorage() {
        return tweetStorage;
    }

    public long getNewUserIdSequence() {
        return ++userIdSequence;
    }

    public long getNewSessionIdSequence() {
        return ++sessionIdSequence;
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
