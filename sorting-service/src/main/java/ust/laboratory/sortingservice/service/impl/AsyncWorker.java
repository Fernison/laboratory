package ust.laboratory.sortingservice.service.impl;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import ust.laboratory.sortingservice.api.dto.EnumStatus;
import ust.laboratory.sortingservice.api.dto.Execution;
import ust.laboratory.sortingservice.sorter.Sorter;
import ust.laboratory.sortingservice.storage.Storage;

@Component
public class AsyncWorker {

	private static final Logger log = LoggerFactory.getLogger(AsyncWorker.class);

	@Autowired
	private Sorter sorter;
	@Autowired
	private Storage storage;
	
	@Async
	public void startExecution(Execution execution) {
		log.debug("startExecution {}", execution);
		// ordenar //
		long init=System.currentTimeMillis();
		int[] output=sorter.sort(execution.getInput());
		long end=System.currentTimeMillis();		
		// Guardar resultado de la ordenacion //		
		execution.setOutput(output);
		execution.setStatus(EnumStatus.completed);
		execution.setDuration(end-init);
		execution.setTimestamp(new Date());
	    storage.save(execution);
	}
}
