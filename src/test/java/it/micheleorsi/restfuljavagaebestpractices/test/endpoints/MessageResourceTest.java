/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.test.endpoints;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.ws.rs.core.MediaType;

import it.micheleorsi.restfuljavagaebestpractices.model.Message;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

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
	 * @throws NoSuchAlgorithmException 
	 */
	@Test
	public void testCreateResource() throws NoSuchAlgorithmException {
		WebResource webResource = resource();
		webResource.setProperty(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties());
		Message responseMsg = webResource
	        .path("messages")
	        .accept(MediaType.APPLICATION_JSON)
	        .type(MediaType.APPLICATION_JSON)
	        .header("Authorization", "Basic MjpwYXNzd29yZA==")
	        .entity(new Message("subj", "body", Integer.valueOf(10)))
	        .post(Message.class);
		assertEquals(Integer.valueOf(10), responseMsg.getId());
		
		responseMsg = webResource
		        .path("messages")
		        .accept(MediaType.APPLICATION_JSON)
		        .type(MediaType.APPLICATION_JSON)
		        .header("authorization", "Basic MjpwYXNzd29yZA==")
		        .entity(new Message("subj", "body", Integer.valueOf(10)))
		        .post(Message.class);
			assertEquals(Integer.valueOf(10), responseMsg.getId());
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
