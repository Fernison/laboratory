package com.ust.sagaeventsourcing.ms.initsrv.test.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ust.sagaeventsourcing.conf.EventAutoConfiguration;
import com.ust.sagaeventsourcing.conf.SagaAutoConfiguration;
import com.ust.sagaeventsourcing.ms.common.api.response.BaseResponse;
import com.ust.sagaeventsourcing.ms.initsrv.conf.KafkaConfiguration;
import com.ust.sagaeventsourcing.ms.initsrv.conf.MySagaConfiguration;
import com.ust.sagaeventsourcing.ms.initsrv.controller.SimpleServiceController;
import com.ust.sagaeventsourcing.ms.initsrv.test.configuration.PersistenceJPAConfiguration;
import com.ust.sagaeventsourcing.ms.initsrv.test.models.kafkamsg;
import com.ust.sagaeventsourcing.ms.initsrv.test.repositories.KafkamsgRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebAppConfiguration
//@SpringBootTest(classes = Application.class) // Si no es un test de Controller se usa esto que sí carga bien la cnf de JPA desde .yml
@WebMvcTest(value = SimpleServiceController.class, secure = false) // Si es un test de controller hay que usar esto y el fichero de conf
					// PersistenceJPAConfiguration para que cargue la configuración
@Import({KafkaConfiguration.class, MySagaConfiguration.class, EventAutoConfiguration.class, 
	PersistenceJPAConfiguration.class, SagaAutoConfiguration.class})
@ComponentScan(basePackages = "com.ust.sagaeventsourcing.ms.initsrv") // Es necesario para que encuentre el controller
public class SagaInitServiceControllerTest {

	private static final Logger log = LoggerFactory.getLogger(SagaInitServiceControllerTest.class); 
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private KafkamsgRepository repository;
	
    @Before
    public void deleteDatabase(){
    	repository.delete();
    } 
    
    private String URL="/simple/test";
    
    private String request="{\r\n" + 
    		"	\"requestInfo\": {\r\n" + 
    		"    	\"channel\": \"canal\",\r\n" + 
    		"    	\"user\": \"usuario\",\r\n" + 
    		"    	\"timestamp\": 1523286345753,\r\n" + 
    		"    	\"application\": \"aplicacion\",\r\n" + 
    		"    	\"operationId\": \"ID operacion\"\r\n" + 
    		"	},\r\n" + 
    		"	\"data\": {\r\n" + 
    		"		\"id\": 123456789,\r\n" + 
    		"		\"data1\": \"data 111111111111111111\",\r\n" + 
    		"		\"data2\": \"data 22222222222222222222\"\r\n" + 
    		"	}\r\n" + 
    		"}";
    
	// Para este test nohace falta ejecutar el resto de MSs //
    @Test
	public void shouldStoreOneEvent() throws Exception { 
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL)
				.contentType("application/json").content(request);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		BaseResponse response=new ObjectMapper().readValue(result.getResponse().getContentAsString(), BaseResponse.class);		
		assertThat(response.getResponseInfo().getDescription()).isEqualTo("Operation successfully");
		Thread.sleep(10000);
		List<kafkamsg> msgList=repository.findAll();
		assertThat(msgList.size()).isEqualTo(1);
	}
    
	// Para este test sí hace falta ejecutar el resto de MSs //
    @Test
	public void shouldStoreFullSagaSequence() throws Exception { 
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL)
				.contentType("application/json").content(request);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		BaseResponse response=new ObjectMapper().readValue(result.getResponse().getContentAsString(), BaseResponse.class);		
		assertThat(response.getResponseInfo().getDescription()).isEqualTo("Operation successfully");
		Thread.sleep(10000);
		List<kafkamsg> msgList=repository.findAll();
		assertThat(msgList.size()).isEqualTo(4);
	}
    
	// Para este test sí hace falta ejecutar el resto de MSs //
    @Test
	public void shouldStorMassiveFullSagaSequence() throws Exception {
		int times=500;
    	ExecutorService threadPool=Executors.newFixedThreadPool(10);
		for(int i=0; i<times; i++) {		    
			threadPool.submit(() -> {
				try {
					RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL)
							.contentType("application/json").content(request);
					MvcResult result = mockMvc.perform(requestBuilder).andReturn();
					BaseResponse response=new ObjectMapper().readValue(result.getResponse().getContentAsString(), BaseResponse.class);		
					assertThat(response.getResponseInfo().getDescription()).isEqualTo("Operation successfully");
				} catch (Exception e) { }
		    });
		}    	    	
		Thread.sleep(60000);
		List<kafkamsg> msgList=repository.findAll();
		assertThat(msgList.size()).isEqualTo(times*4);
		assertThat(repository.getStatusCount("ALTA OK")).isEqualTo(times);
		assertThat(repository.getStatusCount("UPDATE OK")).isEqualTo(times);
		assertThat(repository.getStatusCount("UPDATE2 OK")).isEqualTo(times);
		assertThat(repository.getStatusCount("COMMIT OK")).isEqualTo(times);
	}
		
		
}