package tcp.microservices.config.client.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import tcp.microservices.config.client.service.IConfigService;

@Service 
@RefreshScope
public class ConfigServiceImpl implements IConfigService {  
    
	private static final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class); 

    @Value("${prefix1.key1}")
    private String key;

    @Autowired
    private Environment environment;

    @Override     
	public void testRefresh(Optional<String> name) {
    	log.info("environment: "+environment.getProperty(name.get()+".key1"));
    	log.info("[prefix1.key1]:{}", key);
	}

} 