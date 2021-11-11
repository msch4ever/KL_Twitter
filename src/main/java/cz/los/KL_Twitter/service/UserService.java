package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(String login, String nickName);

    Optional<User> findByLogin(String login);

    List<User> findAllByIdInList(List<Long> collect);
}
