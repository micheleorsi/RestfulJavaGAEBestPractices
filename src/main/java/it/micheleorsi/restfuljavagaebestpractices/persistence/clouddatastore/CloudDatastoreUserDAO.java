/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.persistence.clouddatastore;

import it.micheleorsi.restfuljavagaebestpractices.auth.model.User;
import it.micheleorsi.restfuljavagaebestpractices.persistence.UserDAO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author micheleorsi
 *
 */
public class CloudDatastoreUserDAO implements UserDAO {
	
	private Map<String,User> userDb = new HashMap<String, User>();
	
	public CloudDatastoreUserDAO() {
		// base64: MTpwYXNzd29yZA==
		userDb.put("1", new User("myemail1@email.com", "password", new HashSet<User.Role>(Arrays.asList(User.Role.VISITOR)))); 
		// base64: MjpwYXNzd29yZA==
		userDb.put("2", new User("myemail2@email.com", "password", new HashSet<User.Role>(Arrays.asList(User.Role.CONTRIBUTOR)))); 
		// base64: MzpwYXNzd29yZA==
		userDb.put("3", new User("myemail3@email.com", "password", new HashSet<User.Role>(Arrays.asList(User.Role.EDITOR)))); 
		// base64: NDpwYXNzd29yZA==
		userDb.put("4", new User("myemail4@email.com", "password", new HashSet<User.Role>(Arrays.asList(User.Role.VISITOR)))); 
	}
	
	public User getById(String key) {
		return userDb.get(key);
	}
}
