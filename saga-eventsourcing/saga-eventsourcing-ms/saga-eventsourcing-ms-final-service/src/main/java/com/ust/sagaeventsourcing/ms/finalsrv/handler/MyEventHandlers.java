package com.ust.sagaeventsourcing.ms.finalsrv.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ust.sagaeventsourcing.event.EventHandlerMethod;
import com.ust.sagaeventsourcing.event.EventSubscriber;
import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.ms.finalsrv.api.dto.SimpleDto;

@EventSubscriber(application="final", saga="mi saga")
public class MyEventHandlers {

	private static final Logger log = LoggerFactory.getLogger(MyEventHandlers.class);
	
	@EventHandlerMethod(event= "middle2 event", status="UPDATE2 OK")
	public Eventide<SimpleDto> operacion1(Eventide<SimpleDto> event) {
		log.info("Final. MyEventHandlers.operacion1: {}",event);
		// Do logic... //
		SimpleDto simpleDto=new SimpleDto();
		simpleDto.setData1("final data1");
		simpleDto.setData2("final data2");
		simpleDto.setId(System.currentTimeMillis());
		MyEvent myEvent=new MyEvent("final event", simpleDto);		
		myEvent.setId_transaccion(event.getId_transaccion());
		myEvent.setStatus("COMMIT OK");
		return myEvent;		
	}

}
