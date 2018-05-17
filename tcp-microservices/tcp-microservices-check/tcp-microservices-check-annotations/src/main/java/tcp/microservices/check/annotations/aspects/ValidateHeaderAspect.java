package tcp.microservices.check.annotations.aspects;

import java.util.Arrays;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tcp.microservices.check.annotations.ValidateHeader;

/**
 * Aspecto que hace la logica para cada método anotado con
 * {@link tcp.microservices.check.annotations.ValidateHeader}.
 */
@Aspect
public class ValidateHeaderAspect {

	private static final Logger log = LoggerFactory.getLogger(ValidateHeaderAspect.class); 

    public ValidateHeaderAspect() { }

    /**
     * Aspecto que se ejecuta en la ejecución de cualquier método anotado con
     * {@link tcp.microservices.check.annotations.ValidateHeader}.
     * 
     * @param joinPoint
     *            Punto de unión del aspecto. 
     * @param validateHeader
     *            Información facilitada en el uso de la anotación.
     */
    @Before("execution(@tcp.microservices.check.annotations.ValidateHeader * *(..)) && "
    		+ "@annotation(validateHeader)")
//    @AfterReturning(pointcut="execution(@tcp.microservices.check.annotations.ValidateHeader * *(..)) && "
//    		+ "@annotation(validateHeader)")
    public void beforeCallingService(JoinPoint joinPoint, ValidateHeader validateHeader) {
    	log.debug("ValidateHeaderAspect.beforeCallingService: "+validateHeader.param1()+", "+validateHeader.param2());
    	log.debug("ValidateHeaderAspect.beforeCallingService: "+joinPoint.getThis());
    	// Parámetros que recibe el método //
    	Arrays.stream(joinPoint.getArgs()).forEach(arg -> log.debug("ValidateHeaderAspect. Arg: "+((Optional<String>)arg).get()));
    }

}
