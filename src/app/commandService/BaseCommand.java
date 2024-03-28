package app.commandService;

/**
 * Abstract user command class.
 */
public abstract class BaseCommand {
    String name;
    String description;

    public BaseCommand(String name, String description){
        this.name = name;
        this.description = description;
    }
    String getName(){
        return this.name;
    }

    /**
     * Notifies the user when the command is executed successfully.
     */
    protected void finish(){
        System.out.print("Finished successfully!\n>");
    }
    public abstract void execute(String[] command);
}
