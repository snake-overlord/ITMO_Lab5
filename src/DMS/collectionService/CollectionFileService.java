package DMS.collectionService;

import DMS.models.Address;
import DMS.models.Organization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Vector;

public class CollectionFileService implements ICollectionFileService{
    String file;

    public CollectionFileService(String name){
        this.file = name;
    }
    @Override
    public Vector<Organization> readVector() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        Vector<Organization> deserialized = new Vector<>();
        try (XMLDecoder decoder = new XMLDecoder(fis, null, null, DMS.models.Organization.class.getClassLoader())) {
            deserialized = (Vector<Organization>) decoder.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deserialized;
    }

    @Override
    public void writeVector(Vector<Organization> data) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteVector(Vector<Organization> data) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.remove(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
