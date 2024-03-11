package app.requestService;

import DMS.DMS_ControlService.IControlService;
import DMS.models.Organization;

import java.io.FileNotFoundException;
import java.util.Vector;

public class RequestService implements IRequestService{
    private final IControlService controlService;
    public RequestService(IControlService controlService) {
        this.controlService = controlService;
    }
    @Override
    public Vector<Organization> readRequest() throws FileNotFoundException {
        return controlService.read();
    }
    @Override
    public Vector<Organization> createRequest(){
        return controlService.create();
    }
    @Override
    public void updateRequest(Vector<Organization> current){
        controlService.update(current);
    }
    @Override
    public void deleteRequest(Vector<Organization> collection){
        controlService.delete(collection);
    }
}
