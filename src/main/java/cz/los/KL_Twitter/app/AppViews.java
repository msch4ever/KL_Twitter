package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.config.Configurator;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppViews {

    public static void main(String[] args) {
        Configurator.initApp(args);
        AppViews app = new AppViews();
        log.info("Application starting!");
        app.run();
    }

    private void run() {
        Response response = AppContextHolder.getAppContext().getWelcomeView().render().listen();
        while (response.getCommand() != Command.EXIT) {
            response = response.getView().render().listen();
        }
        System.exit(0);
    }

}
