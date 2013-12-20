/**
 * 
 */
package it.micheleorsi.auth.model;

import java.util.Set;

/**
 * @author
 *
 */
public class User implements java.security.Principal {
    // Role
    public enum Role {
        EDITOR(Values.EDITOR), 
        VISITOR(Values.VISITOR),
        CONTRIBUTOR(Values.CONTRIBUTOR);
        
        private Role(String label) {}
        
        public static class Values {
            public static final String EDITOR = "Editor";
            public static final String VISITOR = "Visitor";
            public static final String CONTRIBUTOR = "Contributor";
        }
    };
 
    private String userId;          // id
    private String name;            // name
    private String emailAddress;    // email
    private String password;		// password
    private Set<Role> roles;        // roles
    
    public User(String emailAddress, String password) {
    	this.emailAddress = emailAddress;
    	this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 
}
