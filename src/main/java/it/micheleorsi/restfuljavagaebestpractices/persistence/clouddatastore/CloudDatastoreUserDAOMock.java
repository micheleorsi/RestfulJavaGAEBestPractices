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
public class CloudDatastoreUserDAOMock implements UserDAO {
	
	private Map<String,User> userDb = new HashMap<String, User>();
	
	public CloudDatastoreUserDAOMock() {
		// base64: bXluYW1lMTpwYXNzd29yZA==
		User user = new User("myname1", "password", new HashSet<User.Role>(Arrays.asList(User.Role.VISITOR)));
		user.setKey("1");
		userDb.put(user.getKey(), user); 
		// base64: bXluYW1lMjpwYXNzd29yZA==
		user = new User("myname2", "password", new HashSet<User.Role>(Arrays.asList(User.Role.CONTRIBUTOR)));
		user.setKey("2");
		userDb.put(user.getKey(), user);
		// base64: 
		user = new User("myname3", "password", new HashSet<User.Role>(Arrays.asList(User.Role.EDITOR))); 
		user.setKey("3");
		userDb.put(user.getKey(), user);
		// base64: 
		user = new User("myname4", "password", new HashSet<User.Role>(Arrays.asList(User.Role.VISITOR)));
		user.setKey("4");
		userDb.put(user.getKey(), user);
		
		user = new User("johndoe", "foobar", new HashSet<User.Role>(Arrays.asList(User.Role.CONTRIBUTOR)));
		user.setKey("5");
		userDb.put(user.getKey(), user);
	}
	
	public User getById(String key) {
		return userDb.get(key);
	}
}
