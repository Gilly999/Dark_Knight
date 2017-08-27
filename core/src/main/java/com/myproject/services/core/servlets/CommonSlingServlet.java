package com.myproject.services.core.servlets;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myproject.services.core.impl.CommonReferenceServiceImpl;

@SlingServlet(methods={"POST"}, paths="/bin/myservlet", extensions="json")

//@SlingServlet(methods={"GET"}, extensions="json")
public class CommonSlingServlet extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private static final Logger log=LoggerFactory.getLogger(CommonSlingServlet.class);
	
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		log.debug("This is Doget method");
		
		doPost(request, response);
			
		log.debug("Just called doPost");
	}
	
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	log.debug("Excuted dopost Method");
	PrintWriter out = response.getWriter();
	out.println("Hi manikanta");
	
	
		
	}
	
	
	
	
}
