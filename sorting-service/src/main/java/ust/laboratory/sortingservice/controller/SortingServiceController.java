package ust.laboratory.sortingservice.controller;  

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ust.laboratory.sortingservice.api.dto.Execution;
import ust.laboratory.sortingservice.api.response.ExecutionListResponse;
import ust.laboratory.sortingservice.exception.SortingServiceException;
import ust.laboratory.sortingservice.service.ISortService;

@RestController
@RequestMapping("/mergesort")
public class SortingServiceController {  
    
	private static final Logger log = LoggerFactory.getLogger(SortingServiceController.class); 

	@Autowired
	private ISortService service;
    
	@ApiOperation(value = "Add a sort execution", response = Execution.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully added execution"),
	        @ApiResponse(code = 400, message = "Error adding sort execution")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)     
    public ResponseEntity<Execution> sortingRequest(@ApiParam(value = "Number array to sort", required = true)
    													@RequestBody final int[] input) {         
    	log.debug("Request {}", input);  
    	try {
			return ResponseEntity.ok(service.startExecution(input));
		} catch (SortingServiceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}   
    } 
	
	@ApiOperation(value = "Get a sort execution by execution ID", response = Execution.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved execution"),
	        @ApiResponse(code = 400, message = "Error getting sort execution"),
	        @ApiResponse(code = 404, message = "Execution ID not found")
	})
	@GetMapping(path = "/executions/{id}", produces = {	MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Execution> sortingExecution(@ApiParam(value = "Execution ID") @PathVariable("id") UUID id) {
    	log.info("Request {}", id);  
    	try {
			return ResponseEntity.ok(service.getExecution(id));
		} catch (SortingServiceException e) {
			if(e.getCode()==SortingServiceException.ID_NOT_FOUND) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);				
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);				
			}
		}  
	}
	
	@ApiOperation(value = "Get sort execution list", response = Execution.class, responseContainer = "List")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved execution list"),
	        @ApiResponse(code = 400, message = "Error getting execution list")
	})
	@GetMapping(path = "/executions", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ExecutionListResponse> sortingExecutionList() {
    	log.info("Request {}");  
    	ExecutionListResponse resp=new ExecutionListResponse();
    	try {
			resp.setExecutions(service.getExecutions());
	    	return ResponseEntity.ok(resp);  
		} catch (SortingServiceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
 } 