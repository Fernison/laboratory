package tcp.microservices.war.service;

import tcp.microservices.war.api.dto.SimpleDto;

public interface ISimpleService {  
    String print(SimpleDto simpleDto) throws Exception;  
} 