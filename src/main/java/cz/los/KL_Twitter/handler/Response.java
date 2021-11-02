package cz.los.KL_Twitter.handler;

public class Response {

    private final boolean success;
    private final Command command;
    private final String message;

    public Response(boolean success, Command command, String message) {
        this.success = success;
        this.command = command;
        this.message = message;
    }

    public Command getCommand() {
        return command;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", command=" + command +
                ", message='" + message + '\'' +
                '}';
    }
}
