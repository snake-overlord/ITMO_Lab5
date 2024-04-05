package app.config;

import DMS.DMS_Configuration;
import app.commandService.*;
import app.commandService.userCommands.*;
import app.consoleService.ConsoleService;
import app.consoleService.IConsoleService;
import app.requestService.IRequestService;
import app.requestService.RequestService;
import app.scriptFileService.IScriptFileService;
import app.scriptFileService.ScriptFileService;

import java.util.*;

public class ServiceConfiguration {
    /**
     * Sets all app services.
     */
    private static final IConsoleService consoleService = new ConsoleService(new Scanner(System.in));
    private static CollectionRepository repository;
    private static IRequestService requestService;
    private static CollectionInput inputController;

    /**
     * Builds ModeController with required services.
     */
    public static ModeController build() {
        inputController = new CollectionInput(consoleService);
        repository = new CollectionRepository();
        requestService = new RequestService(DMS_Configuration.build());
        ICommandService commandService = new CommandService(addCommands());
        commandService.addCommand(new help(commandService));
        IScriptFileService scriptFileService = new ScriptFileService(consoleService, commandService);

        ModeController modeController = new ModeController(requestService,
                commandService,
                repository,
                consoleService, scriptFileService);
        commandService.addCommand(new executeScript(modeController));
        return modeController;
    }

    private static Map<String, BaseCommand> addCommands() {
        Map<String, BaseCommand> commands = new HashMap<>();
        commands.put(add.name, new add(inputController, repository));
        commands.put(addIfMax.name, new addIfMax(inputController, repository));
        commands.put(addIfMin.name, new addIfMin(inputController, repository));
        commands.put(clear.name, new clear(repository));
        commands.put(exit.name, new exit());
        commands.put(filterByType.name, new filterByType(repository));
        commands.put(groupCountingByType.name, new groupCountingByType(repository));
        commands.put(countGreaterThanEmployeesCount.name, new countGreaterThanEmployeesCount(inputController, repository));
        commands.put(head.name, new head(repository));
        commands.put(info.name, new info(repository));
        commands.put(removeById.name, new removeById(repository));
        commands.put(save.name, new save(requestService, repository));
        commands.put(show.name, new show(repository));
        commands.put(update.name, new update(inputController, repository));
        commands.put(printFieldAscendingType.name, new printFieldAscendingType(repository));

        return commands;
    }


}