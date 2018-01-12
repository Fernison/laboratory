package ust.laboratory.sortingservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;

import ust.laboratory.sortingservice.controller.SortingServiceController;
import ust.laboratory.sortingservice.sorter.MergesortSorter;
import ust.laboratory.sortingservice.sorter.Sorter;
import ust.laboratory.sortingservice.storage.MemoryStorage;
import ust.laboratory.sortingservice.storage.Storage;

@Configuration
@EnableAsync
public class SortingServiceConfiguration { 
	
	private static final Logger log = LoggerFactory.getLogger(SortingServiceConfiguration.class); 

	@Bean
	public Sorter getSorter() {
		log.debug("********************************* getSorter");
		return new MergesortSorter();
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Storage getStorage() {
		log.debug("*********************************** getStorage");
		return new MemoryStorage();
	}

}
