package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.app.AppContextHolder;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;

import java.util.HashMap;
import java.util.Map;

public class FeedView extends AbstractView {

    private Mode feedMode = Mode.HOME;

    public FeedView() {
        super(initCommands());
    }

    private static Map<Integer, Command> initCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.SIGN_IN);
        commands.put(2, Command.SIGN_UP);
        commands.put(3, Command.EXIT);
        return commands;
    }

    @Override
    public AbstractView render() {
        clearScreen();
        System.out.println(SEPARATOR);
        System.out.println("FEED VIEW");
        System.out.println("WELCOME " + AppContextHolder.getSecurityContext().getSession().getUserAuthentication().getLogin());
        System.out.println(SEPARATOR);
        return this;
    }

    public void setUserMode() {
        this.feedMode = Mode.USER;
    }

    public void setHomeMode() {
        this.feedMode = Mode.HOME;
    }

    public void setTweetMode() {
        this.feedMode = Mode.TWEET;
    }

    public enum Mode {
        HOME, USER, TWEET;
    }
}
