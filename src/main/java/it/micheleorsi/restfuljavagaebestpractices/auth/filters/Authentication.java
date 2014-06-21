/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.auth.filters;

import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 * @author micheleorsi
 *
 */
public interface Authentication extends ResourceFilter, ContainerRequestFilter {
	
}