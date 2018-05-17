package tcp.microservices.netflix.service2.controller;  

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcp.microservices.netflix.service2.service.IService2;  

@RestController 
@RequestMapping("/")
public class Service2Controller {  
    
	private static final Logger log = LoggerFactory.getLogger(Service2Controller.class); 
	
    @Autowired     
    private IService2 service;  
   
	@GetMapping(path = "/test")
	public ResponseEntity<String> test(@RequestParam(name = "param") Optional<String> param) {
		log.debug("Entering Service 2 Test {}", param);
		String response;
		try {
			response = service.doSomething(param);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("KO Service 2");
		} finally {
			log.debug("Leaving Service 2 Test {}", param);			
		}		
	}
	 	
	@GetMapping(path = "/test2")
	public ResponseEntity<String> test2() {
		log.debug("Entering Service 2 Test2 {}");
		String response;
		try {
			response = service.doSomething(null);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("KO Service 2");
		} finally {
			log.debug("Leaving Service 2 Test2 {}");			
		}		
	}
	
	/*
	@PostMapping(path = "/test")
	public ResponseEntity<String> test2(@RequestParam(name = "param") Optional<String> param) {
		log.debug("Entering Service 2 Post Test2 {}", param);
		String response;
		try {
			response = service.doSomething(param);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("KO Service 2 Post");
		} finally {
			log.debug("Leaving Service 2 Post Test2 {}", param);			
		}		
	}
	*/
	
} 