package com.ust.sagaeventsourcing.ms.middle2srv.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ust.sagaeventsourcing.event.EventHandlerMethod;
import com.ust.sagaeventsourcing.event.EventSubscriber;
import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.ms.middle2srv.api.dto.SimpleDto;

@EventSubscriber(application="middle2", saga="mi saga")
public class MyEventHandlers {

	private static final Logger log = LoggerFactory.getLogger(MyEventHandlers.class);
	
	@EventHandlerMethod(event= "middle1 event", status="UPDATE OK")
	public Eventide<SimpleDto> operacion1(Eventide<SimpleDto> event) {
		log.info("Middle2. MyEventHandlers.operacion1: {}",event);
		// Do logic... //
		SimpleDto simpleDto=new SimpleDto();
		simpleDto.setData1("middle2 data1");
		simpleDto.setData2("middle2 data2");
		simpleDto.setId(System.currentTimeMillis());
		MyEvent myEvent=new MyEvent("middle2 event", simpleDto);		
		myEvent.setId_transaccion(event.getId_transaccion());
		myEvent.setStatus("UPDATE2 OK");
		return myEvent;		
	}

}
