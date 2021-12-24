package cz.los.KL_Twitter.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractHandler implements Handler {

    protected final Command command;

    public AbstractHandler(Command command) {
        this.command = command;
    }

    @Override
    public Response handle(Command inputCommand) {
        if (command == inputCommand) {
            return handleCommand();
        }
        return new Response(false, inputCommand);
    }

    abstract protected Response handleCommand();
}
