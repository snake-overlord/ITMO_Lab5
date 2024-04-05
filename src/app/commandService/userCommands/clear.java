package app.commandService.userCommands;

import app.commandService.BaseCommand;
import app.commandService.ICollectionRepository;

public class clear extends BaseCommand {
    public ICollectionRepository repository;
    public static final String name = "clear";
    public static final String description = ": clear the collection";

    public clear(ICollectionRepository repository) {
        super(name, description);
        this.repository = repository;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            repository.clearCollection();
        }
        finish();
    }
}