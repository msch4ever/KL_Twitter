package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.model.Tweet;

import java.util.List;

public interface TweetDao extends GenericDao<Tweet> {

    List<Tweet> findTweetsFromFollowing(long userId);

    long getLikesCount(Long id);

    long getReplyCount(Long id);

    List<Tweet> findTweetsByUserId(Long id);

    List<Tweet> findTweetReplies(Long id);
}
