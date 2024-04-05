package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;
import app.commandService.CollectionInput;
import app.commandService.ICollectionRepository;

import java.time.LocalDateTime;

public class addIfMax extends BaseCommand {
    public ICollectionRepository repository;
    private static final String name = "add_if_max";
    private static final String description = "{element} : add a new element to the collection, if value {employee count} is max.";
    private final CollectionInput collectionInput;

    public addIfMax(CollectionInput inputController, ICollectionRepository repository) {
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
            Organization element = new Organization(
                    collectionInput.getName(),
                    collectionInput.getCoordinates(),
                    java.sql.Timestamp.valueOf(LocalDateTime.now()),
                    collectionInput.getAnnualTurnover(),
                    collectionInput.getEmployeeCount(),
                    collectionInput.getType(),
                    collectionInput.getAddress());
            long maxEmployees = 0;
            for(Organization x:repository.getVector()){
                if(x.getEmployeesCount()>maxEmployees){
                    maxEmployees = x.getEmployeesCount();
                }
            }
            if(element.getEmployeesCount()>maxEmployees)
                repository.addItem(element);
        }
        finish();
    }
}
