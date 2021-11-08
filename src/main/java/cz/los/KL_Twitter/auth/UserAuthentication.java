package cz.los.KL_Twitter.auth;

import cz.los.KL_Twitter.model.PersistenceEntity;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@RequiredArgsConstructor
public class UserAuthentication implements PersistenceEntity {

    @Setter
    @NonFinal
    Long id;
    String login;
    byte[] salt;
    byte[] saltedPassword;

    public UserAuthentication(UserAuthentication other) {
        this.id = other.id;
        this.login = other.login;
        this.salt = other.salt;
        this.saltedPassword = other.saltedPassword;
    }
}
