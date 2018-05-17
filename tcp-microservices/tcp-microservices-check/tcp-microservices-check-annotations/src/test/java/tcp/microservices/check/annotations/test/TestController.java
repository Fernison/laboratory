package tcp.microservices.check.annotations.test;  

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcp.microservices.check.annotations.ValidateHeader;  

@RestController 
@RequestMapping("/")
public class TestController {  
    
	private static final Logger log = LoggerFactory.getLogger(TestController.class); 
	
	@GetMapping(path = "/testGet")
	@ValidateHeader(param1="parametro 1", param2="parametro 2")
	public String testGet(@RequestParam(name = "name") Optional<String> name,
			@RequestParam(name = "surname") Optional<String> surname) {  
		log.debug("Entering testGet() {},{}", name,surname);
		log.debug("Leaving testGet()");
		return "OK";
	}
    
	@PostMapping(path = "/testPost")
	@ValidateHeader(param1="parametro 1", param2="parametro 2")
	public String testPost(@RequestParam(name = "name") Optional<String> name) {  
		log.debug("Entering testPost() {}", name);
		log.debug("Leaving testPost()");
		return "OK";
	}
 } 