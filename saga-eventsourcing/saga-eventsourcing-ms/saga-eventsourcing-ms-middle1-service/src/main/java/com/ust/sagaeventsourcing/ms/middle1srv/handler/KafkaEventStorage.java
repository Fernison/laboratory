package com.ust.sagaeventsourcing.ms.middle1srv.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;

import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.ms.middle1srv.api.dto.SimpleDto;
import com.ust.sagaeventsourcing.ms.middle1srv.stream.MiddleSrvProcessor;
import com.ust.sagaeventsourcing.saga.Storage;

public final class KafkaEventStorage extends Storage<Eventide<SimpleDto>> {

	private static final Logger log = LoggerFactory.getLogger(KafkaEventStorage.class);

	@Autowired
	private MiddleSrvProcessor middleSrvProcessor;
	
	public KafkaEventStorage(String name, int type) {
		super(name, type);
	}
	
	@Override
	public void store(Eventide<SimpleDto> data) throws Exception {
		log.info("MiddleService. KafkaEventStorage:storage. Storing data:{}",data);
		// Do logic to store data //
		middleSrvProcessor.outputEvent().send(MessageBuilder.withPayload(data).build());
	}
	
}
