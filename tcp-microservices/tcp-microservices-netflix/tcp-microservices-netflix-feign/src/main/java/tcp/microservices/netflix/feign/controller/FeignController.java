package tcp.microservices.netflix.feign.controller;  

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcp.microservices.netflix.feign.client.IFeignClient;  

@RestController 
@RequestMapping("/feign")
public class FeignController {  
    
	private static final Logger log = LoggerFactory.getLogger(FeignController.class); 
	
    @Autowired     
    private IFeignClient service;  
   
	@GetMapping(path = "/test")
	// Feign no se lleva bien con los parametros opcionales //
	// TODO: Ver solucion para parámetros opcionales
	public ResponseEntity<String> test(@RequestParam(name = "param") Optional<String> param) {
		log.debug("Entering Feign Service Test {}", param);
		String response;
		try {
			response = service.doSomething(param);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("KO Feign Service");
		} finally {
			log.debug("Leaving Feign Service Test {}", param);			
		}		
	}
	 
	@GetMapping(path = "/test2")
	public ResponseEntity<String> test2() {
		log.debug("Entering Feign Service Test2{}");
		String response;
		try {
			response = service.doSomething2();
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("KO Feign Service");
		} finally {
			log.debug("Leaving Feign Service Test2 {}");			
		}		
	}

} 