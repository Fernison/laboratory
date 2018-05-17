package tcp.microservices.atomikos.service1.controller;  

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcp.microservices.atomikos.service1.service.IService1;  

@RestController 
@RequestMapping("/")
public class Service1Controller {  
    
	private static final Logger log = LoggerFactory.getLogger(Service1Controller.class); 
	
    @Autowired     
    private IService1 service;  
   
	@GetMapping(path = "/service1")
	public ResponseEntity<String> perform(@RequestParam(name = "param") Optional<String> param) {
		//log.debug("Entering perform {}", param);
		String response;
		try {
			response = service.doSomething(param);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("KO Service 1");
		} finally {
			//log.debug("Leaving perform {}", param);			
		}
		
	}
	
	@PutMapping(path = "/service1", consumes = { "application/tcc" })
	public void confirm(@RequestParam(name = "param") Optional<String> param) {
		log.debug("Entering Service 1 CONFIRM {}", param);
	}
	
	@DeleteMapping(path = "/service1", consumes = { "application/tcc" })
	public void cancel(@RequestParam(name = "param") Optional<String> param) {
		log.debug("Entering Service 1 CANCEL {}", param);
	}
    
 } 