package it.micheleorsi.web;

import it.micheleorsi.endpoints.MessageResourceImpl;
import it.micheleorsi.endpoints.intefaces.MessageResource;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;


public class MainGuice extends GuiceServletContextListener {

    Logger logger = Logger.getLogger(this.getClass().getName());

    // (1) Override getInjector
    @Override
    protected Injector getInjector() {
    	
    	final Map<String, String> params = new HashMap<String, String>();
        /*
         * The following line will scan ausbdsoccer.server.resources package for Jersey Resources
         */
        params.put("com.sun.jersey.config.property.packages","it.micheleorsi.endpoints");
         
        params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        
        params.put("com.sun.jersey.config.feature.DisableWADL", "true");
        
        params.put("com.sun.jersey.spi.container.ResourceFilters", "it.micheleorsi.auth.filter.ResourceFilterFactory");
        
        
        /*
         * The following line will use MainJerseyApplication.java to define Jersey resources
         */
//        params.put("javax.ws.rs.Application", "it.micheleorsi.web.MainJerseyApplication");
    	
        return Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
//                bind(SecurityFilter.class).in(Singleton.class);
//            	bind(MainJerseyApplication.class).in(Singleton.class);
                bind(MessageResource.class).to(MessageResourceImpl.class);// .in(Singleton.class);
//                bind(ServletContainer.class).in(Singleton.class);

                // (2) Change to using the GuiceContainer
                serve("/rest/*").with(GuiceContainer.class,params); // <<<<---

//                bind(UserDao.class).to(UserJdbc.class);
//                bind(SessionUtility.class);
            }
        });
    }


}