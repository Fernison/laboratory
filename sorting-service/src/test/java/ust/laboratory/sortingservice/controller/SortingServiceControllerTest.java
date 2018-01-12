package ust.laboratory.sortingservice.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ust.laboratory.sortingservice.api.dto.EnumStatus;
import ust.laboratory.sortingservice.api.dto.Execution;
import ust.laboratory.sortingservice.controller.SortingServiceController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SortingServiceController.class, secure = false)
@WebAppConfiguration
@ComponentScan(basePackages = "ust.laboratory.sortingservice") 
public class SortingServiceControllerTest {

	private static final Logger log = LoggerFactory.getLogger(SortingServiceControllerTest.class); 
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    
	@Autowired
	private MockMvc mockMvc;

    @Before
    public void init(){
       mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    } 
    
//	@MockBean
//	private StudentService studentService;
// 
//	Course mockCourse = new Course("Course1", "Spring", "10 Steps",
//			Arrays.asList("Learn Maven", "Import Project", "First Example",
//					"Second Example"));
//	String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

	@Test
	public void shouldReturnPendingExecution() throws Exception {
 
//		Mockito.when(
//				studentService.retrieveCourse(Mockito.anyString(),
//						Mockito.anyString())).thenReturn(mockCourse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/mergesort").contentType("application/json").content("[3,5,1,83,51,99]");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Execution execution=new ObjectMapper().readValue(result.getResponse().getContentAsString(), Execution.class);
		log.debug("Respuesta de Controller: "+ execution);
		assertThat(execution.getStatus()).isEqualTo(EnumStatus.pending);
	}

	@Test
	public void shouldReturnSingleCompletedExecution() throws Exception {

	}
	
	@Test
	public void shouldReturnAllExecutions() throws Exception {

	}
	
}