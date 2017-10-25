package com.aem.mytectra.core.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;


@SuppressWarnings("serial")
@SlingServlet(paths="/bin/tectra/servlet")
public class TectraFirstServlet extends SlingAllMethodsServlet {
	
	public Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String value = null;
		String resourceType= "";
		ResourceResolver rr = request.getResourceResolver();
		Resource r = rr.getResource("/content/geometrixx-outdoors/en/men/jcr:content");
	//	Resource r = rr.getResource("/content/geometrixx-outdoors/en/men");
		
		Node node = r.adaptTo(Node.class);
			// Page page=node.adaptTo(Page.class);
		//javax.jcr.Node node = r.adaptTo(javax.jcr.Node.class);
		
		//Page page = r.adaptTo(Page.class);
		try {
			
			//Node api
					
			node.setProperty("jcr:title", "Mens value changed");
			
			if(node.hasProperty("jcr:title")){
				value = node.getProperty("jcr:title").getValue().getString();
			}
			
			value = node.getProperty("jcr:title").getValue().getString();
			
			NodeIterator ni = node.getNodes();
			while(ni.hasNext()){
				Node node1 = ni.nextNode();
				if(node1.hasProperty("sling:resourceType")){
					resourceType += node1.getProperty("sling:resourceType").getValue().getString() + "   ";
				}
				
			}
			
			
			//Page api
			
			//value = page.getTitle() +"   path : "+ page.getPath()  +"  parent :  " +page.getParent().getTitle() ;
			
			
			
			
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
		String fName= request.getParameter("firstName");
		String LName= request.getParameter("lastName");
		LOG.debug("This is log testing ....................................");
		response.getWriter().write("GET response : "+fName+LName + "    resource test :  : " +value +" resource type : " +resourceType);
		
	}
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String fName= request.getParameter("firstName");
		String LName= request.getParameter("lastName");
		LOG.debug("This is log testing ....................................");
		response.getWriter().write("POST response : "+fName+LName);
	}
	


}
