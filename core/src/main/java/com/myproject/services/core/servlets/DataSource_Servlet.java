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

@SlingServlet(resourceTypes = { "datasource/select" }, methods = { "GET" })

public class DataSource_Servlet extends SlingAllMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<Resource> resourceList = new ArrayList<Resource>();

		ValueMap vm = null;
		ResourceResolver resolver = req.getResourceResolver();
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage("/content/fashion/testtwo");

		if (page != null) {
			Iterator<Page> listChildren = page.listChildren();

			while (listChildren.hasNext()) {
				ValueMap properties = listChildren.next().getProperties();

				vm = new ValueMapDecorator(new HashMap<String, Object>());

				vm.put("value", properties.get("Video id"));
				vm.put("text", getShortString(properties.get("jcr:title", " ").toString(), 50));

				resourceList.add(new ValueMapResource(resolver, new ResourceMetadata(), "nt:unstructured", vm));

			}

		}

		DataSource ds = new SimpleDataSource(resourceList.iterator());
		req.setAttribute(DataSource.class.getName(), ds);
		// req.setAttribute(DataSource.class.getName(), ds);
	}

	private Object getShortString(String string, int i) {
		// TODO Auto-generated method stub

		if (string != null && string.length() > i) {
			return string.subSequence(0, i - 3);
		} else {
			return string;
		}
	}

}






















