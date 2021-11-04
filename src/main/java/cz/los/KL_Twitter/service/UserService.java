package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.model.User;

public interface UserService {

    User createUser(String login, String nickName);

}
