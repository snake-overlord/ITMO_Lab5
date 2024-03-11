package app.scriptFileService;

import app.commandService.ICommandService;
import app.consoleService.IConsoleService;
import app.exceptions.FileIsEmptyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ScriptFileService implements IScriptFileService {
    private IConsoleService consoleService;
    private ICommandService commandService;
    private String fileName;

    public ScriptFileService(IConsoleService console, ICommandService commandService) {
        this.consoleService = console;
        this.commandService= commandService;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
    @Override
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void executeScript(){
        String[] scriptCommand;
        try {
            Scanner scan = new Scanner(new File(fileName));
            consoleService.setScanner(scan);
            while (scan.hasNextLine()) {
                scriptCommand = consoleService.parseCommand();
                System.out.print(Arrays.toString(scriptCommand) + "\n");
                commandService.executeCommand(scriptCommand);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}