package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.DefaultHandler;
import cz.los.KL_Twitter.handler.Handler;
import cz.los.KL_Twitter.handler.Response;
import cz.los.KL_Twitter.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static cz.los.KL_Twitter.config.Configurator.initApp;

@Slf4j
public class App {

    public static AppContext appContext;

    private final Handler defaultHandler;

    private App() {
        this.defaultHandler = new DefaultHandler();

    }

    public static void main(String[] args) {
        // parse initial arguments and configure the app
        initApp(args);
        // run app
        App app = new App();
        app.run();
    }

    private void run() {
        Response response;
        do {
            response = startCOR();
        } while (response.getCommand() != Command.EXIT && response.isSuccess());
        System.exit(0);
    }

    private Response startCOR() {
        log.info("Enter the command.");
        Command command = null;
        Scanner scanner = new Scanner(System.in);
        while (command == null) {
            String input = scanner.nextLine();
            command = parseInput(input);
            if (command == null) {
                log.warn("Could not parse input:[" + input + "].");
            }
        }
        //scanner.close();
        Response response = defaultHandler.handle(command);
        log.info(response.toString());
        return response;
    }

    private static Command parseInput(String input) {
        Command[] commands = Command.values();
        for (Command command : commands) {
            if (command.getValue().equalsIgnoreCase(input.trim())) {
                return command;
            }
        }
        return null;
    }
}
