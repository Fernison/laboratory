package com.ust.sagaeventsourcing.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ust.sagaeventsourcing.event.EventSubscriberAspect;
import com.ust.sagaeventsourcing.saga.InitSagaAspect;

@Configuration
@EnableAspectJAutoProxy
public class SagaAutoConfiguration {
	 
	private static final Logger log = LoggerFactory.getLogger(SagaAutoConfiguration.class); 

	@Bean
	public InitSagaAspect createInitSagaAspect(){
		log.info("Starting InitSagaAspect Aspect ...");
		return new InitSagaAspect();
	}
	
	@Bean
	public EventSubscriberAspect createEventSubscriberAspect(){
		log.info("Starting EventSubscriberAspect Aspect ...");
		return new EventSubscriberAspect();
	}
	
}
