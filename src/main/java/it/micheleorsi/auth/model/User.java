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
        Editor, Visitor, Contributor
    };
 
    private String userId;          // id
    private String name;            // name
    private String emailAddress;    // email
    private Set<Role> roles;        // roles
    

    // getters/setters here
 
    @Override
    public String getName() {
        return null;
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
 
}
