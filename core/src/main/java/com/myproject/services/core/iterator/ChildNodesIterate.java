/*package com.myproject.services.core.iterator;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Services;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
@Service
public class ChildNodesIterate implements IteratingNodes {

	ResourceResolverFactory rrfactory; ///Interface
	ResourceResolver rResolver=null;
	 private final Logger log = LoggerFactory.getLogger(ChildNodesIterate.class);

	@Override
	public String getNodes() 
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put(rrfactory.SUBSERVICE, "IteratingNodes");
		try {
			rResolver=rrfactory.getServiceResourceResolver(param);
			Resource page=rResolver.getResource("/content/geometrixx/en");
				Node nodeIter=page.adaptTo(Node.class);
				NodeIterator iterator = nodeIter.getNodes();    
				while (iterator.hasNext()) 
				{    
				Node child = iterator.nextNode();    		   
				log.debug("Child nodes are" +child );	
				return child;
		} 
		}	
				catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		//return null;
	}

	@Override
	public String getChildNodes() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/