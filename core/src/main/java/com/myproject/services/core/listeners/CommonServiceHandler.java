package com.myproject.services.core.listeners;

import java.util.Iterator;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.internal.xmp.options.IteratorOptions;
import com.day.cq.dam.api.DamEvent;
import com.day.cq.replication.ReplicationEvent;
import com.day.cq.wcm.api.PageEvent;
import com.day.cq.wcm.api.PageModification;

@Component(immediate = true)
@Service
@Properties({

		@Property(name = "event.topics", value = { PageEvent.EVENT_TOPIC, DamEvent.EVENT_TOPIC,
				ReplicationEvent.EVENT_TOPIC }) })

public class CommonServiceHandler implements EventHandler {

	private static final Logger log = LoggerFactory.getLogger(CommonServiceHandler.class);

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		log.debug("Handle event started");

		String eventTopic = event.getTopic();
		log.debug("Event triggered by : {}", eventTopic);
		if (eventTopic.equals(PageEvent.EVENT_TOPIC)) {
			log.debug("=====================page event triggered==============");
			log.debug("page event triggered");

			PageEvent pageEvent = PageEvent.fromEvent(event);
			log.debug("Page event is :{}", pageEvent);

			Iterator<PageModification> modification = pageEvent.getModifications();
			// this will return all the modifications what u have done to the
			// page
			while (modification.hasNext()) {
				PageModification pageModification = modification.next();
				log.debug("page modification path : {}", pageModification.getPath());
				log.debug("page modified type : {}", pageModification.getType());
				log.debug("page modified iser id : {}", pageModification.getUserId());
			}
		}
		else if (eventTopic.equals(DamEvent.EVENT_TOPIC)) {
			log.debug("=====================Dam event triggered=================");
			log.debug("Dam event Triggered");

			DamEvent damEvent = DamEvent.fromEvent(event);

			log.debug("DAM---------- path :{}", damEvent.getAssetPath());
			log.debug("dam type : {}", damEvent.getType());
			log.debug("dam user id : {}", damEvent.getUserId());
		}
		
		else if (eventTopic.equals(ReplicationEvent.EVENT_TOPIC)) {
			log.debug("=====================Replicatoion event triggered=========");
			log.debug("Replicatoion --------------event started");
			ReplicationEvent replicationEvent = ReplicationEvent.fromEvent(event);

			log.debug("Replication------------ event : {}", replicationEvent.getReplicationAction().getClass());
			log.debug("Replication ------ path  :  {}", replicationEvent.getReplicationAction().getPath());
			log.debug("Replication -------- user id: {}", replicationEvent.getReplicationAction().getUserId());
		}
	}
}