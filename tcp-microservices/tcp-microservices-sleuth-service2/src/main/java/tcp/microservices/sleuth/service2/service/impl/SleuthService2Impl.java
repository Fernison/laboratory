package tcp.microservices.sleuth.service2.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tcp.microservices.sleuth.service2.service.ISleuthService2;

@Service 
public class SleuthService2Impl implements ISleuthService2 {  
    
	private static final Logger log = 
			 LoggerFactory.getLogger(SleuthService2Impl.class); 
	 
    @Override     
    public String print(Optional<String> name) {         
		final String result = "Hello " + name.map(n -> n).orElse("Nadie");
    	log.info("[RESULT]:{}", result);         
        return result;   
    } 
      
} 