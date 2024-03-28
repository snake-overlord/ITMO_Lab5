package app.commandService.userCommands;

import app.commandService.BaseCommand;
import app.commandService.ICollectionRepository;
import app.requestService.IRequestService;
import app.requestService.RequestService;
/**
 * <b>name</b> <b>description</b>
 */
public class save extends BaseCommand {
    public ICollectionRepository repository;
    private IRequestService requestService;
    private static final String name = "save";
    private static final String description = ": save the collection";

    public save(IRequestService requestService, ICollectionRepository repository) {
        super(name, description);
        this.requestService = requestService;
        this.repository = repository;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            requestService.updateRequest(repository.getVector());
        }
        System.out.print("Saved!");
    }
}
