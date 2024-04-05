package app.commandService;

import java.util.Map;

public interface ICommandService {
    void addCommand(BaseCommand command);

    void addCommands(Map<String, BaseCommand> c);

    void executeCommand(String[] command);
    void printCommands();
}
