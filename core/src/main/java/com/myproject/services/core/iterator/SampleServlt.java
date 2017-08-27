package com.myproject.services.core.iterator;

import java.io.IOException;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
//@Service
//@Property(name="sling.sevlets.paths" ,value="/bin/responsive")
@SlingServlet(paths="/bin/regnantsafeservlet", methods="GET")
public class SampleServlt extends SlingAllMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5094475151855916889L;
	private static final Logger log = LoggerFactory.getLogger(SampleServlt.class);
	@Reference
	SlingRepository repo;
	
	
	String path="/apps/lawfirm/components/content/mainhead";
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response)
			throws ServletException, IOException {
		/*try {
			Session sess = repo.loginAdministrative(null);
			String parameter = request.getParameter(path);
			Node node = sess.getNode(parameter);
			response.getWriter().write(node.getName());
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
			log.info("Called all method servlet");
			response.getWriter().write("Sling All Method Servlet");
	}
		
}