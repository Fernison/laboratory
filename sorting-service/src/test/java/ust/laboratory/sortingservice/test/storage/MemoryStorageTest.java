package ust.laboratory.sortingservice.test.storage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ust.laboratory.sortingservice.api.dto.EnumStatus;
import ust.laboratory.sortingservice.api.dto.Execution;
import ust.laboratory.sortingservice.configuration.SortingServiceConfiguration;
import ust.laboratory.sortingservice.storage.MemoryStorage;

@RunWith(SpringRunner.class)
@Import(SortingServiceConfiguration.class)
public class MemoryStorageTest {

	private static final Logger log = LoggerFactory.getLogger(MemoryStorageTest.class); 
	
    @Autowired
    private MemoryStorage storage;
    
    private Execution executionToSave;
    private List<Execution> executionList;    
    
    @Before
    public void init(){
    	try {
        	storage.clear();
        } catch (Exception e) {
			log.error(e.getMessage());
		}
    	executionToSave=new Execution();
    	executionToSave.setDuration(100);
    	executionToSave.setId(UUID.randomUUID());
    	executionToSave.setInput(new int[]{3,6,1,4,5});
    	executionToSave.setOutput(new int[]{1,3,4,5,6});
    	executionToSave.setStatus(EnumStatus.completed);
    	executionToSave.setTimestamp(new Date());
    	
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
    	executionList2.setInput(new int[]{3,6,1,4,5});
    	executionList2.setOutput(new int[]{1,3,4,5,6});
    	executionList2.setStatus(EnumStatus.completed);
    	executionList2.setTimestamp(new Date());
    	executionList.add(executionList2);
    	try {
			storage.save(executionList2);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
    } 
    
	@Test
	public void shouldStoreExecution() throws Exception {
		storage.save(executionToSave);
		assertThat(storage.getByUid(executionToSave.getId()).getInput()).isEqualTo(executionToSave.getInput());
	}

	@Test
	public void shouldReturnExecutionById() throws Exception {
		Execution execution=storage.getByUid(executionList.get(0).getId());
		assertThat(execution.getTimestamp()).isEqualTo(executionList.get(0).getTimestamp());
	}
	
	@Test
	public void shouldNotReturnExecutionById() throws Exception {
		Execution execution=storage.getByUid(UUID.randomUUID());
		assertThat(execution).isNull();
	}

	@Test
	public void shouldReturnAllExecutions() throws Exception {
		List<Execution> list=storage.getAll();
		assertThat(list.size()).isEqualTo(executionList.size());
	}
	
}