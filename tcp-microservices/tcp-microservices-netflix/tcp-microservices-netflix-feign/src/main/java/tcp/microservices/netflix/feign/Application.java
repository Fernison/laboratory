package tcp.microservices.netflix.feign;  

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients; 

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication 
public class Application {  

	public static void main(String[] args) {         
		SpringApplication application = new SpringApplication(Application.class);         
		application.addListeners(new ApplicationPidFileWriter());         
		application.run(args);     
	}  
	
} 
