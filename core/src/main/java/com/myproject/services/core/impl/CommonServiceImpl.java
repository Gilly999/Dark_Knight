package com.myproject.services.core.impl;

import java.util.Dictionary;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.services.core.CommonService;

@Component(immediate=true , metatype=true)
@Service(value={CommonService.class})
@Properties({
	@Property(name="Common.service.name", value="", label="Enter-name", description="This property will take the name")
})


public class CommonServiceImpl implements CommonService{

	public static final Logger log=LoggerFactory.getLogger(CommonServiceImpl.class);
	private String nameProperty="";
	@Override
	public void callMethod() {
		// TODO Auto-generated method stub
		log.debug("Hi this is CallMethod");
	}

	@Override
	public void displayParams() {
		// TODO Auto-generated method stub
		
		
		log.debug("Hi i am in display Paaram method");
		
	}
	@Activate
	@Modified
	private void activate(ComponentContext context)
	{
	Dictionary properties=context.getProperties();
						
		nameProperty=PropertiesUtil.toString(properties.get("Common.service.name"), "Enter Correct Details");

		
			log.debug("First name is----  : {} ", nameProperty);
	
	

}
}

/*
=======================================
==============error file-=========

package com.myproject.services.core.impl;





import java.util.Dictionary;
import com.myproject.services.core;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate=true , metatype=true)
@Service(value={CommonService.class})
@Properties({
	@Property(name="Common.service.name", value="", label="Enter-name", description="This property will take the name")
	//@Property(name="Common.service.name2", value="", label="Enter First Name", description="This property will take the name")
	
	
	
})

public class CommonServiceImpl implements CommonService{

	public static final Logger log=LoggerFactory.getLogger(CommonServiceImpl.class);
	private String nameProperty="";
	
	@Override
	public void callMethod() {
		// TODO Auto-generated method stub
		log.debug("Hi this is CallMethod");
	}

	@Override
	public void displayParams() {
		// TODO Auto-generated method stub
		
		
		log.debug("Hi i am in display Paaram method");
	}


	@Activate
	@Modified
	private void activate(ComponentContext context)
	{
	Dictionary Properties=context.getProperties();
						
nameProperty=PropertiesUtil.toString(Properties.get("Common.service.name"), "name undefined");

		
  log.debug("Name property  : {} ", nameProperty);
  //log.debug("Name2 property  : {} ", PropertiesUtil.toString(Properties.get("Common.service.name2"), "name undefined");

	}
}


*/