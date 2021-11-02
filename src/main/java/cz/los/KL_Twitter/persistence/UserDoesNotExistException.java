package cz.los.KL_Twitter.persistence;

/**
 * Indicates that users does not exist in the DB
 */
public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException(String s) {
        super(s);
    }
}
