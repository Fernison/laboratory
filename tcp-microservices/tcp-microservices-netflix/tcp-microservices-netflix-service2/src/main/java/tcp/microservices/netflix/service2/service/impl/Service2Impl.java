package tcp.microservices.netflix.service2.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tcp.microservices.netflix.service2.service.IService2;

@Service 
public class Service2Impl implements IService2 {  
    
	private static final Logger log = LoggerFactory.getLogger(Service2Impl.class); 

    @Override     
    public String doSomething(Optional<String> param) throws Exception {
		final String result = "SERVICE2: OK " + (param!=null ? ((param.isPresent() ? param.get() : "VOID")) : " NO PARAMS");
    	//log.info("[RESULT]:{}", result);         
        return result;  
    }
      
} 