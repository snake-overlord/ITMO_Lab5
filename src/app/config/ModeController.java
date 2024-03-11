package app.config;

import DMS.models.Organization;
import app.commandService.ICollectionController;
import app.commandService.ICommandService;
import app.exceptions.FileIsEmptyException;
import app.exceptions.ScriptRecursionException;
import app.requestService.IRequestService;
import app.consoleService.*;
import app.scriptFileService.IScriptFileService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
public class ModeController {
    private boolean isInteractive = true;
    OpenUniqueFile openUniqueFile;
    private final IRequestService requestService;
    private final ICollectionController controller;
    private final IConsoleService consoleService;
    private final ICommandService commandService;
    private final IScriptFileService scriptFileService;
    public ModeController(IRequestService requestService, ICommandService commandService, ICollectionController controller, IConsoleService consoleService, IScriptFileService scriptFileService){
        this.commandService= commandService;
        this.consoleService = consoleService;
        this.controller = controller;
        this.requestService = requestService;
        this.scriptFileService = scriptFileService;
    }

    public void launch(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> commandService.executeCommand("save".trim()
                .split(" ", 2))));
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
        controller.addItems(collection);
        System.out.printf("Items successfully uploaded to the collection: %d. %n", controller.getVector().size());
    }
    private void launchInteractive() {
        while(isInteractive){
            openUniqueFile = new OpenUniqueFile();
            consoleService.setScanner(new Scanner(System.in));
            String[] userCommand;
            userCommand = consoleService.parseCommand();
            commandService.executeCommand(userCommand);
        }

    }
    public void closeInteractiveMode(String fileName){
        isInteractive = false;
        openUniqueFile.check(fileName);
        scriptFileService.setFileName(fileName);
        scriptFileService.executeScript();
        isInteractive = true;
    }
}