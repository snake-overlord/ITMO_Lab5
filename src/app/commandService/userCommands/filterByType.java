package app.commandService.userCommands;

import DMS.models.OrganizationType;
import app.commandService.BaseCommand;
import DMS.models.Organization;
import app.commandService.ICollectionRepository;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Vector;
/**
 * <b>name</b> <b>description</b>
 */
public class filterByType extends BaseCommand {
    public ICollectionRepository repository;
    public static final String name = "filter_by_type";
    public static final String description = "type (COMMERCIAL, PUBLIC, etc.) : output elements with the specified type";
    public filterByType(ICollectionRepository repository){
        super(name, description);
        this.repository = repository;
    }
    @Override
    public void execute(String[] command){
        if(command.length!=2){
            System.err.print("1 argument required. Provided: " + (command.length-1));
            System.out.print("Try again >");
        } else if (!OrganizationType.names().contains(command[1])) {
            System.err.print("Invalid type! Try again.\n");
        } else{
            Vector<Organization> collection = repository.getVector();
            for (Organization org : collection) {
                if (Objects.equals(org.getType().toString(), command[1])) {
                    System.out.print("ID: " + org.getId() + " Name: " + org.getName());
                }
        }

        }
    }
    }


