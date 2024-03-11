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

import static DMS.DMS_Configuration.getEnv;

public class ServiceConfiguration {

    private static final IConsoleService consoleService = new ConsoleService(new Scanner(System.in));
    private  static ModeController modeController;
    private static CollectionController collectionService;
    private static ICommandService commandService;
    private static IRequestService requestService;
    private static CollectionInputController inputController;
    private static IScriptFileService scriptFileService;

    public static ModeController build() {
        inputController = new CollectionInputController(consoleService);
        collectionService = new CollectionController();
        requestService = new RequestService(DMS_Configuration.build());
        commandService = new CommandService(addCommands());
        commandService.addCommand(new help(commandService));
        scriptFileService = new ScriptFileService(consoleService, commandService);

        modeController = new ModeController(requestService,
                commandService,
                collectionService,
                consoleService, scriptFileService);
        commandService.addCommand(new executeScript(modeController));
        return modeController;
    }

    private static List<BaseCommand> addCommands() {
        List<BaseCommand> commands = new ArrayList<>();
        commands.add(new add(inputController));
        commands.add(new addIfMax(inputController));
        commands.add(new addIfMin(inputController));
        commands.add(new clear());
        commands.add(new exit());
        commands.add(new filterByType());
        commands.add(new groupCountingByType());
        commands.add(new countGreaterThanEmployeesCount(inputController));
        commands.add(new head());
        commands.add(new info());
        commands.add(new removeById());
        commands.add(new save(requestService));
        commands.add(new show());
        commands.add(new update(inputController));
        commands.add(new printFieldAscendingType());

        return commands;
    }
    public static CollectionController getCollectionService() {
        return collectionService;
    }


}