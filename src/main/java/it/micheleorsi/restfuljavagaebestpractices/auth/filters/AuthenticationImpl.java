/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.auth.filters;

import it.micheleorsi.restfuljavagaebestpractices.persistence.UserDAO;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
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
		SESSION, BASIC, OAUTH1
	}

	private final Logger LOG;
	private final UserDAO userRepo;

	@Inject
	public AuthenticationImpl(UserDAO userDAO, Logger logger) {
		this.userRepo = userDAO;
		this.LOG = logger;
	}

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		LOG.info("start ContainerRequest");

		// Get the authentification passed in HTTP headers parameters
		String authHeader = request.getHeaderValue("Authorization");
		LOG.info("authHeader " + authHeader);
		LOG.info("Content-Type: " + request.getHeaderValue("Content-Type"));
		LOG.info("Accept: " + request.getHeaderValue("Accept"));

		String userKey = null;
		String authSchema = null;

		if (authHeader != null && !authHeader.isEmpty()) {
			LOG.info("work on AuthType");
			StringTokenizer strToken = new StringTokenizer(authHeader, " ");
			AuthType authType = null;

			if (strToken.hasMoreTokens()) {
				LOG.info("strToken.hasMoreTokens");
				String nextToken = strToken.nextToken().toLowerCase()
						.toUpperCase();
				try {
					authType = AuthType.valueOf(nextToken);

					switch (authType) {
					case BASIC:
						LOG.info("Basic auth");

						// check it uses https
						if (!request.isSecure() && SystemProperty.environment.value() != null) {
							throw new WebApplicationException(
									Status.UNAUTHORIZED);
						} 

						authSchema = SecurityContext.BASIC_AUTH;
						// basic auth
						// lap : loginAndPassword
						// Replacing "Basic THE_BASE_64" to "THE_BASE_64"
						// directly
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
							LOG.info("lap is null");
							throw new WebApplicationException(
									Status.UNAUTHORIZED);
						} else {
							LOG.info("userid " + lap[0]);
							LOG.info("password " + lap[1]);
							userKey = lap[0];
						}
						break;

					default:
						break;
					}
				} catch (IllegalArgumentException ex) {
					LOG.log(Level.WARNING, "illegal auth type provided " + nextToken, ex);
				}
			}

		}

		// Set security context
		LOG.info("authSchema: " + authSchema);
		request.setSecurityContext(new Authorization(request.isSecure(),
				authSchema, userRepo.getById(userKey)));

		LOG.info("ready to return");
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