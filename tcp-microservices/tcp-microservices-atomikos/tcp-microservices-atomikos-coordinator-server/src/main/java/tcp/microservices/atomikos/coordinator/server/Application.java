package tcp.microservices.atomikos.coordinator.server;  

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import com.atomikos.icatch.tcc.rest.CoordinatorImp;
import com.atomikos.icatch.tcc.rest.TransactionProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@SpringBootApplication 
public class Application {  

	private static final Logger log = LoggerFactory.getLogger(Application.class); 

	@Autowired
    private Bus bus;
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
  
    @Bean
    public Server rsServer() {
    	log.info("************** Starting Atomikos Coordinator Server *******************");
    	List coordinatorProviders = new ArrayList();
    	coordinatorProviders.add(new JacksonJsonProvider());
    	coordinatorProviders.add(new TransactionProvider());    	
    	JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setProviders(coordinatorProviders);
        endpoint.setResourceClasses(CoordinatorImp.class);
        endpoint.setBus(bus);
    	endpoint.setAddress("/");
    	log.info("************** Started Atomikos Coordinator Server *******************");
    	return endpoint.create();
    }
} 
