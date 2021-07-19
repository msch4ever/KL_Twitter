package cz.los.KL_Twitter.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelpHandler extends AbstractHandler {

    private static Logger log = LogManager.getLogger(HelpHandler.class);

    public HelpHandler(Handler nextHandler) {
        super(Command.HELP, nextHandler);
    }

    @Override
    protected Response handleCommand() {
        Command[] commands = Command.values();
        StringBuilder sb = new StringBuilder("There are the following commands:");
        for (Command command : commands) {
            sb.append(System.lineSeparator())
                    .append(command.getValue());
        }
        System.out.println(sb.toString());
        return new Response(true, command, "List of commands rendered successfully.");
    }
}
