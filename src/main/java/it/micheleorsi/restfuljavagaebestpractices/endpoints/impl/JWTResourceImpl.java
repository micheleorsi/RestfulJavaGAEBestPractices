/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.endpoints.impl;

import it.micheleorsi.restfuljavagaebestpractices.auth.model.User;
import it.micheleorsi.restfuljavagaebestpractices.endpoints.JWTResource;
import it.micheleorsi.restfuljavagaebestpractices.model.JWT;
import it.micheleorsi.restfuljavagaebestpractices.model.Message;

/**
 * @author micheleorsi
 * 
 */
public class JWTResourceImpl implements JWTResource {

	@Override
	public JWT createResource(User user) {
		return new JWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmaXJzdF9uYW1lIjoiSm9obiIsImxhc3RfbmFtZSI6IkRvZSIsImVtYWlsIjoiam9obkBkb2UuY29tIiwiaWQiOjEyMywiaWF0IjoxNDAzMzkxNDI1LCJleHAiOjE0MDM0MDk0MjV9.anN0Kr-yg9l3ak8CFVJrQGHfCxbFPKQ9V2Kzy5A1blI");
	}

	@Override
	public Message restricted() {
		return new Message("subject","body",Integer.valueOf(12));
	}
}
