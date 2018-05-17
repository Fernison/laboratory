package tcp.microservices.aws.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebugSpringConfiguration implements BeanPostProcessor {
		
  Logger log = LoggerFactory.getLogger(DebugSpringConfiguration.class);
 
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    //log.info("postProcessBeforeInitialization: bean("+beanName+")");
    return bean;
  }
  
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    //log.info("postProcessAfterInitialization: bean("+beanName+")");
    return bean;
  }
}