package com.myproject.services.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

@SlingServlet(paths = "/bin/testservlet", methods="GET")
public class TestServlet extends SlingAllMethodsServlet {

		public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException
{

			
			System.out.println("HIyjfjgfj");
			PrintWriter pw=resp.getWriter();
			pw.println("HI");
			
			
}
	
	
}
