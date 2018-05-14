package com.ust.sagaeventsourcing.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.ust.sagaeventsourcing.event.EventReactor;
import com.ust.sagaeventsourcing.event.EventSubscribersLoader;

@Configuration
@EnableAsync // Para habilitar las llamadas async //
public class EventAutoConfiguration {

	private static final Logger log = LoggerFactory.getLogger(EventAutoConfiguration.class); 

	@Bean
	public EventReactor createEventReactor(){
		log.info("Starting EventReactor ...");
		return new EventReactor();
	}
	
	@Bean
	public EventSubscribersLoader createEventSubscribersLoader(){
		log.info("Starting EventSubscribersLoader ...");
		return new EventSubscribersLoader();
	}
	
}