/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.utils;

import static org.junit.Assert.*;
import it.micheleorsi.restfuljavagaebestpractices.auth.filters.ResourceFilterFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author micheleorsi
 *
 */
public class UtilsTest {

	public UtilsTest() {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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

	@Test
	public void authorizationHader() {
		// basic auth
		assertTrue("Basic ".matches("^[B|b]asic .*$"));
		assertTrue("basic ".matches("^[B|b]asic .*$"));
		assertFalse("Basicg".matches("^[B|b]asic .*$"));
		
		// session auth
		assertTrue("Session ".matches("^[S|s]ession .*$"));
		assertTrue("session ".matches("^[S|s]ession .*$"));
		assertFalse("Sessionf".matches("^[S|s]ession .*$"));
	}

}
