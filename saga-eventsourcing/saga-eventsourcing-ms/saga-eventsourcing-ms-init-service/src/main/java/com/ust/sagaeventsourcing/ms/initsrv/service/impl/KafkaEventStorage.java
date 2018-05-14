package com.ust.sagaeventsourcing.ms.initsrv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;

import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.ms.initsrv.api.dto.SimpleDto;
import com.ust.sagaeventsourcing.ms.initsrv.stream.InitSrvProcessor;
import com.ust.sagaeventsourcing.saga.Storage;

public final class KafkaEventStorage extends Storage<Eventide<SimpleDto>> {

	@Autowired
	private InitSrvProcessor initSrvProcessor;
	
	public KafkaEventStorage(String name, int type) {
		super(name, type);
	}
	
	@Override
	public void store(Eventide<SimpleDto> data) throws Exception {
		// Do logic to store data //
		initSrvProcessor.outputEvent().send(MessageBuilder.withPayload(data).build());
	}

}
