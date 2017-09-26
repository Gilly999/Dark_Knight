package com.myproject.service.core.helpx.implemented;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.ServerException;
import java.util.Dictionary;
import java.util.Calendar;
import java.io.*;
    
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.osgi.OsgiUtil;
import org.apache.sling.jcr.api.SlingRepository;
import org.apache.felix.scr.annotations.Reference;
import org.osgi.service.component.ComponentContext;
import javax.jcr.Session;
import javax.jcr.Node; 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import javax.jcr.ValueFactory;
import javax.jcr.Binary;
  
import javax.jcr.RepositoryException;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.jackrabbit.commons.JcrUtils;
import org.osgi.service.component.ComponentContext;
   
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.io.OutputStream;
  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
import jxl.*; 
  
//Sling Imports
import org.apache.sling.api.resource.ResourceResolverFactory ; 
import org.apache.sling.api.resource.ResourceResolver; 
import org.apache.sling.api.resource.Resource; 
   
//This is a component so it can provide or consume services
@SlingServlet(paths="/bin/excelfile", methods = "POST", metatype=true)
public class HandleFile extends org.apache.sling.api.servlets.SlingAllMethodsServlet {
     private static final long serialVersionUID = 2598426539166789515L;
         
   //Inject a Sling ResourceResolverFactory
     @Reference
     private ResourceResolverFactory resolverFactory;
       
     private Session session;
       
     /** Default log. */
     protected final Logger log = LoggerFactory.getLogger(this.getClass());
        
                 
     @Override
     protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
      
           
   
          
     }
        
        
     @Override
     protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
          
                   
         try
         {
         final boolean isMultipart = org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent(request);
         PrintWriter out = null;
            
            
          
         log.info("GET THE STREAM"); 
          
         out = response.getWriter();
           if (isMultipart) {
             final java.util.Map<String, org.apache.sling.api.request.RequestParameter[]> params = request.getRequestParameterMap();
             for (final java.util.Map.Entry<String, org.apache.sling.api.request.RequestParameter[]> pairs : params.entrySet()) {
               final String k = pairs.getKey();
               final org.apache.sling.api.request.RequestParameter[] pArr = pairs.getValue();
               final org.apache.sling.api.request.RequestParameter param = pArr[0];
               final InputStream stream = param.getInputStream();
                 
                
                
               log.info("GET THE STREAM22"); 
               //Save the uploaded file into the Adobe CQ DAM
               int excelValue = injectSpreadSheet(stream);
               if (excelValue == 0)
                     out.println("Customer data from the Excel Spread Sheet has been successfully imported into the AEM JCR");
               else
                   out.println("Customer data could not be imported into the AEM JCR");
                
             }
           }
         }
            
         catch (Exception e) {
             e.printStackTrace();
         }
        
     }
        
   //Get data from the excel spreadsheet
     public int injectSpreadSheet(InputStream is)
     {
         try
         {
              
             log.info("GET THE STREAM33");
             //Get the spreadsheet
             Workbook workbook = Workbook.getWorkbook(is);
               
             log.info("GET THE STREAMWorkbook");
             Sheet sheet = workbook.getSheet(0);
               
              
             log.info("GET THE STREAMWorkbook");
            String firstName = ""; 
            String lastName = ""; 
            String address = "";
            String desc = "";
              
            log.info("GET THE STREAM44"); 
            for (int index=0; index<4;index++)
            {
                Cell a3 = sheet.getCell(0,index+2); 
                Cell b3 = sheet.getCell(1,index+2); 
                Cell c3 = sheet.getCell(2,index+2);
                Cell d3 = sheet.getCell(3,index+2);
      
                firstName = a3.getContents(); 
                lastName = b3.getContents(); 
                address = c3.getContents();
                desc = d3.getContents();
                  
                 
                log.info("About to inject cust data ..." +firstName);
                 
                //Store the excel data into the Adobe AEM JCR
               injestCustData(firstName,lastName,address, desc);
              
                }    
              
            return 0; 
              
         }
          
         catch(Exception e)
         {
            e.printStackTrace();
         }    
         return -1 ; 
         }
     //Stores customer data in the Adobe CQ JCR
     public int injestCustData(String firstName, String lastName, String address, String desc)
     {
         int num  = 0; 
         try { 
          
             //Invoke the adaptTo method to create a Session used to create a QueryManager
             ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
        	// ResourceResolver resourceResolver=resolverFactory.getServiceResourceResolver(null);
             session = resourceResolver.adaptTo(Session.class);
                     
             //Create a node that represents the root node
             Node root = session.getRootNode(); 
                      
             //Get the content node in the JCR
             Node content = root.getNode("content");
                       
             //Determine if the content/customer node exists
             Node customerRoot = null;
             int custRec = doesCustExist(content);
          
              
             log.info("*** Value of  custRec is ..." +custRec);                               
             //-1 means that content/customer does not exist
             if (custRec == -1)
             {
                 //content/customer does not exist -- create it
                 customerRoot = content.addNode("customerexcel");
             }
            else
            {
                //content/customer does exist -- retrieve it
                customerRoot = content.getNode("customerexcel");
            }
                       
          int custId = custRec+1; //assign a new id to the customer node
                       
          //Store content from the client JSP in the JCR
         Node custNode = customerRoot.addNode("customer"+firstName+lastName+custId, "nt:unstructured"); 
                
             //make sure name of node is unique
         custNode.setProperty("id", custId); 
         custNode.setProperty("firstName", firstName); 
         custNode.setProperty("lastName", lastName); 
         custNode.setProperty("address", address);  
         custNode.setProperty("desc", desc);
                                     
         // Save the session changes and log out
         session.save(); 
         session.logout();
         return custId; 
         }
        
      catch(Exception  e){
          log.error("RepositoryException: " + e);
           }
      return 0 ; 
      } 
        
       
       
     /*
      * Determines if the content/customer node exists 
      * This method returns these values:
      * -1 - if customer does not exist
      * 0 - if content/customer node exists; however, contains no children
      * number - the number of children that the content/customer node contains
     */
     private int doesCustExist(Node content)
     {
         try
         {
             int index = 0 ; 
             int childRecs = 0 ; 
                
         java.lang.Iterable<Node> custNode = JcrUtils.getChildNodes(content, "customerexcel");
         Iterator it = custNode.iterator();
                     
         //only going to be 1 content/customer node if it exists
         if (it.hasNext())
             {
             //Count the number of child nodes in content/customer
             Node customerRoot = content.getNode("customerexcel");
             Iterable itCust = JcrUtils.getChildNodes(customerRoot); 
             Iterator childNodeIt = itCust.iterator();
                    
             //Count the number of customer child nodes 
             while (childNodeIt.hasNext())
             {
                 childRecs++;
                 childNodeIt.next();
             }
              return childRecs; 
            }
         else
             return -1; //content/customer does not exist
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         return 0;
      }
}