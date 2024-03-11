package app.commandService.userCommands;

import app.commandService.BaseCommand;
import app.requestService.IRequestService;
import app.requestService.RequestService;

public class save extends BaseCommand {
    private IRequestService requestService;
    private static final String name = "save";
    private static final String description = ": save the collection";

    public save(IRequestService requestService) {
        super(name, description);
        this.requestService = requestService;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            requestService.updateRequest(controller.getVector());
        }
        finish();
    }
}
