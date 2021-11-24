package cz.los.KL_Twitter.service;

public class TweetLengthExceededException extends Exception {

    public TweetLengthExceededException(String message) {
        super(message);
    }

}
