/**
 * 
 */
package it.micheleorsi.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author micheleorsi
 *
 */
@XmlRootElement
public class Message {
	
	private String subject = null;
	private String body = null;
	
	public Message() {}
	
	public Message(String subject, String body) {
		this.subject = subject;
		this.body = body;
	}
	
	public String getSubject() {return subject;}
	public void setSubject(String subject) {this.subject = subject;}
	public String getBody() {return body;}
	public void setBody(String body) {this.body = body;}

}
