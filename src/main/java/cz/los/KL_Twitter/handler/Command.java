package cz.los.KL_Twitter.handler;

public enum Command {

    HELP("help"), CREATE_USER("create user"), EXIT("exit");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
