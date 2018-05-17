package tcp.microservices.netflix.service1.controller;  

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tcp.microservices.netflix.service1.service.IService1;  

@RestController 
@RequestMapping("/")
public class Service1Controller {  
    
	private static final Logger log = LoggerFactory.getLogger(Service1Controller.class); 
	
    @Autowired     
    private IService1 service;  

    @Bean  
    @LoadBalanced
    public RestTemplate rest(RestTemplateBuilder builder) {         
    	return builder.build();     
    }
    
	@GetMapping(path = "/test")
	public ResponseEntity<String> test(@RequestParam(name = "param") Optional<String> param) {
		log.debug("Entering Service 1 Test {}", param);
		String response;
		try {
			response = service.doSomething(param);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			//e.printStackTrace();
			return ResponseEntity.ok("KO Service 1");
		} finally {
			log.debug("Leaving Service 1 Test {}", param);			
		}
		
	}
	    
 } 