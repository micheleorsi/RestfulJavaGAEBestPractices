/**
 * 
 */
package it.micheleorsi.endpoints;

import static org.junit.Assert.*;
import it.micheleorsi.model.Message;
import it.micheleorsi.web.MainGuice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

/**
 * @author micheleorsi
 *
 */
public class MessageResourceTest extends JerseyTest {

	public MessageResourceTest() {
		super(new WebAppDescriptor.Builder()
        .contextListenerClass(MainGuice.class)
        .filterClass(com.google.inject.servlet.GuiceFilter.class)
        .servletPath("/rest")
        .build());
	}


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link it.micheleorsi.endpoints.intefaces.MessageResource#getResource(java.lang.String)}.
	 */
	@Test
	public void testGetResource() {
		WebResource webResource = resource();
        Message responseMsg = webResource.path("messages/12").get(Message.class);
        assertEquals("12",responseMsg.getId());
	}
}
