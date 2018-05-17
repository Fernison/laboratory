package tcp.microservices.simple.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tcp.microservices.simple.api.dto.SimpleDto;
import tcp.microservices.simple.service.ISimpleService;

@Service 
public class SimpleServiceImpl implements ISimpleService {  
    
	private static final Logger log = LoggerFactory.getLogger(SimpleServiceImpl.class); 

    @Override     
    public String print(SimpleDto simpleDto) throws Exception {
		final String result = "DATA: " + simpleDto;
    	log.info("[RESULT]:{}", result);         
        return result;  
    }
      
} 