/**
 * 
 */
package it.micheleorsi.web;

import it.micheleorsi.endpoints.MessageResourceImpl;
import it.micheleorsi.endpoints.intefaces.MessageResource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * @author micheleorsi
 *
 */
public class MainJerseyApplication extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(MessageResource.class);
//        s.add(MessageResourceImpl.class);
//        s.add(AuthorizationResource.class);
//        s.add(MessageBodyWriterXML.class);
//        s.add(MessageBodyWriterJSON.class);
        return s;
    }
}