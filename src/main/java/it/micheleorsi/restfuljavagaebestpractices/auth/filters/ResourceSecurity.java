/**
 * 
 */
package it.micheleorsi.restfuljavagaebestpractices.auth.filters;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 * @author micheleorsi
 * 
 */
public class ResourceSecurity extends RolesAllowedResourceFilterFactory {
	private final Authentication auth;

	@Inject
	public ResourceSecurity(Authentication auth) {
		this.auth = auth;
	}

	@Override
	public List<ResourceFilter> create(AbstractMethod am) {
		// get filters from RolesAllowedResourceFilterFactory Factory!
		List<ResourceFilter> rolesFilters = super.create(am);
		if (null == rolesFilters) {
			rolesFilters = new ArrayList<ResourceFilter>();
		}

		// Convert into mutable List, so as to add more filters that we need
		// (RolesAllowedResourceFilterFactory generates immutable list of
		// filters)
		List<ResourceFilter> filters = new ArrayList<ResourceFilter>(
				rolesFilters);

		// Load SecurityContext first (this will load security context onto
		// request)
		filters.add(0, auth);

		return filters;
	}
}