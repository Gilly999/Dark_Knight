package com.myproject.service.core.helpx.implemented;
// go to page path in use package
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.service.core.helpx.interfaces.Testing_Interface;
import com.myproject.services.core.use.HeaderNavigation;
@Component
@Service
public class Testing_Inter_Implemented1 implements Testing_Interface{

	
	//private static final Logger log= LoggerFactory.getLogger(Testing_Inter_Implemented1.class);
	@Override
	public String mani() {
		// TODO Auto-generated method stub
		Logger log = LoggerFactory.getLogger(getClass());
		log.debug("I am In Testing_Inter_Implemented1 ");
		return null;
		
	}

}
