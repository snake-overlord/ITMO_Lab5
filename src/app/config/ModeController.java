package app.config;

import DMS.models.Organization;
import app.commandService.ICollectionRepository;
import app.commandService.ICommandService;
import app.requestService.IRequestService;
import app.consoleService.*;
import app.scriptFileService.IScriptFileService;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Implementation of interactive and script modes.
 * <p>
 *  In interactive mode, the program executes commands from console.
 *  In script mode, the program executes commands from a text file.
 * <p>
 */
public class ModeController {
    private boolean isInteractive = true;
    OpenUniqueFile openUniqueFile;
    private final IRequestService requestService;
    private final ICollectionRepository repository;
    private final IConsoleService consoleService;
    private final ICommandService commandService;
    private final IScriptFileService scriptFileService;

    /**
     *
     * @param requestService - used for initial load from the xml file.
     * @param repository - stores loaded collection.
     * @param scriptFileService - executes script in script mode.
     * @param commandService - executes user commands in interactive mode.
     * @param consoleService - reads and parses user input.
     */
    public ModeController(IRequestService requestService,
                          ICommandService commandService,
                          ICollectionRepository repository,
                          IConsoleService consoleService,
                          IScriptFileService scriptFileService){
        this.commandService= commandService;
        this.consoleService = consoleService;
        this.repository = repository;
        this.requestService = requestService;
        this.scriptFileService = scriptFileService;
    }
    /**
     * Initial launch. Loads collection from the file and starts interactive mode.
     */
    public void launch(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.print("Terminating the program...")));
        loadMap();
        while(true){
            launchInteractive();
        }
    }


    private void loadMap() {
        Vector<Organization> collection;
        try {
            collection = requestService.readRequest();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        repository.addItems(collection);
        System.out.printf("Items successfully uploaded to the collection: %d. %n", repository.getVector().size());
    }
    private void launchInteractive() {
        while(isInteractive){
            openUniqueFile = new OpenUniqueFile();
            consoleService.setScanner(new Scanner(System.in));
            String[] userCommand = consoleService.parseCommand();
            commandService.executeCommand(userCommand);
        }

    }

    /**
     * Handles script mode.
     */
    public void closeInteractiveMode(String fileName){
        isInteractive = false;
        if(openUniqueFile.check(fileName)){
            scriptFileService.setFileName(fileName);
            scriptFileService.executeScript();
        }
        isInteractive = true;
    }
}