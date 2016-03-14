package PersistenceStore;

import static org.junit.Assert.*;
import models.OrganizationProfile;
import models.persistence.PersistenceContext;
import models.persistence.PersistenceCtxFactory;

import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import java.util.*;

public class OrganizationProfileTest {
	/* PersistenceContext provides the apis to connect and perform different operations like CRUD
	 *  on a databse ex: Mongodb
	 *  PersistenceCtxFactory provides apis to get hold of the appropriate PersistenceContext instance.
	 *  
	 * */
    PersistenceCtxFactory pCtxFactory=null;
	PersistenceContext pCtx=null;
	@Before 
	public void init(){
		//Create Persistence Context factory
		pCtxFactory=PersistenceCtxFactory.getPersistenceFactory();
		
		//System.out.println("********Got the factory*****"+pCtxFactory);
		//get the appropriate mongodb persistence context 
		pCtx=pCtxFactory.getPersistenceContext("mongodb");
		Logger.debug("************Got the persistence Context******"+pCtx);
	    createDataSet();
		
	}
	private void createDataSet() {
		Logger.debug("******Creating dataset******");
		System.out.println("******Creating dataset******");
		OrganizationProfile orgProfile=createTestOrg("Test Organization","Test Address","www.testurl.org","Test about");
		pCtx.save(orgProfile);
		orgProfile=createTestOrg("Test Organization2","Test Address2","www.testurl2.org","Test about2");
		pCtx.save(orgProfile);
	}
	public OrganizationProfile createTestOrg(String name,String address,String website,String about){
		OrganizationProfile orgProfile=new OrganizationProfile();
		orgProfile.setName(name);
		orgProfile.setAddress(address);
		orgProfile.setWebsite(website);
		orgProfile.setAbout(about);
		
		return orgProfile;
	}
	@Test
	public void testCreate() {
		Logger.debug("******testCreate()******");
		System.out.println("******testCreate()******");
		OrganizationProfile orgProfile=createTestOrg("Test Org Name New","Test Address","www.testurl.org","Test about");
		pCtx.save(orgProfile);
		//Test if the saved object now exists in the db
		Key<?> key=pCtx.exists(orgProfile);
		assertNotNull(key);
		
	}
	
	@Test
	public void testGetAll(){
		Logger.debug("*********testGetAll()********");
		System.out.println("*********testGetAll()********");
		List<OrganizationProfile> res=pCtx.getdAll(OrganizationProfile.class);
		if(res!=null){
			for(OrganizationProfile org:res){
				Logger.debug("***getAll**org id::"+org.getId()+"::Address::"+org.getAddress());
				System.out.println("***getAll**org id::"+org.getId()+"::Address::"+org.getAddress());
			}
		}
		
	}
	@Test
	public void testDeleteById() {
		Logger.debug("*********testDeleteById()********");
		System.out.println("*********testDeleteById()********");
		List<OrganizationProfile> res=pCtx.find(OrganizationProfile.class, "name","Test Organization" );
		System.out.println(res.get(0).getName()+"::"+res.get(0).getId());
		pCtx.deleteById(OrganizationProfile.class,res.get(0).getId().toString()); 
		assertFalse("***testDeleteById()**",pCtx.exists(OrganizationProfile.class, res.get(0).getId()));
		
	}
	

	@Test
	public void testFind(){
		Logger.debug("*********testFind()********");
		System.out.println("*********testFind()********");
		List<OrganizationProfile> res=pCtx.find(OrganizationProfile.class, "name","Test Organization");
		Logger.debug("******The results*****"+res);
	    System.out.println("******The results*****"+res);
	if(res!=null){
		for(OrganizationProfile org:res){
			Logger.debug("*org id::"+org.getId()+"::Address::"+org.getAddress());
			System.out.println("*org id::"+org.getId()+"::Address::"+org.getAddress());
		}
	}
		
	}
 @After
 public void testDeleteAll(){
	 Logger.debug("*********testDeleteAll()********");
	 System.out.println("*********testDeleteAll()********");
	 Query<OrganizationProfile> query=pCtx.findAll(OrganizationProfile.class);
	 pCtx.deleteByQuery(query);
 }
}
