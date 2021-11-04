package cz.los.KL_Twitter.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExitHandler extends AbstractHandler {

    public ExitHandler() {
        super(Command.EXIT, null);
    }

    @Override
    public Response handleCommand() {
        log.info("The application is shutting down.");
        return new Response(true, command, "Sutting Down.");
    }
}
