// extention resource type

package com.myproject.services.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jcr.RepositoryException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SlingServlet(methods={"GET"}, extensions="xml", resourceTypes="myproject/components/structure/fashionbasepage1")
public class CustomResourceTypeServlet extends SlingSafeMethodsServlet {

	
	//private static final long serialVersionUID = 1L;
	public static final	Logger log=LoggerFactory.getLogger(CustomResourceTypeServlet.class);
	
	private void doGet(SlingHttpServletResponse response,SlingHttpServletRequest request) throws IOException,RepositoryException
	{
		// TODO Auto-generated method stub
		log.debug("I am in Customized ReSource");
		
		response.getWriter().print("Customized resource type");
		
		
			
	}
}
