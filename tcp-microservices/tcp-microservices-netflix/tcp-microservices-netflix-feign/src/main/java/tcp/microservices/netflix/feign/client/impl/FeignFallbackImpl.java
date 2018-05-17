package tcp.microservices.netflix.feign.client.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tcp.microservices.netflix.feign.client.IFeignClient;

public class FeignFallbackImpl implements IFeignClient {  
    
	private static final Logger log = LoggerFactory.getLogger(FeignFallbackImpl.class); 

    @Override     
    public String doSomething(Optional<String> param) throws Exception {
		final String result = "FallBack Feign Client";
        return result;  
    }
    
    @Override     
    public String doSomething2() throws Exception {
		final String result = "FallBack Feign Client2";
        return result;  
    }
      
} 