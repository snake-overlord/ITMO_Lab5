package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;
import app.commandService.CollectionInput;
import app.commandService.ICollectionRepository;
import app.config.ServiceConfiguration;

import java.time.LocalDateTime;

/**
 * <b>name</b> <b>description</b>
 */
public class add extends BaseCommand {
    public ICollectionRepository repository;
    private static final String name = "add";
    private static final String description = "{element} : add a new element to the collection";
    private CollectionInput collectionInput;

    public add(CollectionInput inputController, ICollectionRepository repository) {
        super(name, description);
        this.collectionInput = inputController;
        this.repository = repository;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {

            repository.addItem(new Organization(
                    collectionInput.getName(),
                    collectionInput.getCoordinates(),
                    java.sql.Timestamp.valueOf(LocalDateTime.now()),
                    collectionInput.getAnnualTurnover(),
                    collectionInput.getEmployeeCount(),
                    collectionInput.getType(),
                    collectionInput.getAddress()));
        }
        finish();
    }


}
