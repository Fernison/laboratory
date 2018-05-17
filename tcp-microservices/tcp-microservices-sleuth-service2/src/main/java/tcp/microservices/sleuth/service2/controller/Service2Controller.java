package tcp.microservices.sleuth.service2.controller;  

import java.util.Optional;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.slf4j.MDC; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController;

import tcp.microservices.sleuth.service2.service.ISleuthService2; 

@RestController
@RequestMapping("/sleuth2")
public class Service2Controller {  

    private static final Logger log = LoggerFactory.getLogger(Service2Controller.class);  
    @Autowired     
    private ISleuthService2 service;  

    @GetMapping()
    public ResponseEntity<String> send(@RequestParam(name = "name") Optional<String> name) {         
    	final StringBuilder tracking = new StringBuilder();         
    	tracking.append("[NAME]:").append(name);         
    	MDC.put("TRACKING", tracking.toString());         
    	log.info("Request");  
    	return ResponseEntity.ok(service.print(name));     
    } 
  
} 