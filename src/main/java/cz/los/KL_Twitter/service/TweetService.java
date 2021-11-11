package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.Tweet;

import java.util.List;

public interface TweetService {

    List<Tweet> findTweetsFromFollowing(long userId);

}
