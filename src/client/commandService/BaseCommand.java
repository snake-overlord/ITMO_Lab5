package commandService;

import requestService.IRequestService;

public abstract class BaseCommand {
    public static IRequestService requestService;
    String name;
    public BaseCommand(String name){
        this.name = name;
    }
    String getName(){
        return this.name;
    }
    public abstract void execute(String[] command);
}
