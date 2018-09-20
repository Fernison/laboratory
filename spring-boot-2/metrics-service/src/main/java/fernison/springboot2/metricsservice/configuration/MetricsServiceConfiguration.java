package fernison.springboot2.metricsservice.configuration;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import fernison.springboot2.metricsservice.annotations.MeasurableAspect;

@Configuration
@EnableAspectJAutoProxy
@EnableAsync
public class MetricsServiceConfiguration { 
	
	private static final Logger LOG = LoggerFactory.getLogger(MetricsServiceConfiguration.class); 

	@Bean
	public MeasurableAspect createMeasurableAspectAspect(){
		LOG.info("Starting MeasurableAspect Aspect ...");
		return new MeasurableAspect();
	}

    // Inicializa el Executor que gestiona las peticiones asíncronas //
	@Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("MetricsService-");
        executor.initialize();
        return executor;
    }

}
