package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;

import java.util.HashMap;
import java.util.Map;

public class FeedView extends AbstractView {

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
        return null;
    }

    @Override
    public Response listen() {
        return null;
    }
}
