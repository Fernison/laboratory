package fernison.springboot2.metricsservice.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import fernison.springboot2.metricsservice.service.IInfluxDBService;
import fernison.springboot2.metricsservice.service.MetricData;
import fernison.springboot2.metricsservice.service.impl.InfluxDBServiceImpl;

@RunWith(SpringRunner.class)
@TestPropertySource(properties={"app.influxdb.database=My_Database"}) // Inyectamos una propiedad configurable
public class InfluxDBServiceTest {

	private static final String OK="OK";
	
    @TestConfiguration
    static class InfluxDBServiceImplTestContextConfiguration {        
    	@Bean
        public IInfluxDBService influxDBService() {
            return new InfluxDBServiceImpl(new RestTemplateBuilder());
        }
    }
    
    @Autowired
	private IInfluxDBService service;
	    
	@Test
	public void whenMetric_thenSendToInfluxSync() {
	    // given
		HashMap<String, Integer> fields=new HashMap<String, Integer>(); 
		fields.put("value2", 500);
		fields.put("value3", 800);
		MetricData data=new MetricData();
	    data.setFields(fields);
	    data.setMeasurement("mymeas");
	    data.setTag("mytag=1");
	    // when
	    CompletableFuture<String> result=service.writeMeasure(data);
	    // then
	    assertThat(result.join()).isEqualTo(OK);
	}
	
	@Test
	public void whenMetric_thenSendToInfluxAsync() {
	    // given
		HashMap<String, Integer> fields=new HashMap<String, Integer>(); 
		fields.put("value2", 500);
		fields.put("value3", 800);
		MetricData data=new MetricData();
	    data.setFields(fields);
	    data.setMeasurement("mymeas");
	    data.setTag("mytag=1");
	    // when
	    service.writeMeasure(data);
	    // then
	    
	}
	
	@Test(expected = CompletionException.class)
	public void whenNoMeasurementMetric_thenThrowsException() {
	    // given
		HashMap<String, Integer> fields=new HashMap<String, Integer>(); 
		fields.put("value2", 500);
		fields.put("value3", 800);
		MetricData data=new MetricData();
	    data.setFields(fields);
	    data.setMeasurement("");
	    data.setTag("mytag=1");
	    // when
	    service.writeMeasure(data);
	    // then
	    // Capturado con @Test(expected = CompletionException.class)
	}
	
}
