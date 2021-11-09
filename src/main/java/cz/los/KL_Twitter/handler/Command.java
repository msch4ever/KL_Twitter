package cz.los.KL_Twitter.handler;

public enum Command {

    HELP("help"), SIGN_UP("sign_up"), SIGN_IN("sign_in"), EXIT("exit");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
