package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.Feed;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.model.User;
import cz.los.KL_Twitter.views.FeedView;

import java.util.List;
import java.util.Map;

public interface FeedService {

    Feed createFeed(FeedView.Mode mode, Long id);

    Map<Long, User> getFeedAuthors(List<Tweet> tweets);

}
