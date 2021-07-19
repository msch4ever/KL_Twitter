package cz.los.KL_Twitter.handler;

public abstract class AbstractHandler implements Handler {

    protected final Handler nextHandler;
    protected final Command command;

    public AbstractHandler(Command command, Handler nextHandler) {
        this.command = command;
        this.nextHandler = nextHandler;
    }

    @Override
    public Response handle(Command inputCommand) {
        if (command == inputCommand) {
            return handleCommand();
        }
        return nextHandler.handle(inputCommand);
    }

    abstract protected Response handleCommand();
}
