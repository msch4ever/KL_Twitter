package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.DispatcherHandler;
import cz.los.KL_Twitter.handler.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
public abstract class AbstractView {

    public static final String SEPARATOR = "------------------------------------------------------------------------------------------";

    protected final Map<Integer, Command> commands;
    private DispatcherHandler dispatcherHandler;
    protected String message;

    public AbstractView(Map<Integer, Command> commands) {
        this.commands = commands;
    }

    @Autowired
    public final void setDispatcherHandler(DispatcherHandler dispatcherHandler) {
        this.dispatcherHandler = dispatcherHandler;
    }

    public abstract AbstractView render();

    public Response listen() {
        System.out.println("Enter the command.");
        Optional<Command> command = Optional.empty();
        Scanner scanner = new Scanner(System.in);
        while (!command.isPresent()) {
            String input = scanner.nextLine();
            command = parseInput(input);
            if (!command.isPresent()) {
                log.warn("Could not parse input:[" + input + "].");
            }
        }
        //scanner.close();
        Response response = dispatcherHandler.handle(command.get());
        log.info(response.toString());
        return response;
    }

    protected Optional<Command> parseInput(String input) {
        String trimmedInput = input.toLowerCase(Locale.ROOT).trim();
        try {
            return Optional.of(commands.get(Integer.parseInt(input)));
        } catch (NumberFormatException e) {
            log.warn("Input is not a number. [{}]", input);
            log.debug("Trying to find command by value.");
            log.debug("Commands for this view are:{}", commands);
        }
        return commands.values().stream().filter(it -> it.getValue().equals(trimmedInput)).findFirst();
    }

    public Command getCommand(int index) {
        return commands.get(index);
    }

    /**
     * This will only work in a terminal.
     */
    protected void clearScreen() {
        try {
            String operatingSystem = System.getProperty("os.name");

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAndDestroyMessage() {
        String text = message;
        message = null;
        return text;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}