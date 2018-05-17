package tcp.microservices.actuator.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import tcp.microservices.actuator.service.IConfigService;

@Service 
@RefreshScope
public class ConfigServiceImpl implements IConfigService {  
    
	private static final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class); 

    @Value("${config.key}")
    private String key;
    
	@Override     
	public void testRefresh(Optional<String> name) {
		log.info("[Value.Key]:{}", key);
	}
	
} 