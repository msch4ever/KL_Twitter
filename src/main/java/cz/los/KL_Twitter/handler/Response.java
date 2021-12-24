package cz.los.KL_Twitter.handler;

import cz.los.KL_Twitter.views.AbstractView;
import lombok.Value;

@Value
public class Response {

    boolean success;
    Command command;
    String message;
    AbstractView view;

    public Response(boolean success, Command command) {
        this.success = success;
        this.command = command;
        this.message = null;
        this.view = null;
    }

    @Deprecated
    public Response(boolean success, Command command, String message) {
        this.success = success;
        this.command = command;
        this.message = message;
        this.view = null;
    }

    public Response(boolean success, Command command, AbstractView view) {
        this.success = success;
        this.command = command;
        this.message = null;
        this.view = view;
    }

    public Command getCommand() {
        return command;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isInvalidResponse() {
        return !success && message == null && view == null;
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
