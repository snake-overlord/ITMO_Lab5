package app.commandService;

import DMS.models.Address;
import DMS.models.Coordinates;
import DMS.models.OrganizationType;
import app.consoleService.ConsoleService;
import app.consoleService.IConsoleService;

public class CollectionInputController {
    private final IConsoleService consoleService;
    public CollectionInputController(IConsoleService consoleService){
        this.consoleService = consoleService;
    }
    public String getName(){
        return getString("name", false);
    }
    public Coordinates getCoordinates(){
        return new Coordinates(getDouble("x"), getInteger("y"));
    }
    public Address getAddress(){
        String zip = getString("zip code", true);
        while(zip.length()>18){
            System.err.println("String length cannot be more than 18!\n>");
            zip = getString("zip code", true);
        }
        return new Address(zip);
    }
    public Long getAnnualTurnover(){
        long annualTurnover = getLong("annual turnover");
        while(annualTurnover<=0){
            System.err.println("Annual turnover cannot be less than 0!\n>");
            annualTurnover = getLong("annual turnover");
        }
        return annualTurnover;
    }
    public Long getEmployeeCount(){
        long employeecount = getLong("employee count");
        while(employeecount<=0){
            System.err.println("Employee count cannot be less than 0!\n>");
            employeecount = getLong("employee count");
        }
        return employeecount;
    }
    public OrganizationType getType(){
        String fieldName = "organization type";
        String line;
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        while(!consoleService.getScanner().hasNext()){
            System.err.println("Can't be null!\n");
            System.out.printf("Enter %s:%s", fieldName, "\n>");
            consoleService.readLine();
        }
        line = consoleService.readLine().trim();
        while(!OrganizationType.names().contains(line)){
            System.out.print(line);
            System.out.print(OrganizationType.names());
            System.err.println("Invalid type!\n");
            System.out.printf("Enter %s:%s", fieldName, ">");
            line = consoleService.readLine().trim();
        }
        System.out.printf("Got value: %s\n>", line);
        return OrganizationType.valueOf(line);

    }




    private Integer getInteger(String fieldName) {
        String line;
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        while(!consoleService.getScanner().hasNextInt()){
            System.err.println("That's not an Integer!\n");
            System.out.printf("Enter %s:%s", fieldName, "\n>");
            consoleService.readLine();
        }
        line = consoleService.readLine().trim();
        System.out.printf("Got value: %s\n", line);
        return Integer.parseInt(line);
    }
    private Long getLong(String fieldName) {
        String line;
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        while(!consoleService.getScanner().hasNextLong()){
            System.err.println("That's not a Long!\n");
            System.out.printf("Enter %s:%s", fieldName, "\n>");
            consoleService.readLine();
        }
        line = consoleService.readLine().trim();
        System.out.printf("Got value: %s\n", line);
        return Long.parseLong(line);
    }
    private Double getDouble(String fieldName) {
        String line;
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        while(!consoleService.getScanner().hasNextDouble()){
            System.out.println("That's not a Double!\n");
            System.out.printf("Enter %s:%s", fieldName, "\n>");
            consoleService.readLine();
        }
        line = consoleService.readLine();
        System.out.printf("Got value: %s\n>", line);
        return Double.parseDouble(line);
    }
    private String getString(String fieldName, boolean canBeNull) {
        String line;
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        if(!canBeNull)
            while(!consoleService.getScanner().hasNext()){
                System.err.println("Can't be null!\n");
                System.out.printf("Enter %s:%s", fieldName, "\n>");
                consoleService.readLine();
            }
        line = consoleService.readLine().trim();
        System.out.printf("Got value: %s \n", line);
        return line;
    }


}
