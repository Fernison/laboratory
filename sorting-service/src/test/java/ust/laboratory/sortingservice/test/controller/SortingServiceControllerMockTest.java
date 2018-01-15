package ust.laboratory.sortingservice.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import ust.laboratory.sortingservice.api.dto.EnumStatus;
import ust.laboratory.sortingservice.api.dto.Execution;
import ust.laboratory.sortingservice.api.response.ExecutionListResponse;
import ust.laboratory.sortingservice.controller.SortingServiceController;
import ust.laboratory.sortingservice.exception.SortingServiceException;
import ust.laboratory.sortingservice.service.ISortService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SortingServiceController.class, secure = false)
@WebAppConfiguration
@ComponentScan(basePackages = "ust.laboratory.sortingservice") 
public class SortingServiceControllerMockTest {

	private static final Logger log = LoggerFactory.getLogger(SortingServiceControllerMockTest.class); 
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    
	@Autowired
	private MockMvc mockMvc;
    
	@MockBean
	private ISortService service; 
	
    private List<Execution> executionList; 
	private Execution executionToSave;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  
		executionToSave=new Execution();
		executionToSave.setId(UUID.randomUUID());
    	executionToSave.setStatus(EnumStatus.pending);
    	executionToSave.setInput(new int[]{3,5,1,83,51,99});
    	
		executionList=new ArrayList<Execution>();    
		Execution executionList1=new Execution();
		executionList1.setDuration(100);
		executionList1.setId(UUID.randomUUID());
		executionList1.setInput(new int[]{3,6,1,4,5});
		executionList1.setOutput(new int[]{1,3,4,5,6});
		executionList1.setStatus(EnumStatus.completed);
		executionList1.setTimestamp(new Date());
		executionList.add(executionList1);

		Execution executionList2=new Execution();
		executionList2.setDuration(100);
		executionList2.setId(UUID.randomUUID());
		executionList2.setInput(new int[]{8,5,17,78,6});
		executionList2.setOutput(new int[]{5,6,8,17,78});
		executionList2.setStatus(EnumStatus.completed);
		executionList2.setTimestamp(new Date());
		executionList.add(executionList2);
	}	
	
	@Test
	public void shouldReturnPendingExecution() throws Exception {
		Mockito.when(service.startExecution(new int[]{3,5,1,83,51,99})).thenReturn(executionToSave);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/mergesort").contentType("application/json").content("[3,5,1,83,51,99]");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Execution execution=new ObjectMapper().readValue(result.getResponse().getContentAsString(), Execution.class);
		log.debug("Respuesta de Controller: "+ execution);
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.OK));
		assertThat(execution.getStatus()).isEqualTo(EnumStatus.pending);
		assertThat(execution.getInput()).isEqualTo(executionToSave.getInput());
	}
	
	@Test
	public void shouldReturnErrorBecauseInvalidInputData() throws Exception {
		Mockito.when(service.startExecution(new int[]{3})).thenThrow(
				new SortingServiceException("ID_NOT_FOUND", SortingServiceException.INVALID_INPUT_DATA));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/mergesort").contentType("application/json").content("[3]");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.BAD_REQUEST));
	}

	@Test
	public void shouldReturnSingleCompletedExecution() throws Exception {		
		Mockito.when(service.getExecution(executionList.get(0).getId())).thenReturn(executionList.get(0));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/mergesort/executions/"+executionList.get(0).getId()).contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Execution execution=new ObjectMapper().readValue(result.getResponse().getContentAsString(), Execution.class);
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.OK));
		assertThat(execution.getStatus()).isEqualTo(EnumStatus.completed);
		assertThat(execution.getInput()).isEqualTo(executionList.get(0).getInput());
	}
	
	@Test
	public void shouldReturnExecutionNoFound() throws Exception {		
		Mockito.when(service.getExecution(executionList.get(0).getId())).thenThrow(
				new SortingServiceException("ID_NOT_FOUND", SortingServiceException.ID_NOT_FOUND));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/mergesort/executions/"+executionList.get(0).getId()).contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.NOT_FOUND));
	}
	
	@Test
	public void shouldReturnAllExecutions() throws Exception {
		Mockito.when(service.getExecutions()).thenReturn(executionList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/mergesort/executions").contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		ExecutionListResponse executions=new ObjectMapper().readValue(result.getResponse().getContentAsString(), 
											ExecutionListResponse.class);
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.OK));
		assertThat(executions.getExecutions().size()).isEqualTo(executionList.size());
	}

	@Test
	public void shouldReturnEmptyExecutions() throws Exception {
		Mockito.when(service.getExecutions()).thenReturn(new ArrayList<Execution>());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/mergesort/executions").contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		ExecutionListResponse executions=new ObjectMapper().readValue(result.getResponse().getContentAsString(), 
				ExecutionListResponse.class);
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.OK));
		assertThat(executions.getExecutions().size()).isZero();
	}
	
	@Test
	public void shouldReturnExecutionListBadRequest() throws Exception {		
		Mockito.when(service.getExecutions()).thenThrow(				
				new SortingServiceException("ID_NOT_FOUND", SortingServiceException.INTERNAL_ERROR));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/mergesort/executions").contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.BAD_REQUEST));;
	}
}