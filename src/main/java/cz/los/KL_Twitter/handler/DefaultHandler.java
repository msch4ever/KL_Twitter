package cz.los.KL_Twitter.handler;

import cz.los.KL_Twitter.persistence.inMem.UserDaoInMemImpl;
import cz.los.KL_Twitter.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultHandler implements Handler {

    private static Logger log = LogManager.getLogger(DefaultHandler.class);

    private final Handler handler;

    public DefaultHandler() {
        this.handler = new HelpHandler(new CreateUserHandler(new ExitHandler(), new UserService(new UserDaoInMemImpl())));
    }

    @Override
    public Response handle(Command command) {
        return handler.handle(command);
    }
}
