package cz.los.KL_Twitter.service;

import cz.los.KL_Twitter.auth.UserAuthentication;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public interface AuthService {

    UserAuthentication getAuthByLogin(String login);

    boolean authorize(String login, String password);

    void create(String login, String password);

    @SneakyThrows(NoSuchAlgorithmException.class)
    default byte[] encodePassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }
}