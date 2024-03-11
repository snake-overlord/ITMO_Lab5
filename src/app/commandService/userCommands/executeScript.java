package app.commandService.userCommands;

import app.commandService.BaseCommand;
import app.config.ModeController;
import app.exceptions.ScriptRecursionException;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class executeScript extends BaseCommand {
    private static final String name = "execute_script";
    private static final String description = "file_name : read and execute the script from the specified file.";
    private final ModeController controller;

    public executeScript(ModeController controller) {
        super(name, description);
        this.controller = controller;
    }

    @Override
    public void execute(String[] command){
        if (command.length != 2) {
            System.err.print("1 arguments required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            try {
                if (!Paths.get(command[1]).toFile().isFile()) {
                    throw new FileNotFoundException();
                }
            } catch (FileNotFoundException ex) {
                System.err.print("File is not found.");
            }
            controller.closeInteractiveMode(command[1]);
        }
    }
}
