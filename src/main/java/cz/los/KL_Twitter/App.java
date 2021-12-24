package cz.los.KL_Twitter;

import cz.los.KL_Twitter.app.AppConfig;
import cz.los.KL_Twitter.config.Configurator;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.views.WelcomeView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(AppConfig.class)
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        context.getBean(Configurator.class).initDB();
        Response response = context.getBean(WelcomeView.class).render().listen();
        while (response.getCommand() != Command.EXIT) {
            response = response.getView().render().listen();
        }
        System.exit(0);
    }

    /*public void run(String ... args) {
        //Response response = ContextHolder.getAppContext().getWelcomeView().render().listen();
        Response response = SpringApplication.;
        while (response.getCommand() != Command.EXIT) {
            response = response.getView().render().listen();
        }
        System.exit(0);
    }*/

}
