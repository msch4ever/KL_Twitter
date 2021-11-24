package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findByLogin(String login);

    List<User> findAllByIdInList(List<Long> ids);

    int countFollowers(Long id);

    int countFollowing(Long id);

    boolean userIsFollowingOther(Long first, Long second);

    void follow(Long first, Long second);

    void unfollow(Long first, Long second);

}
