package DMS.collectionService;

import DMS.models.Organization;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Vector;
/**
 * Class for collection file handling.
 */
public class CollectionFileService implements ICollectionFileService{
    String file;

    /**
     *
     * @param name - xml file name.
     */
    public CollectionFileService(String name){
        this.file = name;
    }


    @Override
    public Vector<Organization> readVector(){
        Vector<Organization> deserialized = new Vector<>();

        try(FileInputStream fis = new FileInputStream(file);
            XMLDecoder decoder = new XMLDecoder(fis)) {
            final boolean[] e = {false};
            decoder.setExceptionListener(ex -> e[0] = true);
            deserialized = (Vector<Organization>) decoder.readObject();
            if(e[0]){
                System.out.print("XML data is corrupted. Continue with an empty collection...\n");
                return new Vector<>();
            }
            for(Organization org : deserialized){
                if(org.validate())
                    System.out.print(org);
                else
                    System.out.printf("Organization %s has invalid fields! \n", org.getName());
            }
        } catch (IOException e) {
            System.out.println("IO error occurred: " + e.getMessage());
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
