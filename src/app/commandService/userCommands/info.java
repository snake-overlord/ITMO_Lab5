package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;
import app.commandService.ICollectionRepository;

import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class info extends BaseCommand {
    public ICollectionRepository repository;
    public static final String name = "info";
    public static final String description = ": information about the collection (type, initialization date, number of elements, etc.)";

    public info(ICollectionRepository repository) {
        super(name, description);
        this.repository = repository;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 argument required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            Vector<Organization> collection = repository.getVector();
            String result = "";
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            result += String.format("Тип: %s\n", collection.getClass());
            result += String.format("Дата инициализации: %s\n", repository.getDate().format(format));
            result += String.format("Количество элементов: %d", collection.size());
            System.out.print(result+"\n");
        }
    }
}