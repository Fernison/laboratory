package tcp.microservices.couchbase.controller;  

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

import tcp.microservices.common.api.response.BaseResponse;
import tcp.microservices.common.api.response.ResponseInfo;
import tcp.microservices.couchbase.api.request.UserRequest;
import tcp.microservices.couchbase.api.response.UserListResponse;
import tcp.microservices.couchbase.service.IServiceCouchbaseService;  

@RestController
@RequestMapping("/couchbase")
public class ServiceCouchbaseController {  
    
	private static final Logger log = LoggerFactory.getLogger(ServiceCouchbaseController.class); 
	
    @Autowired     
    private IServiceCouchbaseService service;  
   
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
    public ResponseEntity<BaseResponse> save(@RequestBody final UserRequest couchbaseRequest) {         
    	log.info("Request {}", couchbaseRequest);  
    	BaseResponse response = new BaseResponse(); 
    	try {
			service.save(couchbaseRequest.getUser());
	    	response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));  
		} catch (Exception e) {
			log.error("save.Error: {}", e);
	    	response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_NOK, BaseResponse.DESC_NOK));  
		}
    	log.info("Response: {}", response);    	
    	return ResponseEntity.ok(response);     
    } 
	
	@GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    		produces = MediaType.APPLICATION_JSON_UTF8_VALUE)     
    public ResponseEntity<UserListResponse> findAll() {         
    	UserListResponse response = new UserListResponse(); 
    	try {
			response.setUsers(service.findAll());
	    	response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_OK, BaseResponse.DESC_OK));      	
		} catch (Exception e) {
			log.error("findAll.Error: {}", e);
	    	response.setResponseInfo(new ResponseInfo(BaseResponse.CODE_NOK, BaseResponse.DESC_NOK));  
		}
    	log.info("Response: {}", response);    			
    	return ResponseEntity.ok(response);     
    } 
	
 } 