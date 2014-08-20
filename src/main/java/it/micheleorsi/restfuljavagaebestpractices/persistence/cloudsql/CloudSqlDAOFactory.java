/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.persistence.cloudsql;

import it.micheleorsi.restfuljavagaebestpractices.persistence.DAOFactory;
import it.micheleorsi.restfuljavagaebestpractices.persistence.MessageDAO;
import it.micheleorsi.restfuljavagaebestpractices.persistence.clouddatastore.CloudDatastoreUserDAO;

/**
 * @author micheleorsi
 *
 */
public class CloudSqlDAOFactory extends DAOFactory {

	@Override
	public CloudDatastoreUserDAO getUserDAO() {
		return null;
	}

	@Override
	public MessageDAO getMessageDAO() {
		return null;
	}

}
