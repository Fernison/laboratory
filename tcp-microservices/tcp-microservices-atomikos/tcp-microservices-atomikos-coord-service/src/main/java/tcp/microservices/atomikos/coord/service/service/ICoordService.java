package tcp.microservices.atomikos.coord.service.service;

import java.util.Optional;

public interface ICoordService {  
    String coordinate(Optional<String> param) throws Exception;  
} 