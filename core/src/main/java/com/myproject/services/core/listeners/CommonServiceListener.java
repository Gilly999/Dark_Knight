package com.myproject.services.core.listeners;

import javax.jcr.Session;
import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;
import javax.security.auth.login.LoginContext;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate=true, metatype=true)
@Service
public class CommonServiceListener implements EventListener {

	@Reference
	SlingRepository repository;
	private Session session=null;
	private ObservationManager observationManager= null;
	private static final Logger log=LoggerFactory.getLogger(CommonServiceListener.class); 

	@Override
	public void onEvent(EventIterator ite) {
		// TODO Auto-generated method stub
		// we write logics here.
		// what should be done after triggering.
		log.debug("-----------      onEvent       ---------------");
		log.debug("-----------      onEvent  test ---------------");
		
		log.debug("On event started");
		try
		{
			while(ite.hasNext())
			{
				Event event=ite.nextEvent();
				
				
		//max we use these three		
				
				log.debug("get user Id :  {}",	event.getUserID());
				log.debug("get Path name is :  {}",		event.getPath());
				log.debug("get user Id :  {}",	event.getType());
				
		// above three		
				
				log.debug("Class of :  {}",		event.getClass());
					log.debug("Date of :  {}",		event.getDate());
					
					
					log.debug("Identifier of :  {}",event.getIdentifier());
					log.debug("Get info of :  {}",	event.getInfo());
					log.debug("get Path name is :  {}",		event.getPath());
					log.debug("getType of :  {}",	event.getType());
					log.debug("User data :  {}",	event.getUserData());
					log.debug("get user Id :  {}",	event.getUserID());
					log.debug("get node added :  {}", event.PROPERTY_CHANGED);
					log.debug("get node Moved :  {}",event.NODE_MOVED);
					log.debug("get node added :  {}", 		    event.NODE_ADDED);
					log.debug("get property added :  {}",		event.PROPERTY_ADDED);
					log.debug("get property changed :  {}",		event.PROPERTY_CHANGED);
					log.debug("get property removed :  {}",		event.PROPERTY_REMOVED);
			}
		}
		catch(RepositoryException e)
		{
			//log.debug("repository Exception : {} ", e);
			log.error("repository exception :{}", e);
		}
	}
	
	@Activate
	public void activate(ComponentContext context)
	{
		
		//session=repository.loginAdministrative("crx.default");
		
		
		//  in active method we registering the events.
		log.debug("-----------  activate    ---------------");
		log.debug("-----------  activate  test  ---------------");
		try{
	
			/*
			 
 First we create system- user and we give some limited permissions
  to that System-user. and we pass like this.
  

  	 		repository.loginService("we pass the system-user here", null)
  	 
  	 session=repository.loginService("CommonReferenceService", null);
	 and we map this user in user-Map service.
	 
	 i pass CommonReferenceService in service Name.
	 
	 
	 bundleId:ServiceName=userName
	 
	*/ 
			
			
	 session=repository.loginService("CommonReferenceService", null);
			if(null != session)
			{
				log.debug("Session created user : {}", session.getUserID());
				//log.debug(session.get);
			observationManager=session.getWorkspace().getObservationManager();
			observationManager.addEventListener(this, Event.NODE_ADDED, "/content/fashion", true, 
					null, null, false);
			}
			
		}
		catch(LoginException e)
		{
			log.error("Login Exception: {}", e);
		}
		catch(RepositoryException e)
		{
			log.error("Repository Exception :{}", e);
		}
	}
	
	@Deactivate
	// when you un-install the bundle de-activate method will be called 
	void deactivate()
	//This is for De-registering the the events in the event listener.
	{
		log.debug("-----------   deactivate    ---------------");
		if(null !=observationManager)
		{
			try{
				observationManager.removeEventListener(this);
				
			}
			catch(RepositoryException e)
			{
			 log.error("Repository Exception : {}", e);
		}
		
		if(null !=session)
		{
			session.logout();
		}
		}
	}
}