package cz.los.KL_Twitter.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DispatcherHandler implements Handler {

    private final Handler handler;

    public DispatcherHandler(Handler firstInChain) {
        this.handler = firstInChain;
    }

    @Override
    public Response handle(Command command) {
        return handler.handle(command);
    }
}
