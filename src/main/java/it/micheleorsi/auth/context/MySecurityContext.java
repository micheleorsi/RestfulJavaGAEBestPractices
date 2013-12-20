/**
 * 
 */
package it.micheleorsi.auth.context;

import java.security.Principal;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import it.micheleorsi.auth.model.User;

/**
 * @author micheleorsi
 *
 */
public class MySecurityContext implements javax.ws.rs.core.SecurityContext {
	
	private final Logger log = Logger.getLogger(MySecurityContext.class.getName());
	 
    private final User user;
    private final Boolean secure;
    private final String authSchema;
 
    public MySecurityContext(Boolean secure, String authSchema, User user) {
    	log.info("init");
    	
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
    	log.info("check role");
    	
    	if(this.authSchema == null || this.user == null){
        	log.info("auth is null");
            throw new WebApplicationException(Status.UNAUTHORIZED);
        } 
    	
    	boolean returnValue = false;
 
        try {
            // this user has this role?
        	returnValue = user.getRoles().contains(User.Role.valueOf(role));
        } catch (Exception e) {
        	log.warning(e.getMessage());
        }
        log.info("return "+returnValue);
        return returnValue;
    }
}
