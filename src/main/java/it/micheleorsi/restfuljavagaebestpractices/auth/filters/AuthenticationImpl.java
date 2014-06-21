/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.auth.filters;

import it.micheleorsi.restfuljavagaebestpractices.persistence.UserDAO;

import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.DatatypeConverter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * @author micheleorsi
 *
 */
public class AuthenticationImpl implements Authentication {
	
	public enum AuthType {
		SESSION,
		BASIC,
		OAUTH1
	}
	
	private Logger log = Logger.getLogger(AuthenticationImpl.class.getName());
	private final UserDAO userRepo;
	
	@Inject
	public AuthenticationImpl(UserDAO userDAO) {
		log.info("init");
		this.userRepo = userDAO;
	}
	
	@Override
    public ContainerRequest filter(ContainerRequest request) {
		log.info("start ContainerRequest");
 
        //Get the authentification passed in HTTP headers parameters
        String authHeader = request.getHeaderValue("Authorization");
        log.info("authHeader "+authHeader);
        log.info("Content-Type: "+request.getHeaderValue("Content-Type"));
        log.info("Accept: "+request.getHeaderValue("Accept"));
        
        String userKey = null;
        String authSchema = null;
        
        if(authHeader!=null && !authHeader.isEmpty()) {
        	log.info("work on AuthType");
        	StringTokenizer strToken = new StringTokenizer(authHeader," ");
        	AuthType authType = null;
        	
        	if(strToken.hasMoreTokens()) {
        		log.info("strToken.hasMoreTokens");
        		String nextToken = strToken.nextToken().toLowerCase().toUpperCase();
        		try {
        			authType = AuthType.valueOf(nextToken);
        			
        			switch (authType) {
    	    		case BASIC:
    	    			log.info("Basic auth");
    	    			
    	    			// check it uses https
//    	    			if(!request.isSecure()) {
//    	    				log.info("request is not secure");
//    	                    throw new WebApplicationException(Status.UNAUTHORIZED);
//    	    			} FIXME:
    	    			
    	            	authSchema = SecurityContext.BASIC_AUTH;
    	            	// basic auth
    	            	//lap : loginAndPassword
    	            	//Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
    	                authHeader = authHeader.replaceFirst("[B|b]asic ", "");
    	         
    	                //Decode the Base64 into byte[]
    	                byte[] decodedBytes = DatatypeConverter.parseBase64Binary(authHeader);
    	         
    	                //If the decode fails in any case
    	                if(decodedBytes == null || decodedBytes.length == 0){
    	                    return null;
    	                }
    	         
    	                //Now we can convert the byte[] into a splitted array :
    	                //  - the first one is login,
    	                //  - the second one password
    	                String[] lap = new String(decodedBytes).split(":", 2);
    	                
    	                //If login or password fail
    	                if(lap == null || lap.length != 2){
    	                	log.info("lap is null");
    	                    throw new WebApplicationException(Status.UNAUTHORIZED);
    	                } else {
    	                	log.info("userid "+lap[0]);
    	                    log.info("password "+lap[1]);
    	                    userKey = lap[0];
    	                }
    	    			break;
    	
    	    		default:
    	    			break;
        		}
        		} catch(IllegalArgumentException ex) {
        			log.warning("illegal auth type provided "+nextToken);
        		}
        	}
        	
        	
        }
 
        //If the user does not have the right (does not provide any HTTP Basic Auth)
//        if(authHeader!=null && authHeader.matches("^[S|s]ession .*$")) {
//        	log.info("session auth");
//        	authSchema = "SESSION";
//        	
//        	// session auth
//        	 // Get session id from request header
//            final String sessionId = request.getHeaderValue("session-id");
//     
//            Session session = null;
//     
//            if (sessionId != null && sessionId.length() > 0) {
//                // Load session object from repository
////                session = sessionRepository.findOne(sessionId);
//                 
//                // Load associated user from session
//                if (null != session) {
////                    user = userRepository.findOne(session.getUserId());
//                }
//            }
//        }
 
     // Set security context
        log.info("authSchema: "+authSchema);
        request.setSecurityContext(new Authorization(request.isSecure(), authSchema, userRepo.getById(userKey)));
        
        log.info("ready to return");
        return request;
    }
 
    @Override
    public ContainerRequestFilter getRequestFilter() {
        return this;
    }
 
    @Override
    public ContainerResponseFilter getResponseFilter() {
        return null;
    }

}