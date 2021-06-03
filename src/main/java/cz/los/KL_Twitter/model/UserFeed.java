package cz.los.KL_Twitter.model;

import java.util.Set;

public class UserFeed extends Feed {

    private Long userId;
    private boolean homeFeed;

    public UserFeed() {}

    public UserFeed(Set<Tweet> tweets, Long userId, boolean homeFeed) {
        super(tweets);
        this.userId = userId;
        this.homeFeed = homeFeed;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isHomeFeed() {
        return homeFeed;
    }

    public void setHomeFeed(boolean homeFeed) {
        this.homeFeed = homeFeed;
    }

    @Override
    public String toString() {
        return "UserFeed{" +
                "userId=" + userId +
                ", homeFeed=" + homeFeed +
                "} " + super.toString();
    }
}
