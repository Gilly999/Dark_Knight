package com.myproject.services.core.use;

import javax.jcr.Item;
import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Style;

//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.SlingHttpServletResponse;
//import org.apache.sling.api.resource.Resource;
//import org.apache.sling.api.resource.ResourceResolver;
//import org.apache.sling.api.resource.ValueMap;
//
//import com.adobe.cq.sightly.WCMUse;
//import com.day.cq.wcm.api.Page;
//import com.day.cq.wcm.api.designer.Design;
//import com.day.cq.wcm.api.designer.Style;


public class SampleUse extends WCMUse
{
	private String pageValue="";	
	private static final Logger log=LoggerFactory.getLogger(SampleUse.class);
	@Override
	public void activate() throws RepositoryException, PersistenceException  {
		ValueMap properties=getProperties();
		ValueMap pageproperties=getPageProperties();
		Page page=getCurrentPage();
		SlingHttpServletRequest request=getRequest();
		SlingHttpServletResponse responce=getResponse();
		Resource resource=getResource();
		ResourceResolver resourceResolver=getResourceResolver();
		Design currentDesign=getCurrentDesign();
		Style currentStyle=getCurrentStyle();
		pageValue=page.getPath();		
			
		AdaptTo(resource,Node.class);
		AEMApi(page);
		SlingApi(resource, resourceResolver);
		JCRApi();
	}
		private void JCRApi() {
		// TODO Auto-generated method stub
	}
		private void SlingApi(Resource resource, ResourceResolver resolver) throws PersistenceException, ItemExistsException, PathNotFoundException, NoSuchNodeTypeException, LockException, VersionException, ConstraintViolationException, RepositoryException {
		// TODO Auto-generated method stub
			Node resourceNode = resource.adaptTo(Node.class);
			ResourceResolver Resolver = resource.getResourceResolver();
			log.debug("**************Reso API Start*****************");
			log.debug("Resource Path: {}", resource.getPath());
			log.debug("Resource Parent Path: {}", resource.getParent());
			log.debug("Resource has children: {}", resource.hasChildren());
			log.debug("****************************************");
			 resourceNode.addNode("nodebySlingAPI", "nt:unstructured");
			Resolver.commit();
			log.debug("****************************************");
			log.debug("***********Reso API Stop**************");

		}
		private void AEMApi(Page page) {
		// TODO Auto-generated method stub
			//Page page=getCurrentPage();
			log.debug("****************************************");
			log.debug("page Path : {} ", page.getPageTitle());
			
			log.debug("****************************************");
			log.debug("page Class : {} ", page.getClass());
			
			
			log.debug("****************************************");
			log.debug("page Content Resource : {} ", page.getContentResource());
			
			log.debug("****************************************");
			log.debug("page Depth : {} ", page.getDepth());
			
			log.debug("****************************************");
			log.debug("page name : {} ", page.getName());
			
			log.debug("****************************************");
			log.debug("page last modified : {} ", page.getLastModified());
			
			log.debug("****************************************");
			log.debug("page Navigation : {} ", page.getNavigationTitle());
			
			log.debug("****************************************");
			log.debug("page PageManager : {} ", page.getPageManager());
			
			log.debug("****************************************");
			log.debug("page PageTitle : {} ", page.getPageTitle());
			
			log.debug("****************************************");
			log.debug("page Parent : {} ", page.getParent());
			
			log.debug("****************************************");
			log.debug("page hasContent : {} ", page.hasContent());
			
			log.debug("************** Child ********************");
			
			log.debug("page hasChild : {} ", page.hasChild(getPageValue()));
			log.debug("Page has child : {}", page.hasChild("archive"));
			
			log.debug("*************** Child  ******************");
			log.debug("page Path : {} ", page.getPath());
			
			log.debug("****************************************");
			log.debug("page Template : {} ", page.getTemplate());
			
			log.debug("****************************************");
			log.debug("page Properties : {} ", page.getProperties());
			
			log.debug("****************************************");
			log.debug("page Path : {} ", page.getTitle());
			log.debug("page Absolute Parent : {} ", page.getAbsoluteParent(2).getPath());

			log.debug("****************************************");
			
			
			
			
			
	}
		private void AdaptTo(Resource resource, Class<Node> class1) throws RepositoryException, PersistenceException {
		// TODO Auto-generated method stub
			
			Node resourceNode = resource.adaptTo(Node.class);
			ResourceResolver resourceResolver = resource.getResourceResolver();
			Session session = resourceNode.getSession();
			
		/*	log.debug("**************Node API*********************");
			  log.debug("Node Path: {}", resourceNode.getPath());
			  log.debug("Node Parent Path: {}", resourceNode.getParent());
			  log.debug("Node Parent has child nodes: {}",resourceNode.hasNodes());
			  resourceNode.addNode("nodebyNodeAPI","nt:unstructured");
			  resourceNode.addNode("nodebySlingAPI", "nt:unstructured");
			  session.save();*/
			  
			  
			  log.debug("****************************************");
			
			
			  
			
			
			
			
			
			
			
			
			
//		Node resourceNode=resource.adaptTo(Node.class);
//		//log.debug("Resource--------Path : {}",resource.getPath());
//		log.debug("Node------------Path : {}",resourceNode.getPath());
//		log.debug("Node has  Property ----------- : {}", resourceNode.hasProperty("ImagePath"));
//		log.debug("Node get Property  : {}", resourceNode.getProperty("ImagePath").getString());
//		log.debug("Adding a Node  : {}", resourceNode.addNode("testNode","nt:unstructured").toString());
//		resourceNode.getSession().save();
//		resourceNode.getNode("testNode").remove();
//		Node addNode=resourceNode.addNode("testNode123","nt:unstructured");
//			addNode.setProperty("mani","This is Manikanta");
//			resourceNode.getSession().save();
//			
//		Node parentNode=resourceNode.getParent();
//		
//		//log.debug("Parent Node---Path: {}", resourceNode.parentNode.getPath());
//		log.debug("Parent Node---Path: {}", parentNode.getPath());
//		
//		resourceNode.getSession().save();
//		resource.listChildren();
//		resource.getChildren();
//		resource.listChildren();
//		
//		resource.getResourceSuperType();
//		resource.getResourceType();
//		log.debug("Parent Path : {}",resource.getParent());
//		log.debug("parent Node: {}", resource.getName());
//		//log.debug("Parent :{}",);
		
		//Node pageNode=class1.adaptTo(Node.class);
		//class1.adaptTo(Resource.class);
		//resource.adaptTo(Page.class);
		//resource.adaptTo(ValueMap.class);
		
	}
		public String getPageValue()
		{
			return pageValue;
	}
}