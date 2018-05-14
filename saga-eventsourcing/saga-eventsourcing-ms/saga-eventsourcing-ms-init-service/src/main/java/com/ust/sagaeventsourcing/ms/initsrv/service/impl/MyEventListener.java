package com.ust.sagaeventsourcing.ms.initsrv.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.ust.sagaeventsourcing.event.EventReactor;
import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.ms.initsrv.api.dto.SimpleDto;
import com.ust.sagaeventsourcing.ms.initsrv.stream.InitSrvProcessor;

@Service
public class MyEventListener {

	private static final Logger log = LoggerFactory.getLogger(MyEventListener.class);

	@Autowired
	EventReactor reactor;

	@StreamListener(InitSrvProcessor.READ)
    public void receiveNotificationEvent(final Eventide<SimpleDto> event) {
        log.info("Init service. Entering receiveNotificationEvent(event={})", event);    
        reactor.notifyEvent(event);
    }

}