package tcp.microservices.actuator.service;

import tcp.microservices.actuator.api.dto.ActuatorDto;

public interface IServiceActuatorService {  
    String doSomething(ActuatorDto dto);  
    String doOtherthing(ActuatorDto dto);
} 