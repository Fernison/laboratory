package tcp.microservices.cor.annotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aspecto que hace la logica para cada método anotado con
 * {@link tcp.microservices.cor.annotations.Capturable}.
 *
 * @author <a href="mailto:francisco.ahijado@ust-global.com">franciscojama</a>
 */
@Aspect
public class CapturableAspect {

	private static final Logger log = LoggerFactory.getLogger(CapturableAspect.class); 

    public CapturableAspect() { }

    /**
     * Aspecto que se ejecuta en la ejecución de cualquier método anotado con
     * {@link tcp.microservices.cor.annotations.Capturable}.
     *
     * @param joinPoint
     *            Punto de unión del aspecto.
     * @param capturable
     *            Información facilitada en el uso de la anotación.
     */
    @AfterReturning(pointcut = "execution(@tcp.microservices.cor.annotations.Capturable * *(..)) && @annotation(capturable)")
    public void afterCallingService(JoinPoint joinPoint, Capturable capturable) {
    	log.debug("CapturableAspect.Capturable: "+capturable.param1()+", "+capturable.param2());
    }

}
