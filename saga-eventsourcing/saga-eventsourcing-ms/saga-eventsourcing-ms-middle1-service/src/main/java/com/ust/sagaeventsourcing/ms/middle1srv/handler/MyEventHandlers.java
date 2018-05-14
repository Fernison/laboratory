package com.ust.sagaeventsourcing.ms.middle1srv.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ust.sagaeventsourcing.event.EventHandlerMethod;
import com.ust.sagaeventsourcing.event.EventSubscriber;
import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.ms.middle1srv.api.dto.SimpleDto;

@EventSubscriber(application="middle1", saga="mi saga")
public class MyEventHandlers {

	private static final Logger log = LoggerFactory.getLogger(MyEventHandlers.class);
	
	@EventHandlerMethod(event= "init event", status="ALTA OK")
	public Eventide<SimpleDto> operacion1(Eventide<SimpleDto> event) {
		log.info("Middle1. MyEventHandlers.operacion1: {}",event);
		// Do logic... //
		SimpleDto simpleDto=new SimpleDto();
		simpleDto.setData1("middle data1");
		simpleDto.setData2("middle data2");
		simpleDto.setId(System.currentTimeMillis());
		MyEvent myEvent=new MyEvent("middle1 event", simpleDto);		
		myEvent.setId_transaccion(event.getId_transaccion());
		myEvent.setStatus("UPDATE OK");
		return myEvent;		
	}

}
