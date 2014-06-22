/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.endpoints.impl;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import it.micheleorsi.restfuljavagaebestpractices.auth.model.User;
import it.micheleorsi.restfuljavagaebestpractices.endpoints.JWTResource;
import it.micheleorsi.restfuljavagaebestpractices.model.JWT;

/**
 * @author micheleorsi
 * 
 */
public class JWTResourceImpl implements JWTResource {

	@Override
	public JWT createToken(User user) {
		if(user.getName().equals("johndoe") && user.getSecret().equals("foobar")) {
			return new JWT(5+"."+user.getName()+"."+user.getSecret());
		} else {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		
	}
}
