/*package com.myproject.services.canvas;



// canvas component
import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.EmptyDataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.ceb.webcq.components.canvas.CanvasUtils;
import com.ceb.webcq.services.revision.CEBRevisionService;
import com.day.cq.dam.commons.util.DamUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.servlet.Servlet;
import javax.servlet.http.Cookie;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate=true, metatype=true, label="Canvas Styles/Layouts", description="Stores and retrieves the styles/Layouts for Blogs in Canvas  ")
@Service({Servlet.class})
@Properties({@org.apache.felix.scr.annotations.Property(name="sling.servlet.methods", value={"POST"}, propertyPrivate=true), @org.apache.felix.scr.annotations.Property(name="sling.servlet.paths", value={"/bin/ceb/unauthenticatedservlet/canvasStyles"}, propertyPrivate=true), @org.apache.felix.scr.annotations.Property(name="sling.auth.requirements", value={"-/bin/ceb/unauthenticatedservlet/canvasStyles"}, propertyPrivate=true)})
public class CanvasStylesServlet
  extends SlingAllMethodsServlet
{
  private static final long serialVersionUID = 7081816354877045838L;
  private static final String DOT_XLSX = ".xlsx";
  private static final String SLASH = "/";
  private Logger logger;
  @Reference
  private CEBRevisionService revisionService;
  
  public CanvasStylesServlet()
  {
    this.logger = LoggerFactory.getLogger(getClass());
  }
  
  protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
    throws ServerException, IOException
  {
    if (request.getParameterMap().containsKey("layout")) {
      getSyleDefinitions(request, response);
    } else if (request.getParameterMap().containsKey("style")) {
      changeContentLayout(request, response);
    } else {
      getStyleNames(request, response);
    }
  }
  
  private void changeContentLayout(SlingHttpServletRequest request, SlingHttpServletResponse response)
  {
    try
    {
      String styleName = request.getParameter("style");
      String canvasPath = request.getParameter("canvasPath");
      ResourceResolver resourceResolver = request.getResourceResolver();
      Resource resource = resourceResolver.getResource(canvasPath);
      Node node = (Node)resource.adaptTo(Node.class);
      if ((styleName == null) || (node == null))
      {
        response.sendError(500);
        this.logger.error("Update style node aready exist.");
        return;
      }
      node.setProperty("layoutOption", styleName);
    }
    catch (Exception e)
    {
      this.logger.error("{}", e);
    }
  }
  
  private void getStyleNames(SlingHttpServletRequest request, SlingHttpServletResponse response)
    throws ServerException, IOException
  {
    String dataFormat = "";
    try
    {
      String authoringMode = request.getCookie("cq-authoring-mode").getValue();
      if (request.getParameterMap().containsKey("dataFormat")) {
        dataFormat = request.getParameter("dataFormat");
      }
      ResourceResolver resourceResolver = request.getResourceResolver();
      Resource resource = resourceResolver.getResource("/content/dam/Smart Component Layouts/Smart Canvas");
      Node node = (Node)resource.adaptTo(Node.class);
      if ((!authoringMode.equals("TOUCH")) || (dataFormat.equals("json")))
      {
        org.apache.sling.commons.json.JSONObject jsonData = CanvasUtils.getAllNodes(node);
        response.getWriter().write(jsonData.toString());
      }
      else
      {
        request.setAttribute(DataSource.class.getName(), EmptyDataSource.instance());
        List<Resource> fakeResourceList = new ArrayList();
        ValueMap vm = null;
        NodeIterator nodeIterator = node.getNodes();
        while ((nodeIterator != null) && (nodeIterator.hasNext()))
        {
          Node excelNode = nodeIterator.nextNode();
          String nodeName = excelNode.getName();
          if (!nodeName.equals("jcr:content"))
          {
            vm = new ValueMapDecorator(new HashMap());
            String url = "/content/dam/Smart Component Layouts/Smart Canvas/" + excelNode.getName();
            vm.put("value", url);
            vm.put("text", nodeName.substring(0, nodeName.length() - 5));
            fakeResourceList.add(new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", vm));
          }
        }
        DataSource ds = new SimpleDataSource(fakeResourceList.iterator());
        
        request.setAttribute(DataSource.class.getName(), ds);
      }
    }
    catch (Exception e)
    {
      this.logger.error("{}", e);
    }
  }
  
  private void getSyleDefinitions(SlingHttpServletRequest request, SlingHttpServletResponse response)
    throws ServerException, IOException
  {
    try
    {
      String styleName = request.getParameter("layout");
      ResourceResolver resourceResolver = request.getResourceResolver();
      Resource resource = resourceResolver.getResource(styleName);
      Node node = (Node)resource.adaptTo(Node.class);
      
      Map<String, String> map = CanvasUtils.readDataFromExcel(node);
      
      org.json.JSONObject stylesObject = new org.json.JSONObject();
      stylesObject.put("layoutFirst", map.get("layoutFirst"));
      stylesObject.put("layoutOther", map.get("layoutOther"));
      String jsonData = stylesObject.toString();
      response.getWriter().write(jsonData);
    }
    catch (JSONException e)
    {
      this.logger.error("{}", e);
    }
    catch (Exception e)
    {
      this.logger.error("{}", e);
    }
  }
  
  protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
    throws ServerException, IOException
  {
    boolean updateFlag = request.getParameter("updateFlag") != null;
    if (updateFlag) {
      update(request, response);
    } else {
      create(request, response);
    }
  }
  
  private void create(SlingHttpServletRequest request, SlingHttpServletResponse response)
  {
    try
    {
      String stylename = request.getParameter("stylename") + ".xlsx";
      String styletemplatefirst = request.getParameter("styletemplatefirst");
      String styletemplateother = request.getParameter("styletemplateother");
      
      Map<String, String> styleMap = new HashMap();
      styleMap.put("layoutFirst", styletemplatefirst);
      styleMap.put("layoutOther", styletemplateother);
      
      ResourceResolver resourceResolver = request.getResourceResolver();
      Resource resource = resourceResolver.getResource("/content/dam/Smart Component Layouts/Smart Canvas");
      Node node = (Node)resource.adaptTo(Node.class);
      if ((stylename == null) || (styletemplatefirst == null) || (styletemplateother == null) || (node == null) || (node.hasNode(stylename)))
      {
        response.getWriter().write("alreadyExists");
      }
      else
      {
        CanvasUtils.createExcel(stylename, styleMap, resourceResolver);
        response.getWriter().write("success");
      }
    }
    catch (Exception e)
    {
      this.logger.error("{}", e);
    }
  }
  
  private void update(SlingHttpServletRequest request, SlingHttpServletResponse response)
  {
    try
    {
      String styleName = request.getParameter("styleName");
      String layoutfirstsection = request.getParameter("layoutfirstsection");
      String layoutothersection = request.getParameter("layoutothersection");
      
      Map<String, String> styleMap = new HashMap();
      styleMap.put("layoutFirst", layoutfirstsection);
      styleMap.put("layoutOther", layoutothersection);
      
      ResourceResolver resourceResolver = request.getResourceResolver();
      String fileName = styleName.substring(styleName.lastIndexOf("/") + 1, styleName.length());
      this.revisionService.createRevision(DamUtil.resolveToAsset(resourceResolver.getResource(styleName)), resourceResolver);
      
      CanvasUtils.createExcel(fileName, styleMap, resourceResolver);
      
      org.json.JSONObject obj = new org.json.JSONObject();
      obj.put("styleName", styleName);
      obj.put("layoutFirst", layoutfirstsection);
      obj.put("layoutOther", layoutfirstsection);
      String jsonData = obj.toString();
      response.getWriter().write(jsonData);
    }
    catch (Exception e)
    {
      this.logger.error("{}", e);
    }
  }
  
  public JSONArray sortStyles(JSONArray stylesArray)
    throws JSONException
  {
    JSONArray sortedJsonArray = new JSONArray();
    List<org.json.JSONObject> jsonValues = new ArrayList();
    for (int i = 0; i < stylesArray.length(); i++) {
      jsonValues.add(stylesArray.getJSONObject(i));
    }
    Collections.sort(jsonValues, new Comparator()
    {
      public int compare(org.json.JSONObject a, org.json.JSONObject b)
      {
        String valA = new String();
        String valB = new String();
        try
        {
          valA = (String)a.get("layoutOption");
          valB = (String)b.get("layoutOption");
        }
        catch (JSONException e)
        {
          CanvasStylesServlet.this.logger.error("{}", e);
        }
        return valA.compareTo(valB);
      }
    });
    for (int i = 0; i < stylesArray.length(); i++) {
      sortedJsonArray.put(jsonValues.get(i));
    }
    return sortedJsonArray;
  }
  
  protected void bindRevisionService(CEBRevisionService paramCEBRevisionService)
  {
    this.revisionService = paramCEBRevisionService;
  }
  
  protected void unbindRevisionService(CEBRevisionService paramCEBRevisionService)
  {
    if (this.revisionService == paramCEBRevisionService) {
      this.revisionService = null;
    }
  }
}*/