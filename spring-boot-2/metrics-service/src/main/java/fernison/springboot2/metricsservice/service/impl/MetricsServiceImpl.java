package fernison.springboot2.metricsservice.service.impl;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import fernison.springboot2.metricsservice.annotations.Measurable;
import fernison.springboot2.metricsservice.service.IInfluxDBService;
import fernison.springboot2.metricsservice.service.IMetricsService;
import fernison.springboot2.metricsservice.service.MetricData;

@Service
public class MetricsServiceImpl implements IMetricsService {
 
	private static final Logger LOG = LoggerFactory.getLogger(MetricsServiceImpl.class);
		
	@Autowired
	private IInfluxDBService service;

	@Async
	public CompletableFuture<String> writeMeasure(Object args[], Measurable measurable) throws CompletionException {
		//LOG.info("Processing Metric. Args: "+args+". Measurable: "+measurable);
		LOG.info("Processing Metric. Args: {}. Measurable: {}", args, measurable);
		String[] fields=measurable.fields();
		ExpressionParser parser = new SpelExpressionParser();
		MetricData metricData=new MetricData(); 
		metricData.setMeasurement(measurable.measurement());
		metricData.setTag(measurable.tag());
		Arrays.asList(fields).forEach(field -> {
			//LOG.info("field: "+field);			
			Expression exp = parser.parseExpression(field);
			//LOG.info(""+exp.getValueType(joinPoint.getArgs()[0]));		
			if(exp.getValueType(args[0]).equals(String.class)) {
				String value = (String) exp.getValue(args[0]);				
				LOG.info("String Value: "+value);	
			} else if(exp.getValueType(args[0]).equals(Integer.class)) {
				Integer value = (Integer) exp.getValue(args[0]);				
				LOG.info("Integer Value: "+value);							
				metricData.getFields().put(field, value);
			} 
		});
		String[] operations=measurable.operations();
		LOG.info("Operation: "+operations.length);
		Arrays.asList(operations).forEach(operation -> {
			String[] parts=operation.split("=");
			Expression exp = parser.parseExpression(parts[1]);
			if(exp.getValueType(args[0]).equals(String.class)) {
				String value = (String) exp.getValue(args[0]);				
				LOG.info("String Value: "+value);	
				// TODO: Sí que envía cadenas. Hay que cambiarlo para que se pueda enviar este valor //
					
			} else if(exp.getValueType(args[0]).equals(Integer.class)) {
				Integer value = (Integer) exp.getValue(args[0]);				
				LOG.info("Integer Value: "+value);							
				metricData.getFields().put(parts[0], value);
			} 
		});
		return service.writeMeasure(metricData);
	}

}
