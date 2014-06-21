/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.test.endpoints;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import it.micheleorsi.restfuljavagaebestpractices.model.Message;
import org.junit.Test;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * @author micheleorsi
 *
 */
public class MessageResourceTest extends EndpointTest {

	public MessageResourceTest() {
		super();
	}

	/**
	 * Test method for {@link it.micheleorsi.restfuljavagaebestpractices.endpoints.MessageResource#getResource(java.lang.String)}.
	 */
	@Test
	public void testGetResource() {
		WebResource webResource = resource();
        Message responseMsg = webResource
        		.path("messages/12")
        		.get(Message.class);
        assertEquals(Integer.valueOf("12"),responseMsg.getId());
        
        responseMsg = webResource
        		.path("messages/12")
        		.accept(MediaType.APPLICATION_JSON)
        		.get(Message.class);
        assertEquals(Integer.valueOf("12"),responseMsg.getId());
	}
	
	/**
	 * Test method for {@link it.micheleorsi.restfuljavagaebestpractices.endpoints.MessageResource#createResource(it.micheleorsi.restfuljavagaebestpractices.model.Message)}.
	 */
	@Test
	public void testCreateResource() {
		WebResource webResource = resource();
		try {
			Message responseMsg = webResource
	        		.path("messages")
	        		.entity(new Message("subj", "body", Integer.valueOf(10)))
	        		.post(Message.class);
		} catch(UniformInterfaceException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link it.micheleorsi.restfuljavagaebestpractices.endpoints.MessageResource#updateResource(it.micheleorsi.restfuljavagaebestpractices.model.Message)}.
	 */
	@Test
	public void testUpdateResource() {
	}

	/**
	 * Test method for {@link it.micheleorsi.restfuljavagaebestpractices.endpoints.MessageResource#deleteResource(java.lang.String)}.
	 */
	@Test
	public void testDeleteResource() {
	}
}
