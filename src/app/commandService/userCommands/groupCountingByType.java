package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class groupCountingByType extends BaseCommand {
    public static final String name = "group_counting_by_type";
    public static final String description = ": group the elements of the collection by the value of the type field, print the number of elements in each group";

    public groupCountingByType() {
        super(name, description);
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 argument required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            Vector<Organization> collection = controller.getVector();
            Map<String, Integer> typeMap = new HashMap<>();
            for (Organization org : collection) {
                typeMap.put(org.getType().name(), typeMap.getOrDefault(org.getType().name(),0)+1);
            }
            for (Map.Entry<String, Integer> entry : typeMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                }

            }
            }