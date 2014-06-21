/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.auth.filters;

import java.security.Principal;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import it.micheleorsi.restfuljavagaebestpractices.auth.model.User;

/**
 * @author micheleorsi
 *
 */
public class Authorization implements javax.ws.rs.core.SecurityContext {
	
	private static final Logger LOG = Logger.getLogger(Authorization.class.getName());
	 
    private final User user;
    private final Boolean secure;
    private final String authSchema;
 
    public Authorization(Boolean secure, String authSchema, User user) {
    	this.secure = secure;
        this.user = user;
        this.authSchema = authSchema;
    }
 
    @Override
    public String getAuthenticationScheme() {
        return authSchema;
    }
 
    @Override
    public Principal getUserPrincipal() {
        return user;
    }
 
    @Override
    public boolean isSecure() {
        return secure;
    }
 
    @Override
    public boolean isUserInRole(String role) {    	
    	if(this.authSchema == null) {
        	LOG.info("auth is null");
            throw new WebApplicationException(Status.UNAUTHORIZED);
        } else if(this.user == null) {
        	LOG.info("user is null");
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
    	
    	boolean returnValue = false;
    	
    	// this user has this role?
        returnValue = user.getRoles().contains(User.Role.valueOf(role.toUpperCase()));
        
        return returnValue;
    }
}
