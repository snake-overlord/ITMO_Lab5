package app.commandService.userCommands;

import app.commandService.BaseCommand;

public class clear extends BaseCommand {
    private static final String name = "clear";
    private static final String description = ": clear the collection";

    public clear() {
        super(name, description);
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            controller.clearCollection();
        }
        finish();
    }
}