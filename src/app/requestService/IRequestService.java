package app.requestService;

import DMS.models.Organization;

import java.io.FileNotFoundException;
import java.util.Vector;

public interface IRequestService {
    Vector<Organization> createRequest();
    Vector<Organization> readRequest() throws FileNotFoundException;
    void updateRequest(Vector<Organization> current);
    void deleteRequest(Vector<Organization> collection);
}
