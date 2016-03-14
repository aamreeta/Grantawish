package models.persistence;

/**
 * The PersistenceContextFactory enables creation of a PeristsnceContext.
 * Currently it supports creation of MongoDB Persistence Context. in future if any other 
 * database needed to be supported it can be done very easily with minimum code change.
 * 
 * There should be only one PersistenceCtxFactory at any point of time.
 *  
 * @author Aamreeta Gautam
 *
 */

public class PersistenceCtxFactory {
	private static final String MONGODB="mongodb";

private static class LazyCtxFactoryHolder {
		
        static final PersistenceCtxFactory INSTANCE = new PersistenceCtxFactory();
    }
	/*public static PersistenceContext getDefaultPersistenceContext(){
		
	}*/
    public static PersistenceCtxFactory getPersistenceFactory() {
    	
        return LazyCtxFactoryHolder.INSTANCE;
    	//return new PersistenceContext(hostName,port,dbName);
    }
    
    public PersistenceContext getPersistenceContext(String str){
    	
    	PersistenceContext mongoPersistenceContext=null;
    	if(str.equalsIgnoreCase(MONGODB)){
    		mongoPersistenceContext=MongoPersistenceContext.getPersistenceContext();
    		
    	}
    	 return mongoPersistenceContext;
    }
   
}
