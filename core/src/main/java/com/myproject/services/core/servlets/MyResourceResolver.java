package com.myproject.services.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

@Component(immediate = true, metatype = false, label = "Getting Resource Resolver")
@Service@Properties(value = {
        @Property(name = "sling.servlet.paths", value = "/bin/resource")
})
public class MyResourceResolver extends SlingSafeMethodsServlet {
    @Reference   
    ResourceResolverFactory resourceResolverFactory;

    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {

            Map<String, Object> param = new HashMap<String, Object>();
            param.put(ResourceResolverFactory.SUBSERVICE, "adminResourceResolver");
            ResourceResolver resourceResolver = resourceResolverFactory.getResourceResolver(param);
            String path = resourceResolver.getResource("/apps/sling").getPath();
            response.getWriter().write(path);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}