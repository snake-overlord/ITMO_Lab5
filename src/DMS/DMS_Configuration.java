package DMS;

import DMS.DMS_ControlService.ControlService;
import DMS.DMS_ControlService.IControlService;
import DMS.collectionService.CollectionFileService;

import java.util.Objects;

public class DMS_Configuration {
    private static IControlService controlService;

    public static String getEnv() {
        String env = System.getenv("LAB5_ITEMS_PATH");
        if (Objects.nonNull(env)) {
            System.out.printf("Будет использовано имя файла: %s.", env);
            return env;
        } else {
            System.out.printf("Будет использовано стандартное имя файла: %s.\n", "C:\\Users\\79832\\test\\test.xml");
            return "C:\\Users\\79832\\test\\test.xml";
        }
        }
    public static IControlService build(){
        String fileName = getEnv();
        controlService = new ControlService(new CollectionFileService(fileName));
        return controlService;
    }
}
