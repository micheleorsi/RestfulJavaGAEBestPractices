/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.auth.filters;

import it.micheleorsi.restfuljavagaebestpractices.auth.model.User;
import it.micheleorsi.restfuljavagaebestpractices.persistence.UserDAO;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.DatatypeConverter;

import com.google.appengine.api.utils.SystemProperty;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * @author micheleorsi
 * 
 */
public class AuthenticationImpl implements Authentication {

	public enum AuthType {
		BASIC, OAUTH1, BEARER
	}

	private final Logger log;
	private final UserDAO userRepo;
	
	@Inject
	public AuthenticationImpl(UserDAO userDAO, Logger logger) {
		this.userRepo = userDAO;
		this.log = logger;
	}
	
	private String getUserLoggedFromBasicAuth(ContainerRequest request) {
		String userKey;
		// check it uses https
		if (!request.isSecure() && SystemProperty.environment.value() != null) {
			return null;
		} 
		
		// directly
		String authHeader = request.getHeaderValue("Authorization");
		authHeader = authHeader.replaceFirst("[B|b]asic ", "");

		// Decode the Base64 into byte[]
		byte[] decodedBytes = DatatypeConverter
				.parseBase64Binary(authHeader);

		// If the decode fails in any case
		if (decodedBytes == null || decodedBytes.length == 0) {
			return null;
		}

		// Now we can convert the byte[] into a splitted array :
		// - the first one is login,
		// - the second one password
		String[] lap = new String(decodedBytes).split(":", 2);

		// If login or password fail
		if (lap == null || lap.length != 2) {
			return null;
		} else {
			userKey = lap[0];
		}
		return userKey;
	}
	
	private String getUserLoggedFromBearerAuth(ContainerRequest request) {
		String userKey;
		// directly
		String authHeader = request.getHeaderValue("Authorization");
		authHeader = authHeader.replaceFirst("[B|b]earer ", "");
		
		//TODO: calculate real token

		// - the first one is login,
		// - the second one password
		String[] lap = new String(authHeader).split("\\.", 3);

		// If login or password fail
		if (lap == null || lap.length != 3) {
			return null;
		} else {
			userKey = lap[0];
		}
		return userKey;
	}

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		log.info("start ContainerRequest "+request.getHeaderValue("Authorization"));

		// Get the authentification passed in HTTP headers parameters
		String authHeader = request.getHeaderValue("Authorization");
		String authSchema = null;
		String userKey = null;
		
		if (authHeader != null && !authHeader.isEmpty()) {
			log.info("work on AuthType");
			StringTokenizer strToken = new StringTokenizer(authHeader, " ");
			AuthType authType = null;

			if (strToken.hasMoreTokens()) {
				log.info("strToken.hasMoreTokens");
				String authTypeString = strToken.nextToken().toLowerCase()
						.toUpperCase();
				try {
					authType = AuthType.valueOf(authTypeString);

					switch (authType) {
						case BASIC:
							log.info("BASIC auth");
							authSchema = SecurityContext.BASIC_AUTH;
							userKey = this.getUserLoggedFromBasicAuth(request);
							break;
							
						case OAUTH1:
							log.info("OAUTH1 auth");
							authSchema = AuthType.OAUTH1.toString();
							break;
							
						case BEARER:
							log.info("BEARER (jwt) auth");
							authSchema = AuthType.BEARER.toString();
							userKey = this.getUserLoggedFromBearerAuth(request);
							break;
	
						default:
							break;
					}
				} catch (IllegalArgumentException ex) {
					log.log(Level.WARNING, "illegal auth type provided " + authTypeString, ex);
				}
			}

		}
		
		User user = null;
		if(userKey!=null) {
			user = userRepo.getById(userKey);
		} 

		// Set security context
		log.info("authSchema: " + authSchema);
		request.setSecurityContext(new Authorization(request.isSecure(),
				authSchema, 
				user));

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