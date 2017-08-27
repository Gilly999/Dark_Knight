package com.myproject.services.core.workflows;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;



@Component(immediate=true)
@Service
@Properties({
	
		@Property 
		(name="process.label", value="---------My project Workflow Service--------")	})
		//name="process.label"------------ is mandatory

/* value="My project Workflow Service"  this name may be any name 
   but this name should be reflected to dropdown in Process step. 
*/

public class CommonServiceWorkflow implements WorkflowProcess {
	private static final Logger log=LoggerFactory.getLogger(CommonServiceWorkflow.class);
	@Override
	public void execute(WorkItem item, WorkflowSession wfsession, MetaDataMap map) throws WorkflowException {
		// TODO Auto-generated method stub
		log.debug("CommonServiceWorkflow started======");
		item.getWorkflow().getState();
		item.getId().length();
	}
}