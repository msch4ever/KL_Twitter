package cz.los.KL_Twitter.persistence.inMem;

import cz.los.KL_Twitter.model.Tweet;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.storage.Storage;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class TweetDaoInMemImpl implements TweetDao {

    private final Storage storage = Storage.getInstance();

    @Override
    public Long save(Tweet tweet) {
        long newTweetId = storage.getNewTweetIdSequence();
        tweet = createTweetState(tweet);
        tweet.setTweetId(newTweetId);
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
        Map<Long, Tweet> userStorage = storage.getTweetStorage();
        if (userStorage.containsKey(tweetId)) {
            userStorage.remove(tweetId);
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

    private Tweet createTweetState(Tweet tweetOriginal) {
        return new Tweet(tweetOriginal);
    }

}
