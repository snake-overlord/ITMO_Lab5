package app.commandService.userCommands;

import DMS.models.Organization;
import DMS.models.OrganizationType;
import app.commandService.BaseCommand;
import app.commandService.CollectionInputController;
import app.consoleService.ConsoleService;
import app.consoleService.IConsoleService;

import java.util.Objects;
import java.util.Vector;

public class update extends BaseCommand {
    public static final String name = "update_by_id";
    public static final String description = "id : update element by its id";
    CollectionInputController collectionInputController;
    public update(CollectionInputController inputController) {
        super(name, description);
        this.collectionInputController = inputController;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 2) {
            System.err.print("1 argument required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            Vector<Organization> collection = controller.getVector();
            Long id = Long.parseLong(command[1]);
            if(collection
                    .stream()
                    .anyMatch(x -> (Objects.equals(x.getId(), id)))){
                Organization last = collection
                        .stream()
                        .filter(x -> Objects.equals(x.getId(), id))
                        .findFirst()
                        .get();
                Organization org = new Organization(collectionInputController.getName(),
                        collectionInputController.getCoordinates(),
                        last.getCreationDate(),
                        collectionInputController.getAnnualTurnover(),
                        collectionInputController.getEmployeeCount(),
                        OrganizationType.COMMERCIAL,
                        collectionInputController.getAddress());
                controller.deleteItem(last);
                controller.addItem(org, id);
            } else{
                System.err.print("Invalid ID.\n");
            }
            finish();

        }
    }
}
