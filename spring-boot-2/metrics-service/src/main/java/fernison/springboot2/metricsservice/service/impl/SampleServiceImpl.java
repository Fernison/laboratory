package fernison.springboot2.metricsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fernison.springboot2.metricsservice.api.dto.ResponseData;
import fernison.springboot2.metricsservice.api.dto.RequestData;
import fernison.springboot2.metricsservice.exception.MetricsServiceException;
import fernison.springboot2.metricsservice.service.ISampleService;

@Service
public class SampleServiceImpl implements ISampleService {
 
	private static final Logger LOG = LoggerFactory.getLogger(SampleServiceImpl.class);
		
	public ResponseData executeOperation(RequestData data) throws MetricsServiceException {
		LOG.debug("RequestData: "+data);
		return new ResponseData("OK", ResponseData.OK);
	}
		
}
