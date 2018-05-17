package tcp.microservices.actuator.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ActuatorAspect {

    private CounterService counterService;
    private GaugeService gaugeService; 
    
    @Autowired
    public ActuatorAspect(CounterService counterService,
    		GaugeService gaugeService) {
        this.counterService = counterService;
        this.gaugeService=gaugeService;
    }
    
	@AfterReturning("execution(* tcp.microservices.actuator.service.impl.ServiceActuatorServiceImpl.doOtherthing(..))")
	public void doActuator(JoinPoint joinPoint) {
		// Actuator //
		counterService.increment("counter.doOtherthing");
		gaugeService.submit("gauge.doOtherthing", 100);
	}

}