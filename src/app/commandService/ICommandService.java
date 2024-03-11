package app.commandService;

import java.util.List;

public interface ICommandService {
    void addCommand(BaseCommand command);
    void addCommands(List<BaseCommand> commands);
    void executeCommand(String[] command);
    void printCommands();
}
