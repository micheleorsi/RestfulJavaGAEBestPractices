/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.persistence;

import it.micheleorsi.restfuljavagaebestpractices.auth.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author micheleorsi
 *
 */
public class UserRepository {
	
	private Map<String,User> userDb = new HashMap<String, User>();
	
	public UserRepository() {
		userDb.put("1", new User("myemail1@email.com", "password"));
		userDb.put("2", new User("myemail2@email.com", "password"));
		userDb.put("3", new User("myemail3@email.com", "password"));
		userDb.put("4", new User("myemail4@email.com", "password"));
	}
	
	public User getUser(String key) {
		return userDb.get(key);
	}
	
	public User getUser(String email, String password) {
		
		for (User localUser : userDb.values()) {
			if(localUser.getEmailAddress().equals(email) && 
					localUser.getPassword().equals(password)) {
				return localUser;
			}
		}
		
		return null;
	}
	

}
