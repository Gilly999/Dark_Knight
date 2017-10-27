package com.myproject.services.core.use;


import java.util.Properties;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUse;
import com.adobe.cq.social.calendar.client.api.Page;

public class TestingUseClassForImplicitObjects extends WCMUse {

	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		
	  ValueMap properties=getProperties();
	  ValueMap pageProperties=getPageProperties();
	  getRequest();
	  getResponse();
	  getCurrentDesign();
	  getCurrentStyle();
	  getResource();
	  getResourceResolver();
	  
	  
	  
	}

}
