package app.commandService;

import app.config.ServiceConfiguration;
import app.exceptions.ScriptRecursionException;
import app.requestService.IRequestService;

public abstract class BaseCommand {
    public ICollectionController controller = ServiceConfiguration.getCollectionService();
    String name;
    String description;
    public BaseCommand(String name, String description){
        this.name = name;
        this.description = description;
    }
    public void setController(ICollectionController controller){
        this.controller = controller;
    }
    String getName(){
        return this.name;
    }
    protected void finish(){
        System.out.print("Finished successfully!\n>");
    }
    public abstract void execute(String[] command);
}
