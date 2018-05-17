package tcp.microservices.simple.client.controller;  

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcp.microservices.check.annotations.ValidateHeader;
import tcp.microservices.common.api.response.BaseResponse;
import tcp.microservices.common.api.response.ResponseInfo;  

@RestController
@RequestMapping("/test")
public class ServiceController {  
    
	private static final Logger log = LoggerFactory.getLogger(ServiceController.class); 
	   	
	@GetMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	//@ValidateHeader(value="servicio real") // Anotacion de Interceptor
	@ValidateHeader(param1="parametro 1", param2="parametro 2") // Anotacion de Annotations
	public ResponseEntity<BaseResponse> test(@RequestParam(name = "prefix") Optional<String> param) {
		log.debug("Entering test()");
		BaseResponse response = new BaseResponse();
		response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));
		log.debug("Leaving test()");
		return ResponseEntity.ok(response);
	}

	@GetMapping(path="/test2", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> test2(@RequestParam(name = "prefix") Optional<String> param) {
		log.debug("Entering test2()");
		BaseResponse response = new BaseResponse();
		response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));
		log.debug("Leaving test2()");
		return ResponseEntity.ok(response);
	}
	
 } 