/**
 * 
 */
package it.micheleorsi.endpoints;

import it.micheleorsi.model.Message;

import javax.ws.rs.Path;

import it.micheleorsi.endpoints.intefaces.MessageResource;
/**
 * @author micheleorsi
 *
 */
@Path("messages")
public class MessageResourceImpl implements MessageResource {
	
	@Override
	public Message getResource(String id) {
		return new Message("text subject","text body",id);
	}
	@Override
	public Message createResource(Message message) {
		return message;
	}
	@Override
	public Message updateResource(Message message) {
		return message;
	}
	@Override
	public void deleteResource(String id) {}
}
