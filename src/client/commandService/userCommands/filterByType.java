package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;

import java.util.Objects;
import java.util.Vector;

public class filterByType extends BaseCommand {

    public static String name = "filter_by_type";
    public filterByType(){
        super(name);
    }
    @Override
    public void execute(String[] command) {
            Vector<Organization> collection = requestService.readRequest();
            for (Organization org : collection) {
                if (Objects.equals(org.getType().toString(), command[1])) {
                    System.out.print("ID: " + org.getId() + " Name: " + org.getName());
                }
            }
        }
    }


