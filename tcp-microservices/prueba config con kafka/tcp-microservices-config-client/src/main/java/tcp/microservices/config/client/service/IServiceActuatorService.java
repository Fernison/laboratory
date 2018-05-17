package tcp.microservices.config.client.service;

import tcp.microservices.config.client.api.dto.ActuatorDto;

public interface IServiceActuatorService {  
    String doSomething(ActuatorDto dto);  
    String doOtherthing(ActuatorDto dto);
} 