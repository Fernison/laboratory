package ust.laboratory.kubernetesservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import ust.laboratory.kubernetesservice.service.IFeignServiceClient;

@Component // Si no se pone esto, Spring no encuentra este bean //
public class FeignFallbackImpl implements IFeignServiceClient {  
    
	private static final Logger log = LoggerFactory.getLogger(FeignFallbackImpl.class); 

    @Override     
    public String sayRemoteHello(@PathVariable("name") String name) {
		final String result = "FallBack Feign Client";
        return result;  
    }
          
} 
