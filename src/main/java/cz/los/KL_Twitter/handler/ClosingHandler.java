package cz.los.KL_Twitter.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClosingHandler implements Handler {

    @Override
    public Response handle(Command command) {
        log.warn("SAD! Could not handle the request.");
        return new Response(false, command, "Bad request.");
    }
}
