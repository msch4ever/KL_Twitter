package cz.los.KL_Twitter.model;

import java.util.List;
import java.util.Map;

public class TweetFeed extends Feed {

    private Long tweetId;

    public TweetFeed() {
    }

    public TweetFeed(List<Tweet> tweets, Map<Long, User> authors, Long tweetId) {
        super(tweets, authors);
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
