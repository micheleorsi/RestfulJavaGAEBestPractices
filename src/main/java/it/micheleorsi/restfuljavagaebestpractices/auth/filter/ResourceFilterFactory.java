/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.auth.filter;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 * @author micheleorsi
 *
 */
@Provider // register as jersey's provider
public class ResourceFilterFactory extends RolesAllowedResourceFilterFactory {
		
	@Override
	public List<ResourceFilter> create(AbstractMethod am) {
		// get filters from RolesAllowedResourceFilterFactory Factory!
		List<ResourceFilter> rolesFilters = super.create(am);
		if (null == rolesFilters) {
			rolesFilters = new ArrayList<ResourceFilter>();
		}
		
		// Convert into mutable List, so as to add more filters that we need
		// (RolesAllowedResourceFilterFactory generates immutable list of filters)
		List<ResourceFilter> filters = new ArrayList<ResourceFilter>(rolesFilters);

		// Load SecurityContext first (this will load security context onto request)
		filters.add(0, new SecurityContextFilter());

		// Version Control?
//		filters.add(versionFilter);
		
		// If this abstract method is annotated with @Audit, we will apply AuditFilter to audit
		// this request.
//		if (am.isAnnotationPresent(Audit.class)) {
//			filters.add(auditingFilter);
//		}

		return filters;
	}
}