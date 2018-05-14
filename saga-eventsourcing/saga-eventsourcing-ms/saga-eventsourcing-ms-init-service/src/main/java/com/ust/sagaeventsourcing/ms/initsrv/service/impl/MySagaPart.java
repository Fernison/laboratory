package com.ust.sagaeventsourcing.ms.initsrv.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.ms.initsrv.api.dto.SimpleDto;
import com.ust.sagaeventsourcing.saga.InitSaga;
import com.ust.sagaeventsourcing.saga.SagaInitiator;

public class MySagaPart extends SagaInitiator<Eventide<SimpleDto>,SimpleDto> {
 
	private static final Logger log = LoggerFactory.getLogger(MySagaPart.class); 

	@Override
	@InitSaga(application="init", saga="mi saga")
	public Eventide<SimpleDto> initiateSaga(SimpleDto simpleDto) {		
		// Do logic...//
		MyEvent myEvent=new MyEvent("init event", simpleDto);
		myEvent.setId_transaccion(""+UUID.randomUUID());
		myEvent.setStatus("ALTA OK");
		log.info("Saga Event created:{}",myEvent);
		// Always return the event //
		return myEvent;
	}
	
}
