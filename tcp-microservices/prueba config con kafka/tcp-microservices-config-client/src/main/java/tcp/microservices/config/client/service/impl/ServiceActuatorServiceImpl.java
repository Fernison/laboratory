package tcp.microservices.config.client.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;

import tcp.microservices.config.client.api.dto.ActuatorDto;
import tcp.microservices.config.client.service.IServiceActuatorService;

@Service 
public class ServiceActuatorServiceImpl implements IServiceActuatorService {  
    
	private static final Logger log = LoggerFactory.getLogger(ServiceActuatorServiceImpl.class); 

    private CounterService counterService;
    private GaugeService gaugeService; 
    
    @Autowired
    public ServiceActuatorServiceImpl(CounterService counterService,
    		GaugeService gaugeService) {
        this.counterService = counterService;
        this.gaugeService=gaugeService;
    }
	
	@Override     
	public String doSomething(final ActuatorDto dto) {
		log.info("[counterService]:{}", counterService);
		StringBuilder sb = new StringBuilder()
				.append("RESPONSE:")
				.append("[id]:").append(dto.getId())
				.append("[data1]:").append(dto.getData1())
				.append("[data2]:").append(dto.getData2());
		log.info("[RESULT]:{}", sb.toString());
		// Actuator //
		counterService.increment("counter.doSomething");
		gaugeService.submit("gauge.doSomething", 1);
		return sb.toString(); 
	}
	
	// Actuator lo aplicamos en este metodo con AOP //
	@Override     
	public String doOtherthing(final ActuatorDto dto) {
		return null;
	}
} 