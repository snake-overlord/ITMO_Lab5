package app.commandService;

import java.util.Map;

/**
 * Class for operating with available commands.
 */
public class CommandService implements ICommandService{
    private final Map<String, BaseCommand> commands;

    /**
     *
     * @param commands - List for storing available user commands.
     */
    public CommandService(Map<String, BaseCommand> commands){
        this.commands = commands;
    }
    @Override
    public void addCommand(BaseCommand command){
        commands.put(command.name, command);
    }
    @Override
    public void addCommands(Map<String, BaseCommand> c){
        commands.putAll(c);
    }

    /**
     * Finds command by name and tries to execute.
     * @param command - parsed command of the form [name, arg]
     */
    @Override
    public void executeCommand(String[] command) {
        if(command.length == 0){
            return;
        }
        String commandName = command[0];
        if(commands.containsKey(commandName)){
            var com = commands.get(commandName);
            com.execute(command);
        }
        else{
            System.err.printf("Command '%s' is not found. Type 'help' for info \n", commandName);
        }
    }
    public void printCommands() {
        commands.values().forEach(x -> System.out.print(x.name + " " + x.description + "\n"));
    }
}