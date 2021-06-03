package cz.los.KL_Twitter.model;

import java.util.Set;

public abstract class Feed {

    private Set<Tweet> tweets;

    public Feed() {}

    public Feed(Set<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "tweets=" + tweets +
                '}';
    }
}
