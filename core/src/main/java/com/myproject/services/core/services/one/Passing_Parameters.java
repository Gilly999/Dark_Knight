package com.myproject.services.core.services.one;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.jcr.api.SlingRepository;

public class Passing_Parameters {
	
	
	public void method1(ResourceResolver resolver,SlingRepository repository) throws LoginException, RepositoryException
	{
		repository=(SlingRepository) repository.loginService(null, null);
	}

}
