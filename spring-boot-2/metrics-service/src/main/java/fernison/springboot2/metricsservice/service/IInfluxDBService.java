package fernison.springboot2.metricsservice.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public interface IInfluxDBService {

	public CompletableFuture<String> writeMeasure(MetricData data) throws CompletionException;
	
}
