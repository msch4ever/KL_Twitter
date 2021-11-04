package cz.los.KL_Twitter.handler;

import cz.los.KL_Twitter.persistence.inMem.UserDaoInMemImpl;
import cz.los.KL_Twitter.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultHandler implements Handler {

    private final Handler handler;

    public DefaultHandler() {
        this.handler = new HelpHandler(new CreateUserHandler(new ExitHandler(), new UserServiceImpl(new UserDaoInMemImpl())));
    }

    @Override
    public Response handle(Command command) {
        return handler.handle(command);
    }
}
