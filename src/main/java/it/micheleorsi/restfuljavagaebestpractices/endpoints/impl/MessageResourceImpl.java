/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.endpoints.impl;

import it.micheleorsi.restfuljavagaebestpractices.endpoints.MessageResource;
import it.micheleorsi.restfuljavagaebestpractices.model.Message;
import it.micheleorsi.restfuljavagaebestpractices.persistence.MessageDAO;

import javax.inject.Inject;

/**
 * @author micheleorsi
 * 
 */
public class MessageResourceImpl implements MessageResource {

	private final MessageDAO dao;

	@Inject
	public MessageResourceImpl(MessageDAO dao) {
		this.dao = dao;
	}

	@Override
	public Message getResource(String id) {
		return new Message("text subject", "text body", Integer.valueOf(id));
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
	public void deleteResource(String id) {
	}
}
