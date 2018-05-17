package tcp.microservices.couchbase.service;

import java.util.List;

import tcp.microservices.couchbase.api.dto.UserDto;

public interface IServiceCouchbaseService {  
    void save(UserDto aopDto) throws Exception;  
    List<UserDto> findAll() throws Exception;  
} 