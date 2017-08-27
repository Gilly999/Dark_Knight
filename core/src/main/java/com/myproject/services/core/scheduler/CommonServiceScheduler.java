package com.myproject.services.core.scheduler;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component(metatype=true , immediate=true)
@Service
@Properties({
	
	
	//@Property(name="scheduler.expression", value="0 0/1  * * * ?")
	
	@Property(name="schedular.period", longValue=60)
	
})


public class CommonServiceScheduler  implements Runnable{

		private static final Logger log=LoggerFactory.getLogger(CommonServiceScheduler.class);
		
		
		//log.debug("i am run method for CommonServiceSchedular);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		log.debug("i am run method for CommonServiceSchedular and schedular is running ");
		log.debug("Schedular is runnig here:=================================  ");
		
	}

}
