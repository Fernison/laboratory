package com.ust.sagaeventsourcing.event;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Class that loads all event subscribers and handlers
 * Store in a Hashtable with structure: SAGA|EVENT
 * @author fcabrero
 *
 */
public class EventSubscribersLoader implements ApplicationContextAware {

	// TODO: INCLUIR EL ESTADO COMO CLAVE
	
	private static final Logger log = LoggerFactory.getLogger(EventSubscribersLoader.class); 

	/*
	private Hashtable<String,Hashtable<String, EventSubscriberLoaderData>> SUBSCRIBERS=new Hashtable<String,Hashtable<String, EventSubscriberLoaderData>>();
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	System.out.println("setApplicationContext: ");
    	log.info("Loading event subscribers and handlers...");
    	for(String beanName: applicationContext.getBeanDefinitionNames()){
    		Object bean=applicationContext.getBean(beanName);    		
    		String className=AopUtils.getTargetClass(bean).getName();
    		Class<?> cl= null;
			try {
				cl = Class.forName(className);
			} catch (ClassNotFoundException e2) {
				continue;
			}    
            if(cl.isAnnotationPresent(EventSubscriber.class)) {
            	EventSubscriber subscriber = cl.getAnnotation(EventSubscriber.class);
            	Hashtable<String, EventSubscriberLoaderData> METHODS=new Hashtable<String,EventSubscriberLoaderData>();
		        for(Method m: cl.getDeclaredMethods()){		        	
		            if(m.isAnnotationPresent(EventHandlerMethod.class)) {
		            	EventHandlerMethod eventHandler = m.getAnnotation(EventHandlerMethod.class);
		            	for(String e: eventHandler.events()){		            		
		            		METHODS.put(e, new EventSubscriberLoaderData(bean, m));
		            	}
		            }
		        }
		        SUBSCRIBERS.put(subscriber.saga(), METHODS);
            }		
    	}
    	log.info("Subscribers and handlers list: "+SUBSCRIBERS);
    	log.info("Loading event subscribers and handlers...DONE");
    }	
	
	public Hashtable<String,Hashtable<String, EventSubscriberLoaderData>> getSubscribers() {
		return SUBSCRIBERS;
	}
	*/

	//		SAGA			 EVENT			  STATUS 	
	private Hashtable<String,Hashtable<String,Hashtable<String,EventSubscriberLoaderData>>> SUBSCRIBERS=new Hashtable<String,Hashtable<String,Hashtable<String,EventSubscriberLoaderData>>>();
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	System.out.println("setApplicationContext: ");
    	log.info("Loading event subscribers and handlers...");
    	for(String beanName: applicationContext.getBeanDefinitionNames()){
    		Object bean=applicationContext.getBean(beanName);    		
    		String className=AopUtils.getTargetClass(bean).getName();
    		Class<?> cl= null;
			try {
				cl = Class.forName(className);
			} catch (ClassNotFoundException e2) {
				continue;
			}    
            if(cl.isAnnotationPresent(EventSubscriber.class)) {
            	EventSubscriber subscriber = cl.getAnnotation(EventSubscriber.class);
            	
            	Hashtable<String,Hashtable<String,EventSubscriberLoaderData>> EVENTS=
            			new Hashtable<String,Hashtable<String,EventSubscriberLoaderData>>();
//            	Hashtable<String, EventSubscriberLoaderData> METHODS=
//            			new Hashtable<String,EventSubscriberLoaderData>();
		        
            	for(Method m: cl.getDeclaredMethods()){		        	
		            if(m.isAnnotationPresent(EventHandlerMethod.class)) {
		            	EventHandlerMethod eventHandler = m.getAnnotation(EventHandlerMethod.class);
		            	Hashtable<String, EventSubscriberLoaderData> METHODS=EVENTS.get(eventHandler.event());
		            	if(METHODS==null) {
		            		METHODS=new Hashtable<String,EventSubscriberLoaderData>();
		            	}
	            		METHODS.put(eventHandler.status(), new EventSubscriberLoaderData(bean, m));	
		            	EVENTS.put(eventHandler.event(), METHODS);           	
		            }
		        }          	
		        SUBSCRIBERS.put(subscriber.saga(), EVENTS);
            }		
    	}
    	log.info("Subscribers and handlers list: "+SUBSCRIBERS);
    	log.info("Loading event subscribers and handlers...DONE");
    }	
	
	public Hashtable<String,Hashtable<String,Hashtable<String,EventSubscriberLoaderData>>> getSubscribers() {
		return SUBSCRIBERS;
	}

}
