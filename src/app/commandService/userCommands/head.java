package app.commandService.userCommands;

import app.commandService.BaseCommand;
import app.commandService.ICollectionRepository;

public class head extends BaseCommand {
    public ICollectionRepository repository;
    public static final String name = "head";
    public static final String description = ": output the first element of the collection";

    public head(ICollectionRepository repository) {
        super(name, description);
        this.repository = repository;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            if(repository.getVector().isEmpty()){
                System.err.print("Collection is empty!");
            }
            else{
                System.out.print(repository.getVector().elementAt(0));
            }
        }
    }
}
