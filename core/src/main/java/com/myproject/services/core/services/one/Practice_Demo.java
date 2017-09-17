package com.myproject.services.core.services.one;

import javax.jcr.Session;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import com.day.cq.wcm.api.Page;

public class Practice_Demo {

	@Reference
	ResourceResolverFactory resourceResolverFactory;
	
	public String activate() throws LoginException{
	//ResourceResolver resourceResolver1=	resourceResolverFactory.getAdministrativeResourceResolver(null);
	  ResourceResolver resourceResolver =   resourceResolverFactory.getServiceResourceResolver(null);
			Session	session =resourceResolver.adaptTo(Session.class);
			Resource resource=resourceResolver.adaptTo(Resource.class);
			Page path=resourceResolver.adaptTo(Page.class);
			
			if(path==null)
			{
				return null;
			}
			
			return path.getPath();
	
	}
	
	
	
	
}
