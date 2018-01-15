package ust.laboratory.sortingservice.test.sorter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ust.laboratory.sortingservice.configuration.SortingServiceConfiguration;
import ust.laboratory.sortingservice.sorter.MergesortSorter;

@RunWith(SpringRunner.class)
@Import(SortingServiceConfiguration.class)
public class MergeSortTest {

	private static final Logger log = LoggerFactory.getLogger(MergeSortTest.class); 
	
    @Autowired
    private MergesortSorter sorter;
    
    private int[] unSortedList=new int[]{5,9,7,6,23};
    private int[] sortedList=new int[]{5,6,7,9,23};
    private int[] sameNumbersUnSortedList=new int[]{5,9,5,9,23};
    private int[] sameNumbersSortedList=new int[]{5,5,9,9,23};
    private int[] oneNumberList=new int[]{5};
    
        
	@Test
	public void shouldSortANumberList() throws Exception {
		int[] output=sorter.sort(unSortedList);
		assertThat(output).isEqualTo(sortedList);
	}

	@Test
	public void shouldSortANumberListWithSameNumbers() throws Exception {
		int[] output=sorter.sort(sameNumbersUnSortedList);
		assertThat(output).isEqualTo(sameNumbersSortedList);
	}
	
	@Test
	public void shouldReturnAnErrorBecauseNumberListTooShort() throws Exception {
		int[] output=sorter.sort(oneNumberList);
		assertThat(output).isNull();
	}
	
}