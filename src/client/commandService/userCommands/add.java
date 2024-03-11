package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;
import app.commandService.CollectionInputController;
import app.consoleService.ConsoleService;
import app.consoleService.IConsoleService;

import java.time.LocalDateTime;


public class add extends BaseCommand {
    private static final String name = "add";
    private static final String description = "{element} : add a new element to the collection";
    private CollectionInputController collectionInputController;

    public add(CollectionInputController inputController) {
        super(name, description);
        this.collectionInputController = inputController;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {

            controller.addItem(new Organization(
                    collectionInputController.getName(),
                    collectionInputController.getCoordinates(),
                    java.sql.Timestamp.valueOf(LocalDateTime.now()),
                    collectionInputController.getAnnualTurnover(),
                    collectionInputController.getEmployeeCount(),
                    collectionInputController.getType(),
                    collectionInputController.getAddress()));
        }
        finish();
    }


}
