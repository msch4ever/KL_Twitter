package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.auth.Session;
import cz.los.KL_Twitter.auth.UserAuthentication;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

public interface AuthService {

    void startSession(String login);

    void endSession(Session session);

    Optional<UserAuthentication> getAuthByLogin(String login);

    boolean authorize(String login, String password);

    void createAuth(Long userId, String login, String password);

    @SneakyThrows(NoSuchAlgorithmException.class)
    default byte[] encodePassword(byte[] salt, String password) {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    default byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}