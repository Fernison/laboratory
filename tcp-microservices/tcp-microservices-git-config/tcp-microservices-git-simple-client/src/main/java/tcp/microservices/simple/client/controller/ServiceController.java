package tcp.microservices.simple.client.controller;  

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcp.microservices.common.api.response.BaseResponse;
import tcp.microservices.common.api.response.ResponseInfo;
import tcp.microservices.simple.client.service.IConfigService;  

@RestController
@RequestMapping("/test")
public class ServiceController {  
    
	private static final Logger log = LoggerFactory.getLogger(ServiceController.class); 
	   
	@Autowired
	private IConfigService service;
	
	@GetMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> test(@RequestParam(name = "prefix") Optional<String> param) {
		log.debug("Entering test()");
		BaseResponse response = new BaseResponse();
		response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));
		service.testRefresh(param);
		log.debug("Leaving test()");
		return ResponseEntity.ok(response);
	}

 } 