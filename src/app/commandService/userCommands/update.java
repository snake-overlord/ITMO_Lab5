package app.commandService.userCommands;

import DMS.models.Organization;
import DMS.models.OrganizationType;
import app.commandService.BaseCommand;
import app.commandService.CollectionInput;
import app.commandService.ICollectionRepository;

import java.util.Objects;
import java.util.Vector;

public class update extends BaseCommand {
    public ICollectionRepository repository;
    public static final String name = "update_by_id";
    public static final String description = "id : update element by its id";
    CollectionInput collectionInput;
    public update(CollectionInput inputController, ICollectionRepository repository) {
        super(name, description);
        this.collectionInput = inputController;
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
            if(collection
                    .stream()
                    .anyMatch(x -> (Objects.equals(x.getId(), id)))){
                Organization last = collection
                        .stream()
                        .filter(x -> Objects.equals(x.getId(), id))
                        .findFirst()
                        .get();
                Organization org = new Organization(collectionInput.getName(),
                        collectionInput.getCoordinates(),
                        last.getCreationDate(),
                        collectionInput.getAnnualTurnover(),
                        collectionInput.getEmployeeCount(),
                        OrganizationType.COMMERCIAL,
                        collectionInput.getAddress());
                repository.deleteItem(last);
                repository.addItem(org, id);
            } else{
                System.err.print("Invalid ID.\n");
            }
            finish();

        }
    }
}
