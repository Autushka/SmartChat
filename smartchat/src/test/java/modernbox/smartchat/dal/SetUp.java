
package modernbox.smartchat.dal;

import javax.persistence.EntityManagerFactory;

import modernbox.smartchat.dal.PersistenceManager;

public class SetUp {
	private static EntityManagerFactory emf = new EntityManagerFactoryInit("JPA_Test").createEntityManagerFactory();
	
	public static void setUp() {
    	PersistenceManager.getInstance().setEntityManagerFactory(emf);
	}

}
