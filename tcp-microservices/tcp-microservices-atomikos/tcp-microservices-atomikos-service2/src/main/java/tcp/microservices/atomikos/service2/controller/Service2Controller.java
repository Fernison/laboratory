package tcp.microservices.atomikos.service2.controller;  

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

import tcp.microservices.atomikos.service2.service.IService2;  

@RestController 
@RequestMapping("/")
public class Service2Controller {  
    
	private static final Logger log = LoggerFactory.getLogger(Service2Controller.class); 
	
    @Autowired     
    private IService2 service;  
   
	@GetMapping(path = "/service2")
	public ResponseEntity<String> perform(@RequestParam(name = "param") Optional<String> param) {
		//log.debug("Entering perform {}", param);
		String response;
		try {
			response = service.doSomething(param);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("KO Service 2");
		} finally {
			//log.debug("Leaving perform {}", param);			
		}		
	}
	
	@PutMapping(path = "/service2", consumes = { "application/tcc" })
	public void confirm(@RequestParam(name = "param") Optional<String> param) {
		log.debug("Entering Service 2 CONFIRM {}", param);
	}

	@DeleteMapping(path = "/service2", consumes = { "application/tcc" })
	public void cancel(@RequestParam(name = "param") Optional<String> param) {
		log.debug("Entering Service 2 CANCEL {}", param);
	}
    
 } 