package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(String login, String nickName);

    Optional<User> findByLogin(String login);

    Optional<User> findById(Long id);

    List<User> findAllByIdInList(List<Long> collect);

    int countFollowers(Long id);

    int countFollowing(Long id);

    boolean userIsFollowingOther(Long first, Long second);

    void follow(Long first, Long second);

    void unfollow(Long first, Long second);
}
