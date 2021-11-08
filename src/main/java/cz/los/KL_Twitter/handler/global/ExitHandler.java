package cz.los.KL_Twitter.handler.global;

import cz.los.KL_Twitter.handler.AbstractHandler;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExitHandler extends AbstractHandler {

    public ExitHandler() {
        super(Command.EXIT);
    }

    @Override
    public Response handleCommand() {
        log.info("The application is shutting down.");
        return new Response(true, command, "Sutting Down.");
    }
}
