package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;

public class show extends BaseCommand {
    public static final String name = "show";
    public static final String description = ": shows all elements of the collection";
    public show() {
        super(name, description);
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 argument required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            for(Organization x: this.controller.getVector()){
                System.out.print(x.toString());
            }
        }

    }

}
