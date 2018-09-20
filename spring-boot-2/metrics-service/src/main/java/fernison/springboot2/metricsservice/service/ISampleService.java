package fernison.springboot2.metricsservice.service;

import fernison.springboot2.metricsservice.exception.MetricsServiceException;
import fernison.springboot2.metricsservice.api.dto.RequestData;
import fernison.springboot2.metricsservice.api.dto.ResponseData;

public interface ISampleService {

	public ResponseData executeOperation(RequestData data) throws MetricsServiceException;
	
}
