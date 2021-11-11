package cz.los.KL_Twitter.persistence.jdbc;

import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.persistence.TweetDao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TweetDaoJdbcImpl implements TweetDao {
    @Override
    public Long save(Tweet model) {
        return null;
    }

    @Override
    public Set<Tweet> getAll() {
        return null;
    }

    @Override
    public Optional<Tweet> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Tweet model) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Tweet> findTweetsFromFollowing(long userId) {
        return null;
    }

    @Override
    public long getLikesCount(Long id) {
        return 0;
    }

    @Override
    public long getReplyCount(Long id) {
        return 0;
    }
}
