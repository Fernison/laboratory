package tcp.microservices.cor.aop.aspects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tcp.microservices.cor.model.LogInfoData;
import tcp.microservices.cor.model.LogInfoType;
import tcp.microservices.cor.util.LogAspectUtil;

@Aspect
public class CommonControllerAspect {

	private String args="";

	private static final Logger log = LoggerFactory.getLogger(CommonControllerAspect.class); 

	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) ||"
			+ "@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void requestMapping(){}
	
	@Before("requestMapping()")
	// Orden 2?
	protected void generateUID(JoinPoint jointPoint) throws Throwable{		
		log.debug("generateUID: @Before: {}", logJointPoint(jointPoint));
	}	

	/*
	@Before("getMapping()")
	// Orden 3?
	protected void getConfiguration(JoinPoint jointPoint) throws Throwable{		
		log.debug("getConfiguration: @Before: {}", logJointPoint(jointPoint));		
	}	
	
	@Before("getMapping()")
	// Orden 1?
	protected void authorization(JoinPoint jointPoint) throws Throwable{		
		log.debug("authorization: @Before: {}", logJointPoint(jointPoint));		
	}	

	/*
	@AfterReturning("getMapping()")
	protected void afterReturningGetMapping(JoinPoint jointPoint) throws Throwable{		
		log.debug("GetRequest: @AfterReturning: {}", logJointPoint(jointPoint));		
	}

	@AfterThrowing("getMapping()")
	protected void afterThrowingGetMapping(JoinPoint jointPoint) throws Throwable{		
		log.debug("GetRequest: @AfterThrowing: {}", logJointPoint(jointPoint));	
	}
	@After("getMapping()")
	protected void afterGetMapping(JoinPoint jointPoint) throws Throwable{		
		log.debug("GetRequest: @After: {}", logJointPoint(jointPoint));		
	}
	@Around("getMapping()")
	protected Object aroundGetMapping(ProceedingJoinPoint jointPoint) throws Throwable{		
		log.debug("GetRequest: Around: {}", logJointPoint(jointPoint));
		Object result = jointPoint.proceed();
		return result;
	}
	*/

	private String logJointPoint(JoinPoint jointPoint) {
		List<Object> list = Arrays.asList(jointPoint.getArgs());
		list.forEach(e -> args+=e.toString());
//				method-execution	 
		return jointPoint.getKind()+", "
//				0,									????	
				+"["+jointPoint.getArgs().length+", "+args+"]"
//				org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint
				+jointPoint.getClass().getName()+", "
//				print
				+jointPoint.getSignature().getName()+", "
//				org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@53fde15b
				+jointPoint.getSourceLocation()+", "
//				0
				+jointPoint.getStaticPart().getId()+", "
//				tcp.microservices.simple.controller.SimpleServiceController@217f7d72
				+jointPoint.getTarget().toString()+", "
//				execution(public java.lang.String tcp.microservices.cor.test.TestController.testGet(java.util.Optional))
				+jointPoint.toLongString()+", "
//				execution(TestController.testGet(..))
				+jointPoint.toShortString()
				;
	}
	
	protected Object logRequest(ProceedingJoinPoint jointPoint) throws Throwable{		
		LogInfoData logInfo = new LogInfoData(LogInfoType.REST, jointPoint.getKind(), jointPoint.getTarget().toString(), jointPoint.getSignature().getName());			
		logInfo.setInput(LogAspectUtil.getImputParameters((CodeSignature)jointPoint.getSignature(), jointPoint.getArgs()));				
		Object result = jointPoint.proceed();
		logInfo.setOutput(LogAspectUtil.getOutputParameter(jointPoint, result));		
		log.debug("requestTrace:{}", logInfo);
		return result;
	}
}