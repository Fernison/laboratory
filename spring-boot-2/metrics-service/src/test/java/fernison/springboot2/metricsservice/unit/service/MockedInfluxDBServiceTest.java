package fernison.springboot2.metricsservice.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import fernison.springboot2.metricsservice.service.IInfluxDBService;
import fernison.springboot2.metricsservice.service.MetricData;

/**
 * Mockeando el RestTemplate
 * @author fcabrero
 *
 */
@RunWith(SpringRunner.class)
@RestClientTest(IInfluxDBService.class)
public class MockedInfluxDBServiceTest {

	private static final String OK="OK";
	
    @Autowired
	private IInfluxDBService service;
	
    @Autowired
    private MockRestServiceServer server;
 
    @Before
    public void setUp() throws Exception {
        this.server.expect(requestTo("http://localhost:8086/write?db=My_Database"))
        	.andRespond(withStatus(HttpStatus.NO_CONTENT));
    }
    
	@Test
	public void whenMockServer_thenReturn204() {
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
}
