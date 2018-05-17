package tcp.microservices.aws.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration 
@ComponentScan
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class SpringBootInvocation {
    private ApplicationContext applicationContext;

    public SpringBootInvocation() {
    }

    public void run() {
    	String[] args = new String[0];
        applicationContext = SpringApplication.run(SpringBootInvocation.class, args);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
