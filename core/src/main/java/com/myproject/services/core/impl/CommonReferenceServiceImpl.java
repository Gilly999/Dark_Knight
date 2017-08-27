package com.myproject.services.core.impl;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.services.core.CommonReferenceService;
import com.myproject.services.core.CommonService;

@Component(metatype=true , immediate=true)
@Service(value={CommonReferenceService.class})
@Properties({
	@Property(name="prop.sample", boolValue=true, label="Check this to verify Service", description="if this is changed commonservice will be triggered")
})


public class CommonReferenceServiceImpl implements CommonReferenceService {
	
	public static final Logger log=LoggerFactory.getLogger(CommonReferenceServiceImpl.class);		
	
	@Reference
	CommonService commonService;
	@Activate
	@Modified
	private void activate() {
		// TODO Auto-generated method stub
			if(null!=commonService)
			{
				commonService.callMethod();
				commonService.displayParams();
				log.debug("completed calling of Params method");
			}
	}
}
