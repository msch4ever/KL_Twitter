package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.handler.Command;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WriteTweetView extends AbstractView {

    private static final String CONTENT =
            "Please write your tweet here. It should not exceed 140 characters\n" +
                    "\n" +
                    "[1] Sign In    [2] Abort    [3] Exit\n" +
                    "\n";

    public WriteTweetView() {
        super(initCommands());
    }

    private static Map<Integer, Command> initCommands() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, Command.SIGN_OUT);
        commands.put(2, Command.ABORT);
        commands.put(4, Command.EXIT);
        return commands;
    }

    @Override
    public AbstractView render() {
        clearScreen();
        System.out.println(SEPARATOR);
        System.out.println(CONTENT);
        System.out.println(SEPARATOR);
        return this;
    }
}
