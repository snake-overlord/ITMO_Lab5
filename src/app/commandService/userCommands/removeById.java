package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;
import app.commandService.ICollectionRepository;

import java.util.Objects;
import java.util.Vector;
/**
 * <b>name</b> <b>description</b>
 */
public class removeById extends BaseCommand {
    public ICollectionRepository repository;
    public static final String name = "remove_by_id";
    public static final String description = ": remove an element from the collection by its id";

    public removeById(ICollectionRepository repository) {
        super(name, description);
        this.repository = repository;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 2) {
            System.err.print("1 argument required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            Vector<Organization> collection = repository.getVector();
            Long id = Long.parseLong(command[1]);
            collection
                    .stream()
                    .filter(x -> Objects.equals(x.getId(), id))
                    .findFirst()
                    .ifPresentOrElse(repository::deleteItem, () -> System.err.print("Invalid ID.\n"));
        }
    }
}
