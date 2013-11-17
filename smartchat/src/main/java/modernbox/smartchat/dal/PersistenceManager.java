package modernbox.smartchat.dal;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersistenceManager {
	
	public static final boolean DEBUG = true;
	private static final PersistenceManager singleton = new PersistenceManager();
	protected EntityManagerFactory emf;
	  
	public static PersistenceManager getInstance() {
		return singleton;
	}
	  
	private PersistenceManager() {
	}
	 
	public synchronized EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	 
	public synchronized void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}
	  
	public synchronized void closeEntityManagerFactory() {
		if (emf != null) {
			emf.close();
			emf = null;
			if (DEBUG)
				System.out.println("n*** Persistence finished at " + new java.util.Date());
		}
	}

	public static EntityManager createEntityManager() {
		return PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
}
