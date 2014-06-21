/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.persistence;

import it.micheleorsi.restfuljavagaebestpractices.persistence.clouddatastore.CloudDatastoreDAOFactory;
import it.micheleorsi.restfuljavagaebestpractices.persistence.clouddatastore.CloudDatastoreUserDAO;
import it.micheleorsi.restfuljavagaebestpractices.persistence.cloudsql.CloudSqlDAOFactory;

/**
 * @author micheleorsi
 *
 */
public abstract class DAOFactory {
	
	// List of DAO types supported by the factory
	public enum Type {
		CLOUD_DATASTORE,
		CLOUD_SQL;
	}
	public static final int CLOUD_DATASTORE = 1;
	public static final int CLOUD_SQL = 2;
	
	public abstract CloudDatastoreUserDAO getUserDAO();
	public abstract MessageDAO getMessageDAO();
	
	public static DAOFactory getDAOFactory(
			Type whichFactory) {
		  
		    switch (whichFactory) {
		      case CLOUD_DATASTORE: 
		          return new CloudDatastoreDAOFactory();
		      case CLOUD_SQL    : 
		          return new CloudSqlDAOFactory(); 
		      default           : 
		          return null;
		    }
		  }

}
