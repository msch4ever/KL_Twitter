package cz.los.KL_Twitter.persistence.inMem;

import cz.los.KL_Twitter.model.Following;
import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.storage.Storage;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class TweetDaoInMemImpl implements TweetDao {

    private final Storage storage = Storage.getInstance();

    @Override
    public Long save(Tweet tweet) {
        long newTweetId = storage.getNewTweetIdSequence();
        tweet.setTweetId(newTweetId);
        tweet = createTweetState(tweet);
        storage.getTweetStorage().put(newTweetId, tweet);
        log.info("Tweet was persisted in the DB: {}", tweet);
        return newTweetId;
    }

    @Override
    public Optional<Tweet> findById(Long tweetId) {
        final Tweet persistedTweet = storage.getTweetStorage().get(tweetId);
        if (persistedTweet != null) {
            Tweet resultTweet = createTweetState(persistedTweet);
            return Optional.of(resultTweet);
        }
        log.warn("Could not find tweet by id:{}", tweetId);
        return Optional.empty();
    }

    @Override
    public Set<Tweet> getAll() {
        Set<Tweet> userList = new HashSet<>();
        for (final Tweet tweet : storage.getTweetStorage().values()) {
            userList.add(createTweetState(tweet));
        }
        log.debug("{} tweet were fetched from the DB.", userList.size());
        return userList;
    }

    @Override
    public void deleteById(Long tweetId) {
        Map<Long, Tweet> tweetStorage = storage.getTweetStorage();
        if (tweetStorage.containsKey(tweetId)) {
            tweetStorage.remove(tweetId);
            log.info("Tweet has been deleted. TweetId:{}", tweetId);
        } else {
            log.warn("Could not find tweet by id:{}", tweetId);
        }
    }

    /**
     * Editing tweets is not supported
     *
     * @throws UnsupportedOperationException - Editing tweets is not supported
     */
    @Override
    public void update(Tweet model) {
        throw new UnsupportedOperationException("Editing tweets is not supported");
    }

    @Override
    public List<Tweet> findTweetsFromFollowing(long userId) {
        Map<Long, Tweet> tweetStorage = storage.getTweetStorage();
        List<Long> followings = storage.getFollowingStorage().stream()
                .filter(it -> it.getUserId() == userId)
                .map(Following::getFollowingId)
                .collect(Collectors.toList());
        followings.add(userId);
        return tweetStorage.values().stream()
                .filter(it -> followings.contains(it.getUserId()))
                .limit(10)
                .sorted(Comparator.comparing(Tweet::getDatePosted))
                .collect(Collectors.toList());
    }

    @Override
    public long getLikesCount(Long id) {
        return storage.getLikeStorage().stream()
                .filter(it -> id.equals(it.getLikedTweetId()))
                .count();
    }

    @Override
    public long getReplyCount(Long id) {
        return storage.getTweetStorage().values().stream()
                .filter(it -> id.equals(it.getReferenceTweetId()))
                .count();
    }

    private Tweet createTweetState(Tweet tweetOriginal) {
        return new Tweet(tweetOriginal);
    }

}
