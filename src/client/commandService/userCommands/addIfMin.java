package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;
import app.commandService.CollectionInputController;
import app.consoleService.ConsoleService;
import app.consoleService.IConsoleService;

import java.time.LocalDateTime;

public class addIfMin extends BaseCommand {
    private static final String name = "add_if_min";
    private static final String description = "{element} : add a new element to the collection, if value {employee count} is min.";
    private final CollectionInputController collectionInputController;

    public addIfMin(CollectionInputController inputController) {
        super(name, description);
        this.collectionInputController = inputController;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            Organization element = new Organization(
                    collectionInputController.getName(),
                    collectionInputController.getCoordinates(),
                    java.sql.Timestamp.valueOf(LocalDateTime.now()),
                    collectionInputController.getAnnualTurnover(),
                    collectionInputController.getEmployeeCount(),
                    collectionInputController.getType(),
                    collectionInputController.getAddress());
            long minEmployees = element.getEmployeesCount();
            for(Organization x:controller.getVector()){
                if(x.getEmployeesCount()<minEmployees){
                    minEmployees = x.getEmployeesCount();
                }
            }
            if(element.getEmployeesCount()<minEmployees)
                controller.addItem(element);
        }
        finish();
    }
}
