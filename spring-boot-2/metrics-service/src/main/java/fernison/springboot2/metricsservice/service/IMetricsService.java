package fernison.springboot2.metricsservice.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import fernison.springboot2.metricsservice.annotations.Measurable;

public interface IMetricsService {

	public CompletableFuture<String> writeMeasure(Object args[], Measurable measurable) throws CompletionException;
	
}
