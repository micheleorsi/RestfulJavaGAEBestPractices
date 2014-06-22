/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.test.endpoints;

import static org.junit.Assert.*;
import it.micheleorsi.restfuljavagaebestpractices.auth.model.User;
import it.micheleorsi.restfuljavagaebestpractices.model.JWT;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;


/**
 * @author micheleorsi
 *
 */
public class JWTResourceTest extends EndpointTest {

	public JWTResourceTest() {
		super();
	}

	/**
	 * Test method for {@link it.micheleorsi.restfuljavagaebestpractices.endpoints.JWTResource#createToken(it.micheleorsi.restfuljavagaebestpractices.auth.model.User)}.
	 */
	@Test
	public void testCreateToken() {
		WebResource webResource = resource();
		JWT responseMsg = webResource
	        .path("authenticate")
	        .accept(MediaType.APPLICATION_JSON)
	        .type(MediaType.APPLICATION_JSON)
	        .entity(new User("johndoe", "foobar"))
	        .post(JWT.class);
		assertEquals("5.johndoe.foobar", responseMsg.getToken());
	}

}
