package cz.los.KL_Twitter.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExitHandler extends AbstractHandler {

    private static Logger log = LogManager.getLogger(ExitHandler.class);

    public ExitHandler() {
        super(Command.EXIT, null);
    }

    @Override
    public Response handleCommand() {
        log.info("The application is shutting down.");
        return new Response(true, command, "Sutting Down.");
    }
}
