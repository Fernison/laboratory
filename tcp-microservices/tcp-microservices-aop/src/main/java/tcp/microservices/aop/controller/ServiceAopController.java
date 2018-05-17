package tcp.microservices.aop.controller;  

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

import tcp.microservices.aop.api.request.AopRequest;
import tcp.microservices.aop.api.response.AopResponse;
import tcp.microservices.aop.service.IServiceAopService;
import tcp.microservices.common.api.response.BaseResponse;
import tcp.microservices.common.api.response.ResponseInfo;  

@RestController
@RequestMapping("/aop")
public class ServiceAopController {  
    
	private static final Logger log = LoggerFactory.getLogger(ServiceAopController.class); 
	
    @Autowired     
    private IServiceAopService service;  
   
//    @GetMapping("/helloWorld")     
//    public ResponseEntity<String> helloWorld(@RequestParam(name = "name") Optional<String> name) {             	
//    	return ResponseEntity.ok("Hola");     
//    } 
    
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
    public ResponseEntity<BaseResponse> send(@RequestBody final AopRequest aopRequest) {         
    	log.info("Request {}", aopRequest);  
    	AopResponse response = new AopResponse(); 
    	response.setData(service.doSomething(aopRequest.getAop()));    	
    	response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));  
    	log.info("Response: {}", response);
    	
    	// AOP Test
    	service.doOtherthing(null);
		
    	return ResponseEntity.ok(response);     
    } 
 } 