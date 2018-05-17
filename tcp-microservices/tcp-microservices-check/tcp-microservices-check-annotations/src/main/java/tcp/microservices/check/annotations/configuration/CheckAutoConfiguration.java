package tcp.microservices.check.annotations.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tcp.microservices.check.annotations.aspects.ValidateHeaderAspect;

@Configuration
@EnableAspectJAutoProxy
public class CheckAutoConfiguration {
	 
	private static final Logger log = LoggerFactory.getLogger(CheckAutoConfiguration.class);
	 
//	@Bean
//	public CommonControllerAspect createCommonControllerAspect(){
//		log.info("Starting CommonControllerAspect Aspect ...");
//		return new CommonControllerAspect();
//	}

	@Bean
	public ValidateHeaderAspect createValidateHeaderAspect() {
		log.info("Starting ValidateHeaderAspect Aspect ...");
		return new ValidateHeaderAspect();
	}
}
