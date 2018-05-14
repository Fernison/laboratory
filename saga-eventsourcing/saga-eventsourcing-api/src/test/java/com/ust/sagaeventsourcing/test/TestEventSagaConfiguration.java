package com.ust.sagaeventsourcing.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ust.sagaeventsourcing.saga.Storage;
import com.ust.sagaeventsourcing.test.TestSagaPart;

@Configuration
public class TestEventSagaConfiguration {
	 	
	@Bean
	public TestSagaPart createSagaPart(){
		System.out.println("Starting createSagaPart Aspect ...");
		return new TestSagaPart();
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public Storage getStorage() {
		System.out.println("Starting getStorage ...");
		return new TestEventFileStorage();
	}

	@Bean
	public TestEventHandler1 createEventHandlers(){
		System.out.println("Starting createEventHandlers ...");
		return new TestEventHandler1();
	}
	
	@Bean
	public TestEventHandler2 createEventHandlers2(){
		System.out.println("Starting createEventHandlers ...");
		return new TestEventHandler2();
	}
}
