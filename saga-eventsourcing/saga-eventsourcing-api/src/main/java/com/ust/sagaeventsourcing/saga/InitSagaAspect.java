package com.ust.sagaeventsourcing.saga;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class InitSagaAspect {

	private static final Logger log = LoggerFactory.getLogger(InitSagaAspect.class); 

	@SuppressWarnings("rawtypes")
	@Autowired
	private Storage storage;
	
	public InitSagaAspect() { }

    @SuppressWarnings("unchecked")
	@AfterReturning(pointcut = "execution(@com.ust.sagaeventsourcing.saga.InitSaga * *(..)) && @annotation(initSaga)", returning="retVal")
    public void initSaga(JoinPoint joinPoint, InitSaga initSaga, SagaData retVal) throws Throwable {
    	log.debug("Processing InitSagaAspect. InitSaga: "+initSaga+". SagaData: "+retVal);
    	try {
    		retVal.setApplication(initSaga.application());
    		retVal.setSaga(initSaga.saga());
        	storage.store(retVal);
		} catch (Exception e) {			
			e.printStackTrace();
			log.error("InitSagaAspect. InitSaga: Error storing event: "+e.getMessage());
		}    
    }
    
    /*
    // Se tiene que poner InitSaga la primera con mayuscula a diferencia del caso anterior //
    @Around("@annotation(InitSaga)")
    public void logAroundCreateEmployee(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("****LoggingAspect.logAroundCreateEmployee() : " + joinPoint.getSignature().getName() + ": Before Method Execution");
        try {
            joinPoint.proceed(joinPoint.getArgs());
        } finally {
            //Do Something useful, If you have
        }
        System.out.println("****LoggingAspect.logAroundCreateEmployee() : " + joinPoint.getSignature().getName() + ": After Method Execution");
    }
    */

}
