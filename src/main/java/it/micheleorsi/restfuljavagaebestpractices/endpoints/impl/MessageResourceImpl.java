/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.endpoints.impl;

import it.micheleorsi.restfuljavagaebestpractices.endpoints.MessageResource;
import it.micheleorsi.restfuljavagaebestpractices.model.Message;
import it.micheleorsi.restfuljavagaebestpractices.persistence.Dao;

import javax.inject.Inject;
import javax.ws.rs.Path;
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
		return new Message("text subject","text body",Integer.valueOf(id));
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
