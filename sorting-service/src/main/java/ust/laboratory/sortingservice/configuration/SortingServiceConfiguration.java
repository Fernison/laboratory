package ust.laboratory.sortingservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;

import ust.laboratory.sortingservice.sorter.MergesortSorter;
import ust.laboratory.sortingservice.sorter.Sorter;
import ust.laboratory.sortingservice.storage.MemoryStorage;
import ust.laboratory.sortingservice.storage.RedisStorage;
import ust.laboratory.sortingservice.storage.Storage;

@Configuration
@EnableAsync
public class SortingServiceConfiguration { 
	
	private static final Logger Log = LoggerFactory.getLogger(SortingServiceConfiguration.class); 

    @Value("${app.sorter: mergersoft}")
    private String sorter;
    @Value("${app.storage: memory}")
    private String storage;
    
	@Bean
	public Sorter getSorter() {
		switch(sorter) {
			case "mergersoft":
			default:
				Log.info("************************** Selecting MERGERSOFT sorter **************************");				
				return new MergesortSorter();
		}		
	}	
		
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) // No haría falta porque por defecto el scope es Singleton
															// Lo contrario es "Prototype" que crea una instancia por peticion	
	public Storage getStorage() {
		switch(storage) {
			case "redis":
				Log.info("************************** Selecting REDIS storage **************************");				
				return new RedisStorage();
			case "memory":
			default:
				Log.info("************************** Selecting IN MEMORY storage **************************");				
				return new MemoryStorage();
		}
	}

}
