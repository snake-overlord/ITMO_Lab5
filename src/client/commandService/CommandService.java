package commandService;

import java.util.List;

public class CommandService implements ICommandService{
    private List<BaseCommand> commands;
    public CommandService(List<BaseCommand> commands){
        this.commands = commands;
    }
    @Override
    public void addCommand(BaseCommand command){
        commands.add(command);
    }
    @Override
    public void addCommands(List<BaseCommand> c){
        commands.addAll(c);
    }
    @Override
    public void executeCommand(String[] command) {
        String commandName = command[0];
        var com = commands
                .stream()
                .filter(x -> x.getName().equals(commandName))
                .findFirst();
        com.ifPresentOrElse(BaseCommand -> BaseCommand.execute(command), () -> System.err.print("Command '%s' is not found. Type 'help' for info)"));
    }
}
