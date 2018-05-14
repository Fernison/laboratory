package com.ust.sagaeventsourcing.ms.finalsrv.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.ust.sagaeventsourcing.event.EventReactor;
import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.ms.finalsrv.api.dto.SimpleDto;
import com.ust.sagaeventsourcing.ms.finalsrv.stream.MiddleSrvProcessor;

@Service
public class MyEventListener {

	private static final Logger log = LoggerFactory.getLogger(MyEventListener.class);

	@Autowired
	EventReactor reactor;
	
    @StreamListener(MiddleSrvProcessor.READ)
    public void receiveNotificationEvent(final Eventide<SimpleDto> event) {
        log.info("Final service. Entering receiveNotificationEvent(event={})", event);        
        reactor.notifyEvent(event);
    }

}
