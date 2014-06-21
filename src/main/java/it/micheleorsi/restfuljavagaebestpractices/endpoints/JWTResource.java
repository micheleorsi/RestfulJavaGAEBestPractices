/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.endpoints;

import it.micheleorsi.restfuljavagaebestpractices.auth.model.User;
import it.micheleorsi.restfuljavagaebestpractices.model.JWT;
import it.micheleorsi.restfuljavagaebestpractices.model.Message;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author micheleorsi
 *
 */
@Path("jwt")
public interface JWTResource {
	
	@Path("authenticate")
	@POST
	@PermitAll
	@Produces({MediaType.APPLICATION_JSON})
	public JWT createResource(User user);
	
	@Path("api/restricted")
	@GET
	@PermitAll
	@Produces({MediaType.APPLICATION_JSON})
	public Message restricted();
}
