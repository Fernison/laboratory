package tcp.microservices.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

	private static final Logger log = LoggerFactory.getLogger(ServiceAspect.class); 

//	@AfterReturning("execution(* tcp.microservices.aop.service.impl.ServiceAopServiceImpl.doSomething(..))")
	@AfterReturning("execution(* tcp.microservices.aop.service.impl.ServiceAopServiceImpl.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		log.info("Completed: " + joinPoint);
	}

}