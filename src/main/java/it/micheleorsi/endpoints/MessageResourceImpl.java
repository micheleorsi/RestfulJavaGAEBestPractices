/**
 * 
 */
package it.micheleorsi.endpoints;

import it.micheleorsi.model.Message;
import it.micheleorsi.persistence.Dao;

import javax.inject.Inject;
import javax.ws.rs.Path;

import it.micheleorsi.endpoints.intefaces.MessageResource;
/**
 * @author micheleorsi
 *
 */
public class MessageResourceImpl implements MessageResource {
	
//	private Dao<String> dao;
	
//	@Inject
	public MessageResourceImpl() {
//		this.dao = dao;
	}
	
	@Override
	public Message getResource(String id) {
//		String stuff = dao.getById(id);
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
