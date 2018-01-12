package ust.laboratory.sortingservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ust.laboratory.sortingservice.api.dto.EnumStatus;
import ust.laboratory.sortingservice.api.dto.Execution;
import ust.laboratory.sortingservice.controller.SortingServiceController;
import ust.laboratory.sortingservice.exception.SortingServiceException;
import ust.laboratory.sortingservice.service.ISortService;
import ust.laboratory.sortingservice.sorter.Sorter;
import ust.laboratory.sortingservice.storage.Storage;

@Service
public class SortServiceImpl implements ISortService {
 
	private static final Logger log = LoggerFactory.getLogger(SortServiceImpl.class);
	
	@Autowired
	private AsyncWorker worker;
	@Autowired
	private Storage storage;
	
	public Execution startExecution(int[] input) throws SortingServiceException {
		Execution execution=new Execution();
		execution.setId(UUID.randomUUID());
		execution.setStatus(EnumStatus.pending);
		// New Execution is created because it ir necessary
		// to return an Execution with no all the attributes // 
		Execution executionToSort=new Execution();
		executionToSort.setInput(input);
		executionToSort.setId(UUID.randomUUID());
		executionToSort.setStatus(EnumStatus.pending);
		storage.save(executionToSort);
		worker.startExecution(executionToSort);		
		return execution;
	}
	
	public Execution getExecution(UUID id) throws SortingServiceException {
		if(storage.getByUid(id)==null) {
			throw new SortingServiceException("ID: "+id+ " not found", SortingServiceException.ID_NOT_FOUND);
		}
		return storage.getByUid(id);
	}
	
	public List<Execution> getExecutions() throws SortingServiceException {
		return storage.getAll();
	}

}
