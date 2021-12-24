package cz.los.KL_Twitter.handler.global;

import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelpHandler extends AbstractHandler {

    public HelpHandler() {
        super(Command.HELP);
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
