package tcp.microservices.cor.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import tcp.microservices.cor.annotations.CapturableAspect;
import tcp.microservices.cor.aop.aspects.CommonControllerAspect;

@Configuration
@EnableAspectJAutoProxy
//@ConditionalOnProperty(name = "bankia.log.auditing.enable", havingValue = "true", matchIfMissing = false)
public class CorAutoConfiguration {
	 
	private static final Logger log = LoggerFactory.getLogger(CorAutoConfiguration.class);
	
	@Bean
	public CommonControllerAspect createCommonControllerAspect(){
		log.info("Starting CommonControllerAspect Aspect ...");
		return new CommonControllerAspect();
	}

	@Bean
	public CapturableAspect createCapturableAspect(){
		log.info("Starting CapturableAspect Aspect ...");
		return new CapturableAspect();
	}
}
