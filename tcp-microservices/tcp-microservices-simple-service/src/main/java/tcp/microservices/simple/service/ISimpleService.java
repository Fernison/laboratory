package tcp.microservices.simple.service;

import tcp.microservices.simple.api.dto.SimpleDto;

public interface ISimpleService {  
    String print(SimpleDto simpleDto) throws Exception;  
} 