package requestService;

import models.Organization;

import java.util.Vector;

public interface IRequestService {
    Vector<Organization> createRequest();
    Vector<Organization> readRequest();
    void updateRequest();
    void deleteRequest();

}
