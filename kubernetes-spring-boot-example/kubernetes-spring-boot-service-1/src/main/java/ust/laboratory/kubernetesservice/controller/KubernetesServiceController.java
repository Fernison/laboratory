package ust.laboratory.kubernetesservice.controller;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ust.laboratory.kubernetesservice.service.IService;

@RestController
@RequestMapping("/service1")
public class KubernetesServiceController {  
    
	private static final Logger log = LoggerFactory.getLogger(KubernetesServiceController.class); 

	@Autowired
	private IService service;
    
	@GetMapping(path = "/{name}")
	public ResponseEntity<String> echo(@PathVariable("name") String name) {
    	log.info("Request ESCENARIO 3 con configuracion en application.yml {}", name);  
    	log.info("Value SERVICE {}", System.getenv("SERVICE"));
    	log.info("Value SERVICE {}", System.getProperty("SERVICE"));
    	log.info("Value URL {}", System.getenv("URL"));
    	log.info("Value URL {}", System.getProperty("URL"));
		return ResponseEntity.ok(service.sayEcho(name));
	}	
	
 } 