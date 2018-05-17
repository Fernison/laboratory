package tcp.microservices.simple.controller;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcp.microservices.common.api.response.BaseResponse;
import tcp.microservices.common.api.response.ResponseInfo;
import tcp.microservices.cor.annotations.Capturable;
import tcp.microservices.simple.service.ISimpleService;  

@RestController 
@RequestMapping("/simple")
public class SimpleServiceController {  
    
	private static final Logger log = LoggerFactory.getLogger(SimpleServiceController.class); 
	
    @Autowired     
    private ISimpleService service;  
   
	@GetMapping(path = "/test", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@Capturable(param1="parametro 1", param2="parametro 2")
	public ResponseEntity<BaseResponse> print() {
		log.debug("Entering print()");
		BaseResponse response = new BaseResponse();
		response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));  
		log.debug("Leaving print()");
		return ResponseEntity.ok(response);
	}

    
 } 