package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;
import app.commandService.ICollectionRepository;
/**
 * <b>name</b> <b>description</b>
 */
public class show extends BaseCommand {
    public ICollectionRepository repository;
    public static final String name = "show";
    public static final String description = ": shows all elements of the collection";
    public show(ICollectionRepository repository) {
        super(name, description);
        this.repository = repository;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 argument required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            for(Organization x: this.repository.getVector()){
                System.out.print(x.toString());
            }
        }

    }

}
