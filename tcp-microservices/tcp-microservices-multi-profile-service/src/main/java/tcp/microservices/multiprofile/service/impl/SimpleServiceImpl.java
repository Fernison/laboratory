package tcp.microservices.multiprofile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tcp.microservices.multiprofile.service.ISimpleService;

@Service 
public class SimpleServiceImpl implements ISimpleService {  
    
	private static final Logger log = LoggerFactory.getLogger(SimpleServiceImpl.class); 

    @Value("${variable.env}")
    private String env;
    
    @Override     
    public String print() {
		final String result = "DATA: " + env;
    	log.info("[RESULT]:{}", result);         
        return result;  
    }
      
} 