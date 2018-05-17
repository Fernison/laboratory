package tcp.playground.springasync.controller;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcp.playground.springasync.service.IConfigService; 

@RestController
@RequestMapping("/async")
public class AsyncController {  
    
	private static final Logger log = LoggerFactory.getLogger(AsyncController.class); 
	  
	@Autowired
	private IConfigService service;
	
	@GetMapping(path = "/")
	public String get() {
	  service.voidVeryLongMethod();
	  return "OK";
	}
	
 } 