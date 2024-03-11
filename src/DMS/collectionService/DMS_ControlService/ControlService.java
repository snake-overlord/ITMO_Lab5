package DMS.DMS_ControlService;

import DMS.collectionService.ICollectionFileService;
import DMS.models.Organization;

import java.io.FileNotFoundException;
import java.util.Vector;

public class ControlService implements IControlService{
    ICollectionFileService collectionFileService;
    public ControlService(ICollectionFileService collectionFileService){
        this.collectionFileService = collectionFileService;
    }
    @Override
    public Vector<Organization> create(){
        Vector<Organization> collection = new Vector<Organization>();
        collectionFileService.writeVector(collection);
        return collection;
    }

    @Override
    public Vector<Organization> read() throws FileNotFoundException {
        return collectionFileService.readVector();
    }
    @Override
    public  void update(Vector<Organization> current){
        collectionFileService.writeVector(current);
    }
    @Override
    public void delete(Vector<Organization> collection){
        collectionFileService.deleteVector(collection);
    }
}
