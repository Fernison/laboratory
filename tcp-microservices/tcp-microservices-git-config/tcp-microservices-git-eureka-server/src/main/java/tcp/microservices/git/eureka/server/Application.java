package tcp.microservices.git.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Application {  

	public static void main(String[] args) {         
		SpringApplication application = new SpringApplication(Application.class);         
		application.addListeners(new ApplicationPidFileWriter());         
		application.run(args);     
	}  
	
}
