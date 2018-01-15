package ust.laboratory.sortingservice.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import ust.laboratory.sortingservice.storage.Storage;

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
	
	@Autowired
	private Storage storage;
    	    
    private List<Execution> executionList; 
    private Stream<Integer> orderedStream;
    private StringBuffer unorderedList;
    
    @Rule
    public TestName testMethod = new TestName();
    
    @Before
    public void init(){
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();       	
	   	try {
	    	storage.clear();
	    } catch (Exception e) {
			log.error(e.getMessage());
		}
	   	
	   	if(testMethod.getMethodName().equals("shouldSortHugeList")) {		   	
	   		// Populate huge array //
	   		ArrayList<Integer> unorderedArray=new ArrayList<Integer>(); 
	   		unorderedList=new StringBuffer();
	   		unorderedList.append("[");
			IntStream.range(0, 1000).
			map(i -> new Random().nextInt(10000)).
			forEach(i -> {
				unorderedList.append(""+i+",");
				unorderedArray.add(new Integer(i));
			});
	   		unorderedList.deleteCharAt(unorderedList.length()-1); // Se borra la ultima ","		
	   		unorderedList.append("]");
	   		orderedStream=unorderedArray.stream().sorted();
	   	} else if(!testMethod.getMethodName().equals("shouldReturnEmptyExecutions")) {		   	
   			// Only populate data if method name not equals shouldReturnEmptyExecutions //
			executionList=new ArrayList<Execution>();    
			Execution executionList1=new Execution();
			executionList1.setDuration(100);
			executionList1.setId(UUID.randomUUID());
			executionList1.setInput(new int[]{3,6,1,4,5});
			executionList1.setOutput(new int[]{1,3,4,5,6});
			executionList1.setStatus(EnumStatus.completed);
			executionList1.setTimestamp(new Date());
			executionList.add(executionList1);
			try {
				storage.save(executionList1);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			Execution executionList2=new Execution();
			executionList2.setDuration(100);
			executionList2.setId(UUID.randomUUID());
			executionList2.setInput(new int[]{8,5,17,78,6});
			executionList2.setOutput(new int[]{5,6,8,17,78});
			executionList2.setStatus(EnumStatus.completed);
			executionList2.setTimestamp(new Date());
			executionList.add(executionList2);
			try {
				storage.save(executionList2);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
	   	} 
    } 
    
	@Test
	public void shouldReturnPendingExecution() throws Exception { 
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/mergesort").contentType("application/json").content("[3,5,1,83,51,99]");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Execution execution=new ObjectMapper().readValue(result.getResponse().getContentAsString(), Execution.class);
		log.debug("Respuesta de Controller: "+ execution);
		assertThat(execution.getStatus()).isEqualTo(EnumStatus.pending);
		// Comprobar del storage que se ha cogido bien //
		Execution executionAux=storage.getByUid(execution.getId());
		log.debug(""+executionAux);
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.OK));
		assertThat(execution.getId()).isEqualTo(executionAux.getId());
		assertThat(new int[]{3,5,1,83,51,99}).isEqualTo(executionAux.getInput());
	}
	
	@Test
	public void shouldReturnErrorBecauseInvalidInputData() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/mergesort").contentType("application/json").content("[3]");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void shouldSortHugeList() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/mergesort").contentType("application/json").content(unorderedList.toString());
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Execution execution=new ObjectMapper().readValue(result.getResponse().getContentAsString(), Execution.class);
		assertThat(execution.getStatus()).isEqualTo(EnumStatus.pending);
		// Comprobar del storage que se ha cogido bien //
		try {
			Thread.sleep(2000); // Para que de tiempo a que termine la ordenación 
		} catch(Exception e) {}
		Execution executionAux=storage.getByUid(execution.getId());
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.OK));
		assertThat(execution.getId()).isEqualTo(executionAux.getId());		
		assertThat(orderedStream.toArray()).isEqualTo(executionAux.getOutput());
	}

	@Test
	public void shouldReturnSingleCompletedExecution() throws Exception {		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/mergesort/executions/"+executionList.get(0).getId()).contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Execution execution=new ObjectMapper().readValue(result.getResponse().getContentAsString(), Execution.class);
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.OK));
		assertThat(execution.getStatus()).isEqualTo(EnumStatus.completed);
		assertThat(execution.getInput()).isEqualTo(executionList.get(0).getInput());
	}
	
	@Test
	public void shouldReturnAllExecutions() throws Exception {
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
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/mergesort/executions").contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		ExecutionListResponse executions=new ObjectMapper().readValue(result.getResponse().getContentAsString(), 
				ExecutionListResponse.class);
		assertThat(result.getResponse().getStatus()).isEqualTo(Integer.parseInt(""+HttpStatus.OK));
		assertThat(executions.getExecutions().size()).isZero();
	}
	
}