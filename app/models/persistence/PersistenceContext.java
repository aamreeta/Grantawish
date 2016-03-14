package models.persistence;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;

import models.OrganizationProfile;

public interface PersistenceContext {
	 public <T> void save(T entity);
	 public  <T> List<T> find(Class<T> modelClass,String field,String value);
	 public <T>Key<?> exists(T entity);
	 public <T> boolean exists(Class<T> modelClass,ObjectId id);
	 public <T> Query<T> findAll(Class<T> modelClass);
	 public <T>List<T> getdAll(Class<T> modelClass);
	 //public <T>void updateById(Class<T> modelClass,String id,String fieldTobeUpdated,String Updatedval);
	 public <T> void deleteByQuery(Query<T> query);
	 public <T> void deleteById(Class<T> modelClass,String id);
	 public <T> void delete(T entity);
}
