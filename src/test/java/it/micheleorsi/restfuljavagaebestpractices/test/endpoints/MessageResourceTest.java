/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.test.endpoints;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import it.micheleorsi.restfuljavagaebestpractices.model.Message;
import it.micheleorsi.restfuljavagaebestpractices.web.MainGuice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	 * Test method for {@link it.micheleorsi.endpoints.intefaces.MessageResource#getResource(java.lang.String)}.
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
}
