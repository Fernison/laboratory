package com.ust.sagaeventsourcing.event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ust.sagaeventsourcing.saga.SagaData;

@Aspect
public class EventSubscriberAspect {

	private static final Logger log = LoggerFactory.getLogger(EventSubscriberAspect.class); 

	public EventSubscriberAspect() { }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}
    
    // A este aspecto no le encuentro sentido //
	@AfterReturning(pointcut="publicMethod() && @within(eventSubscriber)", returning="retVal")
	public void eventSubscriber(JoinPoint joinPoint, EventSubscriber eventSubscriber, SagaData retVal) throws Throwable {
		log.debug("Processing EventSubscriberAspect. EventSubscriber: "+eventSubscriber+". SagaData: "+retVal);
		retVal.setApplication(eventSubscriber.application());
		retVal.setSaga(eventSubscriber.saga());
	}
	
	
	/*
	@Around("within(@EventSubscriber *)")
	public Object LogExecutionTimeByMethod(ProceedingJoinPoint joinPoint) throws Throwable {
	 	System.out.println("********************* LogExecutionTimeByMethod");
	 	return joinPoint.proceed();
	}	
	*/
	
	/*
	@Pointcut("within(@EventSubscriber *)")
	public void beanAnnotatedWithMonitor() {}
	
	@AfterReturning(pointcut="beanAnnotatedWithMonitor()", returning="retVal")
	public void eventSubscriber(JoinPoint joinPoint, Eventide<?> retVal) {
		System.out.println("****aroundSomeAnnotationMethods.retVal() : " + retVal); 
	}
	*/
			
	/*
	@Pointcut("within(@EventSubscriber *)")
	public void beanAnnotatedWithMonitor() {}

	@AfterReturning("beanAnnotatedWithMonitor()")
    public void eventSubscriber(JoinPoint joinPoint) {
	    System.out.println("****aroundSomeAnnotationMethods.aroundSomeAnnotationMethods() : " + joinPoint.getSignature().getName() + ": Before Method Execution"); 
    }
	*/	
		
}
