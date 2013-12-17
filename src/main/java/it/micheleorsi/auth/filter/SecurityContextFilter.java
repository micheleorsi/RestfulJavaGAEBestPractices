/**
 * 
 */
package it.micheleorsi.auth.filter;

import java.util.logging.Logger;

import it.micheleorsi.auth.context.MySecurityContext;
import it.micheleorsi.auth.model.Session;
import it.micheleorsi.auth.model.User;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 * @author micheleorsi
 *
 */
@Provider // register as jersey's provider
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter {
	
	Logger log = Logger.getLogger(SecurityContextFilter.class.getName());
	
	@Override
    public ContainerRequest filter(ContainerRequest request) {
		log.info("start ContainerRequest");
		//GET, POST, PUT, DELETE, ...
        String method = request.getMethod();
        // myresource/get/56bCA for example
        String path = request.getPath(true);
 
        //Get the authentification passed in HTTP headers parameters
        String auth = request.getHeaderValue("authorization");
        
        User user = null;
        
        String authSchema = null;
 
        //If the user does not have the right (does not provide any HTTP Basic Auth)
        if(auth == null || auth.isEmpty() || auth.length()==0){
        	log.info("auth is null");
            throw new WebApplicationException(Status.UNAUTHORIZED);
        } else if(auth.matches("^[B|b]asic .*$")) {
        	log.info("Basic auth");
        	authSchema = SecurityContext.BASIC_AUTH;
        	// basic auth
        	//lap : loginAndPassword
        	//Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
            auth = auth.replaceFirst("[B|b]asic ", "");
     
            //Decode the Base64 into byte[]
            byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);
     
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
            }
            
          //DO YOUR DATABASE CHECK HERE (replace that line behind)...
//          User authentificationResult =  AuthentificationThirdParty.authentification(lap[0], lap[1]);

          //Our system refuse login and password
//          if(authentificationResult == null){
//              throw new WebApplicationException(Status.UNAUTHORIZED);
//          }

          //TODO : HERE YOU SHOULD ADD PARAMETER TO REQUEST, TO REMEMBER USER ON YOUR REST SERVICE...
        } else if(auth.matches("^[S|s]ession .*$")) {
        	log.info("session auth");
        	authSchema = "SESSION";
        	
        	// session auth
        	 // Get session id from request header
            final String sessionId = request.getHeaderValue("session-id");
     
            Session session = null;
     
            if (sessionId != null && sessionId.length() > 0) {
                // Load session object from repository
//                session = sessionRepository.findOne(sessionId);
                 
                // Load associated user from session
                if (null != session) {
//                    user = userRepository.findOne(session.getUserId());
                }
            }
        } else {
        	log.info("auth no matches");
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
 
     // Set security context
        request.setSecurityContext(new MySecurityContext(request.isSecure(), authSchema, user));
        
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