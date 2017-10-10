package com.myproject.services.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@SlingServlet(resourceTypes = { "db/brightcove" }, methods = { "GET" })
public class BrightcoveVideoData extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		List<Resource> resourceList = new ArrayList<Resource>();
		ValueMap vm = null;

		ResourceResolver resolver = request.getResourceResolver();
		PageManager pageManager = resolver.adaptTo(PageManager.class);

		Page page = pageManager.getPage("/content/lighthouse/brightcovevideos");
		
		
		if (page != null) {
			Iterator<Page> listChildren = page.listChildren();
			while (listChildren.hasNext()) {
				ValueMap properties = listChildren.next().getProperties();
				vm = new ValueMapDecorator(new HashMap<String, Object>());
				vm.put("value", properties.get("videoId", " "));
				vm.put("text", getShortString(properties.get("jcr:title", " ").toString(), 50));
				resourceList.add(new ValueMapResource(request.getResourceResolver(), new ResourceMetadata(),"nt:unstructured", vm));
			}
		}
		DataSource ds = new SimpleDataSource(resourceList.iterator());
		request.setAttribute(DataSource.class.getName(), ds);

	}

	public String getShortString(String post, int width) {
		if (post != null && post.length() > width) {
			return post.substring(0, width - 3) + "...";
		} else {
			return post;
		}
	}
}