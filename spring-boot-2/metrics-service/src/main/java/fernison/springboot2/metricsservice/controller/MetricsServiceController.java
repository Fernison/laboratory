package fernison.springboot2.metricsservice.controller;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fernison.springboot2.metricsservice.annotations.Measurable;
import fernison.springboot2.metricsservice.api.dto.RequestData;
import fernison.springboot2.metricsservice.api.dto.ResponseData;
import fernison.springboot2.metricsservice.exception.MetricsServiceException;
import fernison.springboot2.metricsservice.service.ISampleService;

@RestController
@RequestMapping("/metrics")
public class MetricsServiceController {  
    
	private static final Logger LOG = LoggerFactory.getLogger(MetricsServiceController.class); 

	@Autowired
	private ISampleService service;
    	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseData> requestExecution() {
    	LOG.info("GET. TEST");  
		return ResponseEntity.ok(new ResponseData("OK", ResponseData.OK));
	}
	
	@Measurable(measurement="mymeas", tag="mytag=1", fields={"value2", "value3"})
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)     
    public ResponseEntity<ResponseData> requestExecution(@RequestBody final RequestData data) {         
		LOG.debug("Request {}", data);  
    	try {
			return ResponseEntity.ok(service.executeOperation(data));
		} catch (MetricsServiceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData("KOK", ResponseData.KO));
		}   
    } 

 } 