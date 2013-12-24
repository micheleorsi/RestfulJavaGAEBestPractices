package it.micheleorsi.web;

import it.micheleorsi.endpoints.MessageResourceImpl;
import it.micheleorsi.endpoints.intefaces.MessageResource;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;


public class MainGuice extends GuiceServletContextListener {

    Logger logger = Logger.getLogger(this.getClass().getName());

    // (1) Override getInjector
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
//                bind(SecurityFilter.class).in(Singleton.class);
                bind(MessageResource.class).to(MessageResourceImpl.class);// .in(Singleton.class);
//                bind(ServletContainer.class).in(Singleton.class);

                // (2) Change to using the GuiceContainer
                serve("/rest/*").with(GuiceContainer.class); // <<<<---

//                bind(UserDao.class).to(UserJdbc.class);
//                bind(SessionUtility.class);
            }
        });
    }


}