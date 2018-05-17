package tcp.microservices.netflix.service1.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import tcp.microservices.netflix.service1.service.IService1;

@Service 
public class Service1Impl implements IService1 {  
    
	private static final Logger log = LoggerFactory.getLogger(Service1Impl.class); 

	@Autowired     
    private RestTemplate restTemplate;    
	    
    @Value("${app.service2.url}")     
    private String serviceUrl; 
    
    @Override     
    @HystrixCommand(fallbackMethod = "getSendReliable")     
    public String doSomething(Optional<String> param) throws Exception {
    	String url = serviceUrl;         
    	if (param.isPresent()) {             
    		url = UriComponentsBuilder.fromUriString(serviceUrl).
    				queryParam("param", param.get()).build().encode().toString();         
    	}  
    	Map<String, String> params = new HashMap<>();         
    	if (param.isPresent()) params.put("name", param.get()); 
    	HttpHeaders headers = new HttpHeaders();         
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));         
    	headers.setContentType(MediaType.APPLICATION_JSON_UTF8);         
    	HttpEntity<?> httpEntity = new HttpEntity<>(headers);  
        
    	final ResponseEntity<String> result = 
        		restTemplate.exchange(url, HttpMethod.GET,
        				httpEntity, String.class, params);  
        log.info("[RESULT]:{}", result);         
        return "SERVICE1: OK "+result.getBody(); 
    }
     
    @SuppressWarnings("unused")
	private String getSendReliable(final Optional<String> name, Throwable e) {
    	final String result = "Error" + (name.map(n -> " " + n).orElse("")+
    			" (" + e.getClass() + "): " + e.getMessage()); 
    	log.info("[RESULT]:{}", result);         
        return result;          
    }

} 