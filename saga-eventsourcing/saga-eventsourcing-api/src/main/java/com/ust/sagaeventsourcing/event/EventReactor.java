package com.ust.sagaeventsourcing.event;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ust.sagaeventsourcing.saga.Reactor;
import com.ust.sagaeventsourcing.saga.Storage;

public class EventReactor implements Reactor<Eventide<?>> {

	private static final Logger log = LoggerFactory.getLogger(EventReactor.class); 

	@Autowired
	EventSubscribersLoader subsLoader;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Storage storage;
	
	
	// TODO: COMPROBAR TAMBIÉN EL ESTADO
	/*
	@SuppressWarnings("unchecked")
	@Override
	public void notifyEvent(Eventide<?> event) {
		try {
			log.info("EventReactor. notifyEvent init:{}",event);
			Hashtable<String,Hashtable<String, EventSubscriberLoaderData>> SUBSCRIBERS=subsLoader.getSubscribers();
			Hashtable<String, EventSubscriberLoaderData> sagas=SUBSCRIBERS.get(event.getSaga());
			log.info("EventReactor. notifyEvent SUBSCRIBERS:{}",SUBSCRIBERS);
			log.info("EventReactor. notifyEvent sagas:{}",sagas);
			if(sagas==null) {
				return;
			}
			EventSubscriberLoaderData data=sagas.get(event.getEventName());
			log.info("EventReactor. notifyEvent data:{}",data);
			if(data==null) {
				return;
			}			
			CompletableFuture.supplyAsync(() -> {
				try {					
					log.info("EventReactor. notifyEvent Inside supplyAsync");
					return (Eventide<?>)(data.getMethod().invoke(data.getBean(), event));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					log.error("EventReactor:notifyEvent. Error invoking method: "+data.getMethod()+" in bean: "+data.getBean()+". Error: "+e.getMessage());
					return null;
				}
			}).thenAccept(s -> {
				try {
					log.info("EventReactor. notifyEvent Inside thenAccept:{}",s);
					if(s!=null) {
						storage.store(s);
					}	
				} catch (Exception e) {
					log.error("EventReactor:notifyEvent. Could no store event: "+e.getMessage());
				}
			});
		} catch (Exception e) {
			log.error("EventReactor:notifyEvent. Error notifying event: "+e.getMessage());
		}
	}
	*/

	@SuppressWarnings("unchecked")
	@Override
	public void notifyEvent(Eventide<?> event) {
		try {
			log.info("EventReactor. notifyEvent init:{}",event);
			Hashtable<String,Hashtable<String,Hashtable<String,EventSubscriberLoaderData>>> SUBSCRIBERS=
					subsLoader.getSubscribers();
			Hashtable<String,Hashtable<String,EventSubscriberLoaderData>> sagas=SUBSCRIBERS.get(event.getSaga());
			log.info("EventReactor. notifyEvent SUBSCRIBERS:{}",SUBSCRIBERS);
			log.info("EventReactor. notifyEvent sagas:{}",sagas);
			if(sagas==null) {
				return;
			}
			Hashtable<String,EventSubscriberLoaderData> events=sagas.get(event.getEventName());
			if(events==null) {
				return;
			}			
			EventSubscriberLoaderData data=events.get(event.getStatus());
			log.info("EventReactor. notifyEvent data:{}",data);
			if(data==null) {
				return;
			}			
			CompletableFuture.supplyAsync(() -> {
				try {					
					log.info("EventReactor. notifyEvent Inside supplyAsync");
					return (Eventide<?>)(data.getMethod().invoke(data.getBean(), event));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					log.error("EventReactor:notifyEvent. Error invoking method: "+data.getMethod()+" in bean: "+data.getBean()+". Error: "+e.getMessage());
					return null;
				}
			}).thenAccept(s -> {
				try {
					log.info("EventReactor. notifyEvent Inside thenAccept:{}",s);
					if(s!=null) {
						storage.store(s);
					}	
				} catch (Exception e) {
					log.error("EventReactor:notifyEvent. Could no store event: "+e.getMessage());
				}
			});
		} catch (Exception e) {
			log.error("EventReactor:notifyEvent. Error notifying event: "+e.getMessage());
		}
	}
}
