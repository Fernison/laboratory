package com.ust.sagaeventsourcing.ms.initsrv.controller;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sagaeventsourcing.ms.common.api.response.BaseResponse;
import com.ust.sagaeventsourcing.ms.common.api.response.ResponseInfo;
import com.ust.sagaeventsourcing.ms.initsrv.api.request.SimpleRequest;
import com.ust.sagaeventsourcing.ms.initsrv.service.ISimpleService;

@RestController 
@RequestMapping("/simple")
public class SimpleServiceController {  
    
	private static final Logger log = LoggerFactory.getLogger(SimpleServiceController.class); 
	
    @Autowired     
    private ISimpleService service;  
   
	@GetMapping(path = "/test", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> print() {
		log.debug("Entering print()");
		BaseResponse response = new BaseResponse();		
		response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));  
		try {
			service.print(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Leaving print()");
		return ResponseEntity.ok(response);
	}

	@PostMapping(path = "/test", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> print(final @RequestBody SimpleRequest param) {
		log.info("Entering print():{}",param);
		BaseResponse response = new BaseResponse();		
		response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));  
		try {
			service.print(param.getData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Leaving print()");
		return ResponseEntity.ok(response);
	}
	
 } 