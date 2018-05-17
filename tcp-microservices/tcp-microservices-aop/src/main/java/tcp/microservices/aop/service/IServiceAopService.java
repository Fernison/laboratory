package tcp.microservices.aop.service;

import tcp.microservices.aop.api.dto.AopDto;

public interface IServiceAopService {  
    String doSomething(AopDto aopDto);  
    // AOP //
    String doOtherthing(AopDto aopDto);
} 