package tcp.microservices.atomikos.coord.service.controller;  

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

import tcp.microservices.atomikos.coord.service.service.ICoordService;  

@RestController 
@RequestMapping("/")
public class CoordServiceController {  
    
	private static final Logger log = LoggerFactory.getLogger(CoordServiceController.class); 
	
    @Autowired     
    private ICoordService service;  
   
    @Bean  
    @LoadBalanced
    public RestTemplate rest(RestTemplateBuilder builder) {         
    	return builder.build();     
    }
    
	// No recibe ni devuelve JSON para hacerlo más simple //
    @GetMapping(path = "/coord")
	public ResponseEntity<String> coordinate(@RequestParam(name = "param") Optional<String> param) {
		//log.debug("Entering coordinate {}", param);
		String response;
		try {
			response = service.coordinate(param);
			return ResponseEntity.ok("OK Coord:::"+response);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.ok("KO");
		} finally {
			//log.debug("Leaving coordinate {}", param);			
		}
	}

    
 } 