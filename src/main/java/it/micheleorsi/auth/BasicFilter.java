/**
 * 
 */
package it.micheleorsi.auth;

import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.DatatypeConverter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

/**
 * @author micheleorsi
 *
 */
public class BasicFilter implements ContainerRequestFilter {
	
	private static final Logger log = Logger.getLogger(BasicFilter.class
			.getName());

	protected static String[] decode(String auth) {
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
        return new String(decodedBytes).split(":", 2);
    }
	
	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) {
		//GET, POST, PUT, DELETE, ...
        String method = containerRequest.getMethod();
        // myresource/get/56bCA for example
        String path = containerRequest.getPath(true);
 
        //Get the authentification passed in HTTP headers parameters
        String auth = containerRequest.getHeaderValue("authorization");
 
        //If the user does not have the right (does not provide any HTTP Basic Auth)
        if(auth == null){
        	log.info("auth is null");
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
 
        //lap : loginAndPassword
        String[] lap = this.decode(auth);
        
        //If login or password fail
        if(lap == null || lap.length != 2){
        	log.info("lap is null");
            throw new WebApplicationException(Status.UNAUTHORIZED);
        } else {
        	log.info("userid "+lap[0]);
            log.info("password "+lap[1]);
        }
 
        //DO YOUR DATABASE CHECK HERE (replace that line behind)...
//        User authentificationResult =  AuthentificationThirdParty.authentification(lap[0], lap[1]);
 
        //Our system refuse login and password
//        if(authentificationResult == null){
//            throw new WebApplicationException(Status.UNAUTHORIZED);
//        }
 
        //TODO : HERE YOU SHOULD ADD PARAMETER TO REQUEST, TO REMEMBER USER ON YOUR REST SERVICE...
 
        return containerRequest;
	}

}
