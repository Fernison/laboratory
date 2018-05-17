package tcp.microservices.atomikos.service1.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tcp.microservices.atomikos.service1.service.IService1;

@Service 
public class Service1Impl implements IService1 {  
    
	private static final Logger log = LoggerFactory.getLogger(Service1Impl.class); 

    @Override     
    public String doSomething(Optional<String> param) throws Exception {
		final String result = "SERVICE1: OK " + param.get();
    	//log.info("[RESULT]:{}", result);         
        return result;  
    }
      
} 