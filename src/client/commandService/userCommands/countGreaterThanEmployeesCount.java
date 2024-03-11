package app.commandService.userCommands;

import DMS.models.Organization;
import app.commandService.BaseCommand;
import app.commandService.CollectionInputController;
import app.consoleService.ConsoleService;
import app.consoleService.IConsoleService;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Vector;

public class countGreaterThanEmployeesCount extends BaseCommand {
    public static final String name = "count_greater_than_employees_count";
    public static final String description = "count : output the number of elements with Employee Count field greater than the specified one";
    CollectionInputController collectionInputController;
    public countGreaterThanEmployeesCount(CollectionInputController inputController) {
        super(name, description);
        this.collectionInputController = inputController;
    }

    @Override
    public void execute(String[] command) {
        if (command.length != 2) {
            System.err.print("1 argument required. Provided: " + (command.length - 1));
            System.out.print("Try again >");
        } else {
            Long ecount;
            Vector<Organization> collection = controller.getVector();
            ParsePosition pos = new ParsePosition(0);
            NumberFormat.getInstance().parse(command[1], pos);
            if(pos.getIndex()==command[1].length()|!(command[1].length()>19)){
                ecount = Long.parseLong(command[1]);
            }
            else{
                System.err.print("This is not a number! Try again\n");
                return;
            }
            int count = 0;
            for (Organization org : collection) {
                if(org.getEmployeesCount()>ecount){
                    ++count;
                }
                }
            System.out.printf("Elements with Employee Count field greater than %s: %s", ecount, count);
        }
    }
}
