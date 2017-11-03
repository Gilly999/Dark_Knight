package com.myproject.services.core.webservices;


import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
  
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
 
import java.util.ArrayList;
import java.util.Arrays;
  
 
 
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
 
//SLing HTTP Client

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod; 
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import org.apache.commons.httpclient.params.HttpMethodParams;
 
 
/**
 * Just a simple DS Component
 */
@Component(metatype=true)
@Service
public class InvokeSlingServlet implements InvokeSling {
     
    /** Default log. */
     protected final Logger log = LoggerFactory.getLogger(this.getClass());
     
     
     @Override
     public String getClaim()
    {
         
        //Create an HTTPClient object
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod("http://localhost:4502/bin/mySearchServlet");
         
         
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,    new DefaultHttpMethodRetryHandler(3, false));
 
        try {
           int statusCode = client.executeMethod(method);
 
            if (statusCode != HttpStatus.SC_OK) {
              log.info("HTTP Method failed: " + method.getStatusLine());
            }
 
               
          // Read the response body.
          byte[] responseBody = method.getResponseBody();
 
          log.info("INVOKED THE AEM SLING SERVLET!!! "); 
           
          // Deal with the response.
          // Use caution: ensure correct character encoding and is not binary data
         return new String(responseBody);
 
        } catch (Exception e) {
            log.info("ERROR: " +e.getMessage());
        }
         
         
        return "There was an error invoking the servlet" ; 
    }
     
         
     
 
}