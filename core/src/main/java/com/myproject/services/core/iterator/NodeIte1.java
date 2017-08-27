package com.myproject.services.core.iterator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SlingServlet(paths = "/bin/regnantNode", methods = "GET")
public class NodeIte1 extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ResourceResolverFactory rrfactory; /// Interface
	ResourceResolver rResolver = null;
	private final Logger log = LoggerFactory.getLogger(NodeIte1.class);

	public String getNodes1() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(rrfactory.SUBSERVICE, "readService");
		try {
			rResolver = rrfactory.getServiceResourceResolver(param);
			Resource page = rResolver.getResource("/content/geometrixx/en");
			Node nodeIter = page.adaptTo(Node.class);
			NodeIterator iterator = nodeIter.getNodes();
			while (iterator.hasNext()) {
				Node child = iterator.nextNode();
				log.debug("Child nodes are" + child);
				return child.getName();
			}
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


@Override
protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	getNodes1();
	
	}



}
