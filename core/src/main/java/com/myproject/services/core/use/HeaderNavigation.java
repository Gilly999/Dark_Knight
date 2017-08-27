package com.myproject.services.core.use;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;


public class HeaderNavigation extends WCMUse {
	//commit to git 
	private static final Logger log= LoggerFactory.getLogger(HeaderNavigation.class);
	public Map<String , List<String>> nav =new HashMap<String, List<String>>();
	@Override
	public void activate() throws Exception { 
		// TODO Auto-generated method stub
		
		String navPath=get("nav", String.class);
		log.debug("Navigation path :{}", navPath );
		if(null !=navPath){
			log.info("Page navigation path : {}", navPath);
			Page parentPage= getResourceResolver().resolve(navPath).adaptTo(Page.class);
			log.debug("Parent Path :{}", parentPage);
		if(null !=parentPage){
			Iterator<Page> children= parentPage.listChildren();
			
			while(children.hasNext())
			{
				Page nextPage=children.next();
				List<String> props =new ArrayList<String>();
				props.add(nextPage.getTitle());
				
				nav.put(nextPage.getPath(), props);
			}
		}
		
	}
	} 
	public Map<String, List<String>> getNav()
	{
		Iterator<String> iterator=nav.keySet().iterator();
		while(iterator.hasNext())
		{
			String key =iterator.next();
			log.debug("key: {}  and Value: {}" ); iterator.next();
			
		}
		return nav;
}
}
