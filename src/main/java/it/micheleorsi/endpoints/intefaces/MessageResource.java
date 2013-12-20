/**
 * 
 */
package it.micheleorsi.endpoints.intefaces;

import it.micheleorsi.auth.model.User.Role;
import it.micheleorsi.model.Message;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author micheleorsi
 *
 */
public interface MessageResource {
	
	@GET
	@Path("{id}")
	@PermitAll
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Message getResource(@PathParam("id") String id);
	
	@POST
	@RolesAllowed({Role.Values.CONTRIBUTOR})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Message createResource(Message message);
	
	@PUT
	@Path("{id}")
	@RolesAllowed({Role.Values.EDITOR})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Message updateResource(Message message);
	
	@DELETE
	@Path("{id}")
	@DenyAll
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void deleteResource(@PathParam("id") String id);

}
