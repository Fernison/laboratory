package com.ust.sagaeventsourcing.ms.initsrv.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ust.sagaeventsourcing.ms.initsrv.service.impl.KafkaEventStorage;
import com.ust.sagaeventsourcing.ms.initsrv.service.impl.MySagaPart;
import com.ust.sagaeventsourcing.saga.Storage;

@Configuration
public class MySagaConfiguration {
	 	
	@Bean
	public MySagaPart createSagaPart(){
		System.out.println("Starting createSagaPart Aspect ...");
		return new MySagaPart();
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public Storage getStorage() {
		System.out.println("Starting getStorage ...");
		return new KafkaEventStorage("kafka",0);
	}

}
