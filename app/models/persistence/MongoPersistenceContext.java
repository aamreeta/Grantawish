package models.persistence;




import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.*;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.MongoClient;


/**
 * MongoPersistenceContext enables apis to connect to a MongoDb database.
 * It exposes apis to do CRUD operations on the database.
 * 
 * @author Aamreeta Gautam
 * @param <T>
 * @param <T>
 *
 */
public class MongoPersistenceContext implements PersistenceContext{
	 private MongoClient mongo;
	 private Morphia morphia;
	 private Datastore datastore;
	
	private MongoPersistenceContext(){
		//TODO: Read the configurations from a property file.
		//Change in next iteration
        this.mongo = new MongoClient("127.0.0.1", 27017);
        this.morphia = new Morphia();
		this.datastore = this.morphia.createDatastore(this.mongo, "GrantAWishTest");
		this.datastore.ensureIndexes();   
		this.datastore.ensureCaps(); 
		
	}
	private static class LazyHolder {
		
        static final PersistenceContext INSTANCE = new MongoPersistenceContext();
    }
	/*public static PersistenceContext getDefaultPersistenceContext(){
		
	}*/
    public static PersistenceContext getPersistenceContext() {
    	
        return LazyHolder.INSTANCE;
    	
    }
    
    public <T> void save(T entity){
    	
    	this.datastore.save(entity);
    }
    public <T> void deleteById(Class<T> modelClass,String id){
    	
    	ObjectId objectid=new ObjectId(id);
    	this.datastore.delete(modelClass, objectid);
    	
    	
    	 	
    }
    
    public <T> List<T> find(Class<T> modelClass,String field,String value){
    
    	List<T> results=this.datastore.createQuery(modelClass).filter(field, value).asList();
    	//List<OrganizationProfile> results=this.datastore.find(OrganizationProfile.class).asList();
    	return results;	              
    	
    	
    }
    public <T> boolean exists(Class<T> modelClass,ObjectId id){
       // this.datastore.find(modelClass, "id", id);
        T enity=this.datastore.get(modelClass,id);
        if(enity != null)
        	
    	      return true;
        
        else 
        	return false;
    	
    }
    public <T> Key<?> exists(T entity){
    	
    	
    return	this.datastore.exists(entity);
    }
    
    public <T> Query<T> findAll(Class<T> modelClass){
    	return this.datastore.find(modelClass);
    }
    public <T>List<T> getdAll(Class<T> modelClass){
    	//Query query=this.datastore.find(modelClass);
    	List<T> results=this.datastore.createQuery(modelClass).asList();
    	return results;
    }
   /**
    * TODO: implement update
    */
	/*public <T>void updateById(Class<T> modelClass,String id,String fieldTobeUpdated,String Updatedval){
    	UpdateOperations<T> update=this.datastore.createUpdateOperations(modelClass);
    	update.set(fieldTobeUpdated, Updatedval);
    	Query<T> query=this.datastore.createQuery(modelClass).filter("_id",id);
    	this.datastore.findAndModify(query, update);
    }*/
    public <T> void deleteByQuery(Query<T> query){
    	this.datastore.delete(query);
    }

	@Override
	public <T> void delete(T entity) {
		this.datastore.delete(entity);
		
	}
   
}
