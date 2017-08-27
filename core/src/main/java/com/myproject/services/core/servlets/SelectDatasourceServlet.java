package com.myproject.services.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;





@SlingServlet(resourceTypes={"fashion/select/datasource"}, methods={"GET"})
public class SelectDatasourceServlet extends SlingSafeMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Logger log=LoggerFactory.getLogger(SelectDatasourceServlet.class);
	

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		// Create an ArrayList to hold data
				List<Resource> resourceList = new ArrayList<Resource>();
		 
				ValueMap vm = null;
		 
				for (int i = 1; i <= 10; i++) {
					vm = new ValueMapDecorator(new HashMap<String, Object>());
					String Value = "samplevalue" + i;
					String Text = "Drop Down :" + i;
					
					vm.put("value", Value);
					vm.put("text", Text);
		 
					log.debug("Hi i am in SelectDatasourceServlet ");
					
					resourceList.add(new ValueMapResource(request.getResourceResolver(),
							new ResourceMetadata(), "nt:unstructured", vm));
				}
		
		
		
		// Create a DataSource that is used to populate the drop-down control
				DataSource ds = new SimpleDataSource(resourceList.iterator());
		// i am keeping data source object in request object		
				request.setAttribute(DataSource.class.getName(), ds);
				
		
	}

}