package tcp.microservices.check.annotations.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import tcp.microservices.check.interceptor.configuration.InterceptorAutoConfiguration;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TestController.class, secure = false)
@Import(InterceptorAutoConfiguration.class)
public class TestControllerTest {

	private static final Logger log = LoggerFactory.getLogger(TestControllerTest.class); 
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getRequest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/testGet?name=nombre&surname=apellido");//.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		log.debug("Respuesta de Controller: {}", result.getResponse().getContentAsString());
		assertThat(result.getResponse().getContentAsString()).isEqualTo("OK");
	}

	@Test
	public void postRequest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/testPost");//.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		log.debug("Respuesta de Controller: {}", result.getResponse().getContentAsString());
		assertThat(result.getResponse().getContentAsString()).isEqualTo("OK");
	}
}