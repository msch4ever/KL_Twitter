package cz.los.KL_Twitter.model;

import java.util.Set;

public class TweetFeed extends Feed {

    private Long tweetId;

    public TweetFeed() {
    }

    public TweetFeed(Set<Tweet> tweets, Long tweetId) {
        super(tweets);
        this.tweetId = tweetId;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    @Override
    public String toString() {
        return "TweetFeed{" +
                "tweetId=" + tweetId +
                "} " + super.toString();
    }
}
