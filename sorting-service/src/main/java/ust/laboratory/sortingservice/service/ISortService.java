package ust.laboratory.sortingservice.service;

import java.util.List;
import java.util.UUID;

import ust.laboratory.sortingservice.api.dto.Execution;
import ust.laboratory.sortingservice.exception.SortingServiceException;

public interface ISortService {

	public Execution startExecution(int[] input) throws SortingServiceException;
	public Execution getExecution(UUID id) throws SortingServiceException;
	public List<Execution> getExecutions() throws SortingServiceException;

}
