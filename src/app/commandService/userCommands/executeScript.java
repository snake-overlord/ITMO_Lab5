package app.commandService.userCommands;

import app.commandService.BaseCommand;
import app.config.ModeController;
import java.nio.file.Files;
import java.nio.file.Path;
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
            String filePath = command[1];
            Path file = Paths.get(filePath);

            if (Files.exists(file) && Files.isRegularFile(file)) {
                controller.closeInteractiveMode(command[1]);
            } else {
                System.err.println("File does not exist or is not a regular file.");
            }
        }
    }
}
