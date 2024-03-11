package app.commandService;

import DMS.models.Organization;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

public class CollectionController implements ICollectionController{
    LocalDateTime date = LocalDateTime.now();
    Vector<Organization> collection = new Vector<>();

    @Override
    public Vector<Organization> getVector(){
        return new Vector<>(collection);
    }
    @Override
    public void addItem(Organization org){
        org.setId(UniqueID.createID());
        org.setCreationDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        collection.add(org);
    }
    @Override
    public void addItems(Vector<Organization> items){
        if(Objects.nonNull(items))
            items.forEach(this::addItem);
    }
    @Override
    public void deleteItem(Organization org){
        collection.remove(org);
    }
    @Override
    public void clearCollection() {
        collection.clear();
    }
    @Override
    public LocalDateTime getDate() {
        return date;
    }
    @Override
    public void setDate() {
        date = LocalDateTime.now();
    }
    @Override
    public void addItem(Organization org, Long id){
        org.setId(id);
        org.setCreationDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        collection.add(org);
    }


    public static class UniqueID {
        private static AtomicLong idCounter = new AtomicLong(1);

        public static Long createID()
        {
            return idCounter.getAndIncrement();
        }
    }
}
