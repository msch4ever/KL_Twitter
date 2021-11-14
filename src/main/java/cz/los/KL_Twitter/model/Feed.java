package cz.los.KL_Twitter.model;

import java.util.List;
import java.util.Map;

public abstract class Feed {

    private List<Tweet> tweets;
    private Map<Long, User> authors;

    public Feed() {}

    public Feed(List<Tweet> tweets, Map<Long, User> authors) {
        this.tweets = tweets;
        this.authors = authors;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Map<Long, User> getAuthors() {
        return authors;
    }

    public void setAuthors(Map<Long, User> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "tweets=" + tweets +
                '}';
    }
}
