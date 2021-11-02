package cz.los.KL_Twitter.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClosingHandler implements Handler {

    private static Logger log = LogManager.getLogger(ClosingHandler.class);

    @Override
    public Response handle(Command command) {
        log.warn("SAD! Could not handle the request.");
        return new Response(false, command, "Bad request.");
    }
}
