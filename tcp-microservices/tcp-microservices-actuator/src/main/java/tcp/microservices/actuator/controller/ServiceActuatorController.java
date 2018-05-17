package tcp.microservices.actuator.controller;  

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

import tcp.microservices.actuator.api.request.ActuatorRequest;
import tcp.microservices.actuator.api.response.ActuatorResponse;
import tcp.microservices.actuator.service.IConfigService;
import tcp.microservices.actuator.service.IServiceActuatorService;
import tcp.microservices.common.api.response.BaseResponse;
import tcp.microservices.common.api.response.ResponseInfo;  

@RestController
@RequestMapping("/actuator")
public class ServiceActuatorController {  
    
	private static final Logger log = LoggerFactory.getLogger(ServiceActuatorController.class); 
	
    @Autowired     
    private IServiceActuatorService service;  
    @Autowired     
    private IConfigService cService;  
   
	@GetMapping(path = "/", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> test() {
		log.debug("Entering test()");
		BaseResponse response = new BaseResponse();
		response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));  
		log.debug("Leaving test()");
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    		produces = MediaType.APPLICATION_JSON_UTF8_VALUE)     
    public ResponseEntity<BaseResponse> send(@RequestBody final ActuatorRequest request) {         
    	log.info("Request {}", request);  
    	ActuatorResponse response = new ActuatorResponse(); 
    	response.setData(service.doSomething(request.getActuator()));    	
    	response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));  
    	log.info("Response: {}", response);
    	
    	// AOP Test
    	service.doOtherthing(null);
    	
    	//Spring Coud Config Test
    	cService.testRefresh(null);
		
    	return ResponseEntity.ok(response);     
    } 
 } 