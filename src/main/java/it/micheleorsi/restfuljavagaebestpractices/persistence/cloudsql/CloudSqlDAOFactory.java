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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageDAO getMessageDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
