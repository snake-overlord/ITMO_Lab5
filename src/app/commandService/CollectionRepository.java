package app.commandService;

import DMS.models.Organization;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repository for storing and editing the current collection.
 */
public class CollectionRepository implements ICollectionRepository {
    private LocalDateTime date = LocalDateTime.now();
    private final Vector<Organization> collection = new Vector<>();

    /**
     *
     * @return copy of the collection.
     */
    @Override
    public Vector<Organization> getVector(){
        return new Vector<>(collection);
    }
    /**
     * Adds item to the collection if it is valid.
     */
    @Override
    public void addItem(Organization org){
        org.setId(UniqueID.createID());
        org.setCreationDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        if(org.validate())
            collection.add(org);
    }

    /**
     * Method to merge with another collection.
     */
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

    /**
     * Sets the current date.
     */
    @Override
    public void setDate() {
        date = LocalDateTime.now();
    }

    /**
     * Adds item to the collection by id.
     */
    @Override
    public void addItem(Organization org, Long id){
        org.setId(id);
        org.setCreationDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        if(org.validate())
            collection.add(org);
    }

    /**
     * Generates unique id.
     */
    public static class UniqueID {
        private static final AtomicLong idCounter = new AtomicLong(1);

        public static Long createID()
        {
            return idCounter.getAndIncrement();
        }
    }
}
