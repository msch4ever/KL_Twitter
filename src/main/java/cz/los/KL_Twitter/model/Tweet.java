package cz.los.KL_Twitter.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Tweet implements PersistenceEntity {

    private Long tweetId;
    private Long userId;
    private Long referenceTweetId;
    private final LocalDateTime datePosted;
    private final String content;
    private List<User> mentionedUsers;
    private List<User> likes;
    private List<Long> retweets;

    public Tweet(Long userId, Long referenceTweet, String content) {
        this.userId = userId;
        this.referenceTweetId = referenceTweet;
        this.datePosted = LocalDateTime.now();
        this.content = content;
        this.mentionedUsers = parseContentForMentions(content);
        this.likes = new ArrayList<>();
        this.retweets = new ArrayList<>();
    }

    public Tweet(Tweet other) {
        this.tweetId = other.getTweetId();
        this.userId = other.getUserId();
        this.referenceTweetId = other.getReferenceTweetId();
        this.datePosted = other.getDatePosted();
        this.content = other.getContent();
        this.mentionedUsers = other.getMentionedUsers();
        this.likes = other.getLikes();
        this.retweets = other.getRetweets();
    }

    private List<User> parseContentForMentions(String content) {
        //System.out.println("we'll get to it later");
        return Collections.emptyList();
    }

    @Override
    public void setId(Long id) {
        this.tweetId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (tweetId != null ? !tweetId.equals(tweet.tweetId) : tweet.tweetId != null) return false;
        if (!userId.equals(tweet.userId)) return false;
        if (referenceTweetId != null ? !referenceTweetId.equals(tweet.referenceTweetId) : tweet.referenceTweetId != null)
            return false;
        if (!datePosted.equals(tweet.datePosted)) return false;
        return content != null ? content.equals(tweet.content) : tweet.content == null;
    }

    @Override
    public int hashCode() {
        int result = tweetId != null ? tweetId.hashCode() : 0;
        result = 31 * result + userId.hashCode();
        result = 31 * result + (referenceTweetId != null ? referenceTweetId.hashCode() : 0);
        result = 31 * result + datePosted.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", userId=" + userId +
                ", referenceTweet=" + referenceTweetId +
                ", content='" + content + '\'' +
                '}';
    }
}
