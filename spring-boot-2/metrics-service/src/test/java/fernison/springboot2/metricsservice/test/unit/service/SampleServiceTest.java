package fernison.springboot2.metricsservice.test.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import fernison.springboot2.metricsservice.api.dto.RequestData;
import fernison.springboot2.metricsservice.api.dto.ResponseData;
import fernison.springboot2.metricsservice.exception.MetricsServiceException;
import fernison.springboot2.metricsservice.service.ISampleService;

/**
 * Este test prueba la llamada a un servicio con una anotación.
 * Para que se ejecute correctamente y llame al aspecto hay que anotar el aspecto con @Component 
 * @author fcabrero
 *
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "fernison.springboot2.metricsservice.service",  "fernison.springboot2.metricsservice.annotations" })
class MySpringContextAOP {   

	// Si no se pone esto da el error "No qualifying bean of type 'org.springframework.boot.web.client.RestTemplateBuilder' available" //
	@Bean
    public RestTemplateBuilder getRestTemplateBuilder() {
        return new RestTemplateBuilder();
    }
	
}

@RunWith(SpringRunner.class)
@TestPropertySource(properties={"app.influxdb.database=My_Database"}) // Inyectamos una propiedad configurable
@ContextConfiguration(classes = MySpringContextAOP.class)
public class SampleServiceTest {
      
    @Autowired
	private ISampleService service;
	    
    @Test
	public void whenRequestData_thenInvokeSampleService() {
	    // given
		RequestData data=new RequestData("valor 1", 500, 800);
		// when
		ResponseData response=null;
		try {
			response = service.executeOperation(data);
		} catch (MetricsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // then
	    assertThat(response.getCodeMsg()).isEqualTo(1);
	}
	
}
