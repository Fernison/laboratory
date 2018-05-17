package tcp.microservices.config.server.repositories;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;


//@ConfigurationProperties("spring.cloud.config.server.custom")
//@Profile("custom")
//@Configuration
public class CustomEnvironmentRepository implements EnvironmentRepository, Ordered {
    
	private static final Logger log = LoggerFactory.getLogger(CustomEnvironmentRepository.class); 

	private int order = Ordered.LOWEST_PRECEDENCE;
	
//	private int counter=0;
	
//	@Autowired
//	private Environment environment;
	
	@Override
    public Environment findOne(String application, String profile, String label) {
        Environment environment = new Environment(application, profile);
//        environment.setName(application);
//        String profiles[]=new String[1];
//        profiles[0]=profile;
//        environment.setProfiles(profiles);        
//        log.info("************************************ findOne: counter: "+counter);
        log.info("************************************ findOne: application: "+application);
        log.info("************************************ findOne: profile: "+profile);
        log.info("************************************ findOne: label:"+label);
        final Map<String, String> properties =loadYouPropertiesTotal();
//        if(counter==0) {
//        	properties = loadYouPropertiesTotal();
//            counter++;
//        } else {
//        	properties = loadYouPropertiesParcial();        	
//        }
        environment.add(new PropertySource("mapPropertySource", properties));
//        environment.addFirst(new PropertySource("mapPropertySource", properties));
        return environment;
    }
	
	@Override
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	private Map<String, String> loadYouPropertiesTotal() {
		Map<String, String> properties=new HashMap<String, String>();
		properties.put("prefix1.key1", "value1_total");
		properties.put("prefix2.key1", "value2_total");
		properties.put("prefix3.key1", "value3_total");
		return properties;
	}
	
	private Map<String, String> loadYouPropertiesParcial() {
		Map<String, String> properties=new HashMap<String, String>();
		properties.put("prefix2.key1", "value2_parcial");
		return properties;
	}
	
//	@Bean
//	public Environment getEnvironment() {
//		return new Environment("h","k","l");
//	}
	
}