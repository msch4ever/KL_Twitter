package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.DispatcherHandler;
import cz.los.KL_Twitter.handler.Handler;
import cz.los.KL_Twitter.handler.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

import static cz.los.KL_Twitter.config.Configurator.initApp;

@Slf4j
public class App {

    private final Handler dispatcherHandler;

    private App() {
        this.dispatcherHandler = AppContextHolder.getAppContext().getDispatcherHandler();
    }

    public static void main(String[] args) {
        initApp(args);
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
        Response response = dispatcherHandler.handle(command);
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
