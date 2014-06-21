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
public class Message {
	
	private String subject = null;
	private String body = null;
	private Integer id = null;
	
	public Message() {}
	
	public Message(String subject, String body, Integer id) {
		this.subject = subject;
		this.body = body;
		this.id = id;
	}
	
	public String getSubject() {return subject;}
	public void setSubject(String subject) {this.subject = subject;}
	
	public String getBody() {return body;}
	public void setBody(String body) {this.body = body;}

	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}

}
