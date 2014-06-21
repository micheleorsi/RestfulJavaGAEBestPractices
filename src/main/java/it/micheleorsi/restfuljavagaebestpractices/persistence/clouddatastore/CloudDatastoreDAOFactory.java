/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.persistence.clouddatastore;

import it.micheleorsi.restfuljavagaebestpractices.persistence.DAOFactory;
import it.micheleorsi.restfuljavagaebestpractices.persistence.MessageDAO;

/**
 * @author micheleorsi
 *
 */
public class CloudDatastoreDAOFactory extends DAOFactory {

	@Override
	public CloudDatastoreUserDAO getUserDAO() {
		return new CloudDatastoreUserDAO();
	}

	@Override
	public MessageDAO getMessageDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
