package ust.laboratory.sortingservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ust.laboratory.sortingservice.api.dto.EnumStatus;
import ust.laboratory.sortingservice.api.dto.Execution;
import ust.laboratory.sortingservice.exception.SortingServiceException;
import ust.laboratory.sortingservice.service.ISortService;
import ust.laboratory.sortingservice.storage.Storage;

@Service
public class SortServiceImpl implements ISortService {
 
	private static final Logger Log = LoggerFactory.getLogger(SortServiceImpl.class);
	
	@Autowired
	private AsyncWorker worker;
	@Autowired
	private Storage storage;
	
	public Execution startExecution(int[] input) throws SortingServiceException {
		if(input==null || input.length<=1) {
			throw new SortingServiceException("Input data must have at least 2 numbers", SortingServiceException.INVALID_INPUT_DATA);
		}
		Execution execution=new Execution();
		execution.setId(UUID.randomUUID());
		execution.setStatus(EnumStatus.pending);
		// New Execution is created because it is necessary
		// to return an Execution with no all the attributes // 
		Execution executionToSort=new Execution();
		executionToSort.setInput(input);
		executionToSort.setId(execution.getId());
		executionToSort.setStatus(EnumStatus.pending);
		try {
			storage.save(executionToSort);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SortingServiceException(e.getMessage(), SortingServiceException.INTERNAL_ERROR);
		}
		worker.startExecution(executionToSort);		
		return execution;
	}
	
	public Execution getExecution(UUID id) throws SortingServiceException {
		Execution exec=null;
		try {
			exec=storage.getByUid(id);
		} catch (Exception e) {
			throw new SortingServiceException(e.getMessage(), SortingServiceException.INTERNAL_ERROR);
		}
		if(exec==null) {
			throw new SortingServiceException("ID: "+id+ " not found", SortingServiceException.ID_NOT_FOUND);
		}
		return exec;
	}
	
	public List<Execution> getExecutions() throws SortingServiceException {
		try {
			return storage.getAll();
		} catch (Exception e) {
			throw new SortingServiceException(e.getMessage(), SortingServiceException.INTERNAL_ERROR);
		}
	}

}
