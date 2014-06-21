/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author micheleorsi
 * 
 */
@XmlRootElement
public class JWT {

	private String token = null;

	public JWT(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
