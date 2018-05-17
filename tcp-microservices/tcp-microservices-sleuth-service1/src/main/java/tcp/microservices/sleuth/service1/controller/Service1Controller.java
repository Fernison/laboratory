package tcp.microservices.sleuth.service1.controller;  

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tcp.microservices.sleuth.service1.service.ISleuthService1;  

@RestController 
@RequestMapping("/sleuth1")
public class Service1Controller {  
    
	private static final Logger log = LoggerFactory.getLogger(Service1Controller.class); 
	
    @Autowired     
    private ISleuthService1 service;  
   
    @Bean  
    @LoadBalanced
    public RestTemplate rest(RestTemplateBuilder builder) {         
    	return builder.build();     
    }
    
    @GetMapping()   
    public ResponseEntity<String> send(@RequestParam(name = "name") Optional<String> name) {         
    	final StringBuilder tracking = new StringBuilder();         
    	tracking.append("[NAME]:").append(name);         
    	MDC.put("TRACKING", tracking.toString());         
    	log.info("Request");  
    	return ResponseEntity.ok(service.send(name));     
    } 
    
 } 