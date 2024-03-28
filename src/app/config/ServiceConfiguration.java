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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceConfiguration {
    /**
     * Sets all app services.
     */
    private static final IConsoleService consoleService = new ConsoleService(new Scanner(System.in));
    private  static ModeController modeController;
    private static CollectionRepository repository;
    private static ICommandService commandService;
    private static IRequestService requestService;
    private static CollectionInput inputController;
    private static IScriptFileService scriptFileService;

    /**
     * Builds ModeController with required services.
     */
    public static ModeController build() {
        inputController = new CollectionInput(consoleService);
        repository = new CollectionRepository();
        requestService = new RequestService(DMS_Configuration.build());
        commandService = new CommandService(addCommands());
        commandService.addCommand(new help(commandService));
        scriptFileService = new ScriptFileService(consoleService, commandService);

        modeController = new ModeController(requestService,
                commandService,
                repository,
                consoleService, scriptFileService);
        commandService.addCommand(new executeScript(modeController));
        return modeController;
    }

    private static List<BaseCommand> addCommands() {
        List<BaseCommand> commands = new ArrayList<>();
        commands.add(new add(inputController, repository));
        commands.add(new addIfMax(inputController, repository));
        commands.add(new addIfMin(inputController, repository));
        commands.add(new clear(repository));
        commands.add(new exit());
        commands.add(new filterByType(repository));
        commands.add(new groupCountingByType(repository));
        commands.add(new countGreaterThanEmployeesCount(inputController, repository));
        commands.add(new head(repository));
        commands.add(new info(repository));
        commands.add(new removeById(repository));
        commands.add(new save(requestService, repository));
        commands.add(new show(repository));
        commands.add(new update(inputController, repository));
        commands.add(new printFieldAscendingType(repository));

        return commands;
    }


}