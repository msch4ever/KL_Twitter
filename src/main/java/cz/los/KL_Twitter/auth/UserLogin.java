package cz.los.KL_Twitter.auth;

import cz.los.KL_Twitter.model.User;
import lombok.Value;

@Value
public class UserLogin {

    User user;
    Password password;

    private static class Password {
        private String salted;

        private Password(String password) {
            this.salted = encodePassword(password);
        }

        private String encodePassword(String password) {
            return null;
        }
    }

}
