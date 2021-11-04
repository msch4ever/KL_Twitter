package cz.los.KL_Twitter.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelpHandler extends AbstractHandler {

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
