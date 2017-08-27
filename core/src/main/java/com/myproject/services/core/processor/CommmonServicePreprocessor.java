package com.myproject.services.core.processor;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.Preprocessor;
import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.ReplicationOptions;



@Component
@Service
public class CommmonServicePreprocessor implements Preprocessor {

		private static final Logger log= LoggerFactory.getLogger(CommmonServicePreprocessor.class);
	@Override
	public void preprocess(ReplicationAction replicationAction, ReplicationOptions replicationOptions) throws ReplicationException {
		// TODO Auto-generated method stub
		// from replication Action we can get the replication path and replication type
		
		
		/*Map<String,Object> paramMap = new HashMap<String,Object>();
	      paramMap.put(ResourceResolverFactory.SUBSERVICE, "CommonReferenceService");
	      ResourceResolver ServiceResourceResolver =null;
	      if(!replicationAction.getPath().startsWith("/content/fashion"))
		
		*/
		if(replicationAction.getType().equals(ReplicationActionType.ACTIVATE))
		{
		log.debug("Replication Path :{}",replicationAction.getPath());
		log.debug("Replication Type : {}", replicationAction.getTime());
		log.debug("Replication Userid : {}", replicationAction.getUserId());
		}
		else if(replicationAction.getType().equals(ReplicationActionType.DEACTIVATE))
		{
		log.debug("Replication Path :{}",replicationAction.getPath());
		log.debug("Replication Type : {}", replicationAction.getTime());
		log.debug("Replication Userid : {}", replicationAction.getUserId());
		}
	}

}
