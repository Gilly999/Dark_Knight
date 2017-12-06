package com.myproject.service.core.helpx.implemented;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.service.core.helpx.interfaces.Testing_Interface;

@Component
@Service
public class Testing_Inter_Implemented2 implements Testing_Interface {

	
	//private static final Logger log= LoggerFactory.getLogger(clazz)
	@Override
	public String mani() {
		
		Logger log = LoggerFactory.getLogger(getClass());
		
		// TODO Auto-generated method stub
		log.debug("i am in Testing_Inter_Implemented2");
		return null;
	}
}
