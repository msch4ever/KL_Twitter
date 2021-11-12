package cz.los.KL_Twitter.storage;

import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.auth.UserAuthentication;
import cz.los.KL_Twitter.model.Following;
import cz.los.KL_Twitter.model.Like;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    private final Map<String, UserAuthentication> authStorage = new HashMap<>();
    private final Map<Long, Session> sessionStorage = new HashMap<>();
    private final Map<Long, Tweet> tweetStorage = new HashMap<>();
    private final Map<Long, User> userStorage = new HashMap<>();
    private final List<Following> followingStorage = new ArrayList<>();
    private final List<Like> likeStorage = new ArrayList<>();
    private long sessionIdSequence = 0;
    private long tweetIdSequence = 0;
    private long authIdSequence = 0;
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

    public List<Following> getFollowingStorage() {
        return followingStorage;
    }

    public List<Like> getLikeStorage() {
        return likeStorage;
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

    public long getAuthIdSequence() {
        return ++authIdSequence;
    }

    public static Storage getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final Storage instance = new Storage();
    }
}
