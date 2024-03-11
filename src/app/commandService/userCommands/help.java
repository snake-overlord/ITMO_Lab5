package app.commandService.userCommands;

import app.commandService.BaseCommand;
import app.commandService.ICommandService;

public class help extends BaseCommand {
    public static final String name = "help";
    public static final String description = ": available commands info";
    private ICommandService commandService;
    public help(ICommandService commandService){
        super(name, description);
        this.commandService = commandService;
    }
    @Override
    public void execute(String[] command){
        if(command.length!=1){
            System.err.print("0 arguments required. Provided: " + (command.length-1));
            System.out.print("Try again >");
        }
        else{
            commandService.printCommands();
        }
    }
}
