/**
 *
 *
 *
 *
 * //
Encode the submitted form data to JSON
JSONObject obj=new JSONObject();
obj.put("id","id");
obj.put("firstname",firstName);
obj.put("lastname",lastName);
obj.put("address",address);
obj.put("cat",cat);
obj.put("state",state);
obj.put("details",details);
obj.put("date",date);
obj.put("city",city);
 
//Get the JSON formatted data          
String jsonData = obj.toJSONString();

 * 
 */
/**
 * @author hp
 *
 */
package com.adobe.cq.sling;




/*A custom Sling Servlet is an OSGi bundle. However, a difference 
between an OSGi bundle that contains a service and an OSGi bundle that 
contains a Sling Servlet is the former requires that you create an instance
of the service. For example, assume an OSGi bundle contains a service based 
on a Java class named com.adobe.cq.CustomerService. To get data from the client web page 
to this OSGi service, you have to create an instance of com.adobe.cq.CustomerService, 
as shown in this example.


	com.adobe.cq.CustomerService cs = sling.getService(com.adobe.cq.CustomerService.cl
*/
