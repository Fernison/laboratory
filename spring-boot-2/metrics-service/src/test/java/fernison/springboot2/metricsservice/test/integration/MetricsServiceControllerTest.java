package fernison.springboot2.metricsservice.test.integration;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import fernison.springboot2.metricsservice.api.dto.RequestData;
import fernison.springboot2.metricsservice.api.dto.ResponseData;
import fernison.springboot2.metricsservice.controller.MetricsServiceController;
import fernison.springboot2.metricsservice.service.IInfluxDBService;
import fernison.springboot2.metricsservice.service.ISampleService;
import fernison.springboot2.metricsservice.service.MetricData;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MetricsServiceController.class, secure = false)
@WebAppConfiguration
public class MetricsServiceControllerTest {

	private static final Logger LOG = LoggerFactory.getLogger(MetricsServiceControllerTest.class); 
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ISampleService service; 
	@MockBean
	private IInfluxDBService influxDBService; 
	
    @Before
    public void init(){
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();       	
    }
    
	@Test
	public void whenMetricsService_thenReturnOKCodeMsg() throws Exception {
		// given
		//RequestData data=new RequestData("valor 1", 500, 800);
		Mockito.when(service.executeOperation(Mockito.any(RequestData.class)/*metricData*//*data*/))
				.thenReturn(new ResponseData("OK",1));
		
		HashMap<String, Integer> fields=new HashMap<String, Integer>(); 
		fields.put("value2", 500);
		fields.put("value3", 800);
		MetricData metricData=new MetricData();
		metricData.setFields(fields);
		metricData.setMeasurement("mymeas");
		metricData.setTag("mytag=1");
		Mockito.when(influxDBService.writeMeasure(Mockito.any(MetricData.class)/*metricData*/)).thenReturn(CompletableFuture.completedFuture("OK"));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/metrics").contentType("application/json").content("{\r\n" + 
						"	\"value1\": \"valor 1\",\r\n" + 
						"	\"value2\": 500,\r\n" + 
						"	\"value3\": 800\r\n" + 
						"}");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();		
		LOG.debug("Response: "+result.getResponse().getContentAsString());
		// when
		ResponseData resData=new ObjectMapper().readValue(result.getResponse().getContentAsString(), ResponseData.class);
		LOG.debug("Respuesta de Controller: "+ resData);
		// then
		assertThat(resData.getCodeMsg()).isEqualTo(1);
	}
		
}