package app.commandService.userCommands;

import app.commandService.BaseCommand;

public class head extends BaseCommand {

    private static final String name = "head";
    private static final String description = ": output the first element of the collection";

    public head() {
        super(name, description);
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            if(controller.getVector().isEmpty()){
                System.err.print("Collection is empty!");
            }
            else{
                System.out.print(controller.getVector().elementAt(0));
            }
        }
    }
}
