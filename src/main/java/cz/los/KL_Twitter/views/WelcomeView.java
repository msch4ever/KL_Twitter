package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.handler.Command;

import java.util.HashMap;
import java.util.Map;

public class WelcomeView extends AbstractView {

    private static final String CONTENT =
            "Welcome to the GRANNY BENCH.\n" +
                    "\n" +
                    "Here you can share your thoughts and find friends!\n" +
                    "\n" +
                    "[1] Sign In    [2] Sign Up    [3] exit\n" +
                    "\n";

    public WelcomeView() {
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
        System.out.print(CONTENT);
        System.out.println(SEPARATOR);
        if (message != null) {
            System.out.println(SEPARATOR);
            System.out.println("Message: " + getAndDestroyMessage());
            System.out.println(SEPARATOR);
        }
        return this;
    }
}
