package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.auth.UserAuthentication;

public interface AuthDao {

    void save(UserAuthentication authentication);

    UserAuthentication findByLogin(String login);

    void delete(String login);

    void update(UserAuthentication authentication);

}
