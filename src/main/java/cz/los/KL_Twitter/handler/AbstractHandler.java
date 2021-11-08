package cz.los.KL_Twitter.handler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractHandler implements Handler {

    @Setter
    protected Handler nextHandler;
    protected final Command command;

    public AbstractHandler(Command command) {
        this.command = command;
    }

    @Override
    public Response handle(Command inputCommand) {
        if (nextHandler == null) {
            throw new HandlerException("Next handler was not initialized!");
        }
        if (command == inputCommand) {
            return handleCommand();
        }
        return nextHandler.handle(inputCommand);
    }

    abstract protected Response handleCommand();
}
