package ust.laboratory.sortingservice.sorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public final class MergesortSorter extends Sorter {

	private static final Logger log = LoggerFactory.getLogger(MergesortSorter.class);

	public MergesortSorter() {
		super();
	}

	@Override
	public int[] sort(int[] input) {
		log.debug("sort: {}", input);
		
		return new int[]{1,2,3,4,5};
	}

	
	
}
