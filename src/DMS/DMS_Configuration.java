package DMS;

import DMS.DMS_ControlService.ControlService;
import DMS.DMS_ControlService.IControlService;
import DMS.collectionService.CollectionFileService;

import java.util.Objects;

public class DMS_Configuration {
    private static IControlService controlService;
    public static String getEnv() {
        String env = "C:\\Users\\79832\\test\\test.xml";
        return env;
        }
    public static IControlService build(){
        String fileName = getEnv();
        controlService = new ControlService(new CollectionFileService(fileName));
        return controlService;
    }
}
