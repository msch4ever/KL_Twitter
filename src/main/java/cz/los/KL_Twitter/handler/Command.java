package cz.los.KL_Twitter.handler;

public enum Command {

    HELP("help"),
    SIGN_UP("sign up"),
    SIGN_IN("sign in"),
    SIGN_OUT("sign out"),
    TWEET("tweet"),
    PROFILE("profile"),
    EXIT("exit");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
