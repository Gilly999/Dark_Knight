package com.myproject.services.core.use;

import javax.jcr.Session;
import javax.jcr.Value;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.useradmin.UserAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

public class JcrUse extends WCMUse {

	
	
	private static final Logger log=LoggerFactory.getLogger(JcrUse.class);

	private static final String UserAdmin = null;
	
	@Reference
	SlingRepository slingRepository;
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		
	
	Value session=slingRepository.getDescriptorValue(null);
	log.debug("JcrUse class");
	//log.debug(session);
	
	//return session;
	
	}

	public String retur()
	{
		return UserAdmin;
		
	}
	
	
	
	
}
