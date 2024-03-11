package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;

import java.util.Objects;
import java.util.Vector;

public class removeById extends BaseCommand {
    public static final String name = "remove_by_id";
    public static final String description = ": remove an element from the collection by its id";

    public removeById() {
        super(name, description);
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 2) {
            System.err.print("1 argument required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            Vector<Organization> collection = controller.getVector();
            Long id = Long.parseLong(command[1]);
            collection
                    .stream()
                    .filter(x -> Objects.equals(x.getId(), id))
                    .findFirst()
                    .ifPresentOrElse(controller::deleteItem, () -> System.err.print("Invalid ID.\n"));
        }
    }
}
