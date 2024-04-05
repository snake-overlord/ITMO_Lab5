package app.commandService.userCommands;

import app.commandService.BaseCommand;
import app.consoleService.ConsoleService;

public class exit extends BaseCommand {
    public static final String name = "exit";
    public static final String description = ": terminate the program (without saving)";

    public exit() {
        super(name, description);
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            System.exit(0);
        }
    }
}
