package cz.los.KL_Twitter.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class DispatcherHandler implements Handler {

    private final ApplicationContext context;
    private List<Handler> handlerChain;
    private ClosingHandler closingHandler;

    public DispatcherHandler(@Autowired ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Response handle(Command command) {
        if (handlerChain == null) {
            initChain();
        }
        Response response = new Response(false, command);
        for (Handler handler : handlerChain) {
            response = handler.handle(command);
            if (!response.isInvalidResponse()) {
                break;
            }
        }
        if (response.isInvalidResponse()) {
            return closingHandler.handle(command);
        }
        return response;
    }

    private void initChain() {
        List<Handler> handlers = new ArrayList<>(context.getBeansOfType(Handler.class).values());
        handlers.remove(this);
        closingHandler = (ClosingHandler) handlers.stream().filter(it -> it instanceof ClosingHandler).findFirst().orElseThrow(IllegalStateException::new);
        handlers.remove(closingHandler);
        handlerChain = Collections.unmodifiableList(handlers);
    }
}
