package com.myproject.services.core.use;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Style;

public class PagePath extends WCMUse {
	private String pageValue="";
	private static final Logger log=LoggerFactory.getLogger(PagePath.class);

	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		Page page=getCurrentPage();
		SlingHttpServletRequest request=getRequest();
		SlingHttpServletResponse responce=getResponse();
		Resource resource=getResource();
		ResourceResolver resourceResolver=getResourceResolver();
		Design currentDesign=getCurrentDesign();
		Style currentStyle=getCurrentStyle();
		pageValue=page.getPath();		
	}
	public String getPageValue()
	{
		return pageValue;
}	
}
