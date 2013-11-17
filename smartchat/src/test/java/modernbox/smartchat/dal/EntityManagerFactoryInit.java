package modernbox.smartchat.dal;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.google.appengine.api.utils.SystemProperty;

public class EntityManagerFactoryInit {
	public static final boolean DEBUG = true;
	private String persistenceUnitName = "empty_does_not_exists";
	
	public EntityManagerFactoryInit() {
	}
	
	public EntityManagerFactoryInit(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}
	
	protected EntityManagerFactory createEntityManagerFactory() {
		/*JtdsDataSource dsMYSQL = new JtdsDataSource();
      dsMYSQL.setServerName("127.0.0.1");
      dsMYSQL.setDatabaseName("smartchat");
      dsMYSQL.setUser("root");
      dsMYSQL.setPassword("");*/
		EntityManagerFactory emf = null;
		DataSource ds = null;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production ||
			SystemProperty.environment.value() == SystemProperty.Environment.Value.Development) {
			persistenceUnitName = "transactions-optional";
			System.out.println("n*** App Engine JPA " + new java.util.Date());
		} else {
			try {
				ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/mydb");
			} catch (NamingException e) {
			    if (DEBUG)
				      System.out.println("n*** no context foud at " + new java.util.Date());
			}
		}
		if (ds == null) {
			emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		    if (DEBUG)
			      System.out.println("n*** (PUC) Persistence started at " + new java.util.Date());
		} else {
	        Map<String, DataSource> properties = new HashMap<String, DataSource>();
	    	properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
	    	emf = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
		    if (DEBUG)
			      System.out.println("n*** (CLC) Persistence started at " + new java.util.Date());
		}
		return emf;
	}	
}
