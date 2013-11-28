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
//	@Ref(resource=MessageResource.class)
//	URI u;
	
	private String subject = null;
	private String body = null;
	private String id = null;
	
	public Message() {}
	
	public Message(String subject, String body, String id) {
		this.subject = subject;
		this.body = body;
		this.id = id;
	}
	
	public String getSubject() {return subject;}
	public void setSubject(String subject) {this.subject = subject;}
	
	public String getBody() {return body;}
	public void setBody(String body) {this.body = body;}

	public String getId() {return id;}
	public void setId(String id) {this.id = id;}

}
