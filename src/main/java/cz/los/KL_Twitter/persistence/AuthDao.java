package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.auth.UserAuthentication;

import java.util.Optional;

public interface AuthDao extends GenericDao<UserAuthentication> {

    Long save(UserAuthentication authentication);

    Optional<UserAuthentication> findByLogin(String login);

    void delete(String login);

    void update(UserAuthentication authentication);

}
