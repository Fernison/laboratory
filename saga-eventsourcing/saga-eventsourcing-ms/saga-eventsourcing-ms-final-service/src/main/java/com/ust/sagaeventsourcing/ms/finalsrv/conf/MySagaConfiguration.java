package com.ust.sagaeventsourcing.ms.finalsrv.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ust.sagaeventsourcing.ms.finalsrv.handler.KafkaEventStorage;
import com.ust.sagaeventsourcing.ms.finalsrv.handler.MyEventHandlers;
import com.ust.sagaeventsourcing.saga.Storage;


@Configuration
public class MySagaConfiguration {
	 		
	@SuppressWarnings("rawtypes")
	@Bean
	public Storage getStorage() {
		System.out.println("Starting getStorage ...");
		return new KafkaEventStorage("kafka",0);
	}

	@Bean
	public MyEventHandlers createEventHandlers(){
		System.out.println("Starting createEventHandlers ...");
		return new MyEventHandlers();
	}
}
