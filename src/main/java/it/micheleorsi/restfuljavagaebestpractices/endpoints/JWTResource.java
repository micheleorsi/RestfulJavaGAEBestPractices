/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.endpoints;

import it.micheleorsi.restfuljavagaebestpractices.auth.model.User;
import it.micheleorsi.restfuljavagaebestpractices.model.JWT;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author micheleorsi
 *
 */
@Path("authenticate")
public interface JWTResource {
	
	@POST
	@PermitAll
	@Produces({MediaType.APPLICATION_JSON})
	public JWT createToken(User user);
	
}
