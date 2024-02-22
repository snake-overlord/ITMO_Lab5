package scriptFileService;

import consoleService.IConsoleService;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScriptFileService implements IScriptFileService {
    private IConsoleService consoleService;
    private String fileName;

    public ScriptFileService(IConsoleService console, String fileName) {
        this.fileName = fileName;
        this.consoleService = console;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public List<String[]> getCommands() {
        List<String[]> commands = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(fileName));
            consoleService.setScanner(scan);
            if (scan.hasNextLine())
                do {
                    commands.add(consoleService.parseCommand());
                } while(scan.hasNextLine());
            else {
                throw new RuntimeException();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return commands;
    }
}