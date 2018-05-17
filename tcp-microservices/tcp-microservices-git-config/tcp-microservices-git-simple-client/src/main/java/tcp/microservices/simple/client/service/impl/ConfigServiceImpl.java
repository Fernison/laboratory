package tcp.microservices.simple.client.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import tcp.microservices.simple.client.service.IConfigService;

@Service
@RefreshScope
public class ConfigServiceImpl implements IConfigService {  
    
	private static final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class); 
	
    @Value("${prefix1.key1: defecto}")
    private String key1;
    
    @Autowired
    private Environment environment;
    
	@Override     
	public void testRefresh(Optional<String> name) {
		log.info("environment Simple dinamico: "+environment.getProperty(name.get()+".key1"));
		log.info("environment Simple estatico: "+environment.getProperty("prefix1.key1"));
		log.info("Value:{}", key1);
	}
	
} 