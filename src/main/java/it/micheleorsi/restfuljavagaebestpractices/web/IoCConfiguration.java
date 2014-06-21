package it.micheleorsi.restfuljavagaebestpractices.web;

import it.micheleorsi.restfuljavagaebestpractices.auth.filters.Authentication;
import it.micheleorsi.restfuljavagaebestpractices.auth.filters.AuthenticationImpl;
import it.micheleorsi.restfuljavagaebestpractices.auth.filters.ResourceFilterFactory;
import it.micheleorsi.restfuljavagaebestpractices.endpoints.MessageResource;
import it.micheleorsi.restfuljavagaebestpractices.endpoints.impl.MessageResourceImpl;
import it.micheleorsi.restfuljavagaebestpractices.persistence.DAOFactory;
import it.micheleorsi.restfuljavagaebestpractices.persistence.UserDAO;
import it.micheleorsi.restfuljavagaebestpractices.persistence.DAOFactory.Type;
import it.micheleorsi.restfuljavagaebestpractices.persistence.clouddatastore.CloudDatastoreUserDAO;
import it.micheleorsi.restfuljavagaebestpractices.utils.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.Application;

import com.sun.jersey.spi.container.ResourceFilters;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;


public class IoCConfiguration extends GuiceServletContextListener {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected Injector getInjector() {
    	
        return Guice.createInjector(new MyModule());
    }
    
    private class MyModule extends JerseyServletModule {
    	
    	@Override
        protected void configureServlets() {
    		Map<String, String> params = new HashMap<String, String>();
//            params.put(Application.class.getName(), MainJerseyApplication.class.getName());
            params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
            params.put("com.sun.jersey.config.feature.DisableWADL", "true");
            params.put(ResourceFilters.class.getName(), ResourceFilterFactory.class.getName());
            
            // REST resources
            bind(MessageResource.class).to(MessageResourceImpl.class);
            
            // persistence layer 
            bind(UserDAO.class).to(CloudDatastoreUserDAO.class);
            
            // auth layer
            bind(Authentication.class).to(AuthenticationImpl.class);

            serve(Constants.ROOT_PATH+"/*").with(GuiceContainer.class,params); 
        }
    }


}