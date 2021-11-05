package cz.los.KL_Twitter.auth;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class UserAuthentication {

    String login;
    byte[] salt;
    byte[] saltedPassword;

    public UserAuthentication(UserAuthentication other) {
        this.login = other.login;
        this.salt = other.salt;
        this.saltedPassword = other.saltedPassword;
    }
}
