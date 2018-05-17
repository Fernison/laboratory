package tcp.microservices.aop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tcp.microservices.aop.api.dto.AopDto;
import tcp.microservices.aop.service.IServiceAopService;

@Service 
public class ServiceAopServiceImpl implements IServiceAopService {  
    
	private static final Logger log = LoggerFactory.getLogger(ServiceAopServiceImpl.class); 

	@Override     
	public String doSomething(final AopDto aopDto) {
		StringBuilder sb = new StringBuilder()
				.append("RESPONSE:")
				.append("[id]:").append(aopDto.getId())
				.append("[data1]:").append(aopDto.getData1())
				.append("[data2]:").append(aopDto.getData2());
		log.info("[RESULT]:{}", sb.toString());
		return sb.toString(); 
	}
	
    // AOP //
	@Override     
	public String doOtherthing(final AopDto aopDto) {
		return null;
	}
} 