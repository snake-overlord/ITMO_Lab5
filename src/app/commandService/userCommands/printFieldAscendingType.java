package app.commandService.userCommands;

import DMS.models.Organization;
import DMS.models.OrganizationType;
import app.commandService.BaseCommand;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

public class printFieldAscendingType extends BaseCommand {
    public static final String name = "print_field_ascending_type";
    public static final String description = ": print values of the type field of all elements in ascending order";
    public printFieldAscendingType(){
        super(name, description);
    }
    @Override
    public void execute(String[] command) {
        if (command.length != 1) {
            System.err.print("0 argument required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            SortedMap<String, OrganizationType> map = new TreeMap<>();
            Vector<Organization> collection = controller.getVector();
            for (Organization org : collection) {
                map.put(org.getType().name(), org.getType());
            }
            System.out.print(map.values());
        }

        }
    }