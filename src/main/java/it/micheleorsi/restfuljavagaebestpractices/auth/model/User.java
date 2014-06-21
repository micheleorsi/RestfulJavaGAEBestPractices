/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.auth.model;

import java.security.Principal;
import java.util.Set;

/**
 * @author
 * 
 */
public class User implements Principal {
	// Role
	public enum Role {
		EDITOR(Values.EDITOR), 
		VISITOR(Values.VISITOR), 
		CONTRIBUTOR(Values.CONTRIBUTOR);

		private Role(String label) {
		}

		public static class Values {
			private Values() {
			}
			
			public static final String EDITOR = "EDITOR";
			public static final String VISITOR = "VISITOR";
			public static final String CONTRIBUTOR = "CONTRIBUTOR";
		}
	};

	private String userId; 
	private String name; 
	private String emailAddress; 
	private String secret; 
	private Set<Role> roles; 

	public User(String emailAddress, String password, Set<Role> roles) {
		this(emailAddress, password);
		this.roles = roles;
	}

	public User(String emailAddress, String secret) {
		this.emailAddress = emailAddress;
		this.secret = secret;
	}
	
	public User() {
	}

	// getters/setters here

	@Override
	public String getName() {
		return name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
