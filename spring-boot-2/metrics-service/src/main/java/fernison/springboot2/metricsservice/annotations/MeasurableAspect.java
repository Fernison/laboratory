package fernison.springboot2.metricsservice.annotations;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import fernison.springboot2.metricsservice.service.IMetricsService;

@Aspect
//@Component // Si no se pone esta anotacion no se descubre con un @ComponentScan, necesario para los tests //
			 // PROBLEMA. Si se pone, se registra por duplicado y se llama al aspecto dos veces //
public class MeasurableAspect {

	private static final Logger LOG = LoggerFactory.getLogger(MeasurableAspect.class); 

	@Autowired
	private IMetricsService service;
	
	public MeasurableAspect() { }

    @AfterReturning(pointcut = "execution(@fernison.springboot2.metricsservice.annotations.Measurable * *(..)) && @annotation(measurable)")
	public void metricEvent(JoinPoint joinPoint, Measurable measurable) {
		LOG.info("Processing MeasurableAspect. Measurable: "+measurable);		
		// Si NO queremos esperar a saber la respuesta //
		// No captura la excepción aunque se produzca. No sabemos en este punto si ha habido algún error llamando al servicio //
//		try {
//			service.writeMeasure(joinPoint.getArgs(), measurable);
//		} catch (Exception e) {
//			LOG.error(e.getMessage());
//		}
		// Si queremos esperar a saber la respuesta //
		try {
			CompletableFuture<String> result=service.writeMeasure(joinPoint.getArgs(), measurable);
			LOG.info("Write metric result: "+result.join());
		} catch (CompletionException e) {
			LOG.error("Error: "+e.getCause());
		}		
	}
	
}
