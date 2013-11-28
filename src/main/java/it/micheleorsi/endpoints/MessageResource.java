/**
 * 
 */
package it.micheleorsi.endpoints;

import it.micheleorsi.model.Message;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author micheleorsi
 *
 */
@Path("/messages")
public class MessageResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Message getResource() {
		return new Message("text subject","text body");
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Message createResource(Message message) {
		return message;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateResource(Message message) {
		return message;
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteResource() {}

}
