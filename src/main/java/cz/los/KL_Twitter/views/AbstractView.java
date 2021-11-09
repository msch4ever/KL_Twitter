package cz.los.KL_Twitter.views;

import cz.los.KL_Twitter.app.AppContextHolder;
import cz.los.KL_Twitter.handler.Command;
import cz.los.KL_Twitter.handler.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public abstract class AbstractView {

    protected final Map<Integer, Command> commands;

    public AbstractView(Map<Integer, Command> commands) {
        this.commands = commands;
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
        Response response = AppContextHolder.getAppContext().getDispatcherHandler().handle(command.get());
        log.info(response.toString());
        return response;
    }

    private Optional<Command> parseInput(String input) {
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

    /**
     * This will only work in a terminal.
     */
    protected void clearScreen() {
        System.out.println();
        System.out.print("\033[H\033[2J");
        System.out.println();
        System.out.flush();
    }

}
