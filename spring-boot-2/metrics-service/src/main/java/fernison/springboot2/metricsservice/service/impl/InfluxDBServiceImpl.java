package fernison.springboot2.metricsservice.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import fernison.springboot2.metricsservice.api.dto.RequestData;
import fernison.springboot2.metricsservice.exception.MetricsServiceException;
import fernison.springboot2.metricsservice.service.IInfluxDBService;
import fernison.springboot2.metricsservice.service.MetricData;

@Service
public class InfluxDBServiceImpl implements IInfluxDBService {
 
	private static final Logger LOG = LoggerFactory.getLogger(InfluxDBServiceImpl.class);
	
    private final RestTemplate restTemplate;

    @Value("${app.influxdb.url: http://localhost:8086}")
    private String url_db;
    
    @Value("${app.influxdb.database: mydb}")
    private String db;
    
    public InfluxDBServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
	@Async
	public CompletableFuture<String> writeMeasure(MetricData data) throws CompletionException {
		LOG.info("Writing measure: " + data);
		//String url = "http://localhost:8086/write?db="+db;
		String url = url_db+"/write?db="+db;
		ResponseEntity<String> response=null;
		try {
			StringBuffer metricContent=new StringBuffer();
			metricContent.append(data.getMeasurement());
			if(data.getTag()!=null && !data.getTag().equals("")) {
				metricContent.append(",");
				metricContent.append(data.getTag());
			}
			metricContent.append(" ");
			data.getFields().forEach( (key,value) -> {
				metricContent.append(key);
				metricContent.append("=");
				metricContent.append(""+value);
				metricContent.append(",");
			});
			// Borrar la última coma //
			metricContent.deleteCharAt(metricContent.length()-1);
			LOG.info("Content: "+metricContent.toString());
			response = restTemplate.postForEntity(new URI(url), metricContent.toString(), String.class);
		} catch (RestClientException e) {
			LOG.info("RestClientException");
			throw new CompletionException(new MetricsServiceException("Error: "+e.getMessage(), MetricsServiceException.INTERNAL_ERROR));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new CompletionException(new MetricsServiceException("Error: "+e.getMessage(), MetricsServiceException.INTERNAL_ERROR));
		}
		// Solo para pruebas //
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		///////////////////////
		LOG.info("RESULT: " + response);
		if(response.getStatusCode()==null || response.getStatusCode()!=HttpStatus.NO_CONTENT) {
			LOG.info("HTTP Code not 204");
			throw new CompletionException(new MetricsServiceException("Error: "+response.getStatusCode(), MetricsServiceException.INTERNAL_ERROR));
		}
        return CompletableFuture.completedFuture("OK");	
	}

}
