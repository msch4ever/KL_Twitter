package cz.los.KL_Twitter.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class Tweet {

    private Long tweetId;
    private User user;
    private Long referenceTweetId;
    private LocalDateTime datePosted;
    private String content;
    private Set<User> mentionedUsers;
    private Set<User> likes;
    private Set<Tweet> retweets;

    public Tweet() {
    }

    public Tweet(User user, String content, Set<User> mentionedUsers) {
        this.user = user;
        this.content = content;
        this.mentionedUsers = mentionedUsers;
    }

    public Tweet(Long tweetId, User user, Long referenceTweetId, LocalDateTime datePosted, String content,
                 Set<User> mentionedUsers, Set<User> likes, Set<Tweet> retweets) {
        this.tweetId = tweetId;
        this.user = user;
        this.referenceTweetId = referenceTweetId;
        this.datePosted = datePosted;
        this.content = content;
        this.mentionedUsers = mentionedUsers;
        this.likes = likes;
        this.retweets = retweets;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getReferenceTweetId() {
        return referenceTweetId;
    }

    public void setReferenceTweetId(Long referenceTweetId) {
        this.referenceTweetId = referenceTweetId;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<User> getMentionedUsers() {
        return mentionedUsers;
    }

    private void setMentionedUsers(Set<User> mentionedUsers) {
        this.mentionedUsers = mentionedUsers;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Set<Tweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(Set<Tweet> retweets) {
        this.retweets = retweets;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", user=" + user +
                ", referenceTweetId=" + referenceTweetId +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tweet tweet = (Tweet) o;

        if (!Objects.equals(tweetId, tweet.tweetId)) {
            return false;
        }
        if (!user.equals(tweet.user)) {
            return false;
        }
        if (!Objects.equals(referenceTweetId, tweet.referenceTweetId)) {
            return false;
        }
        return content.equals(tweet.content);
    }

    @Override
    public int hashCode() {
        int result = tweetId != null ? tweetId.hashCode() : 0;
        result = 31 * result + user.hashCode();
        result = 31 * result + (referenceTweetId != null ? referenceTweetId.hashCode() : 0);
        result = 31 * result + content.hashCode();
        return result;
    }
}
