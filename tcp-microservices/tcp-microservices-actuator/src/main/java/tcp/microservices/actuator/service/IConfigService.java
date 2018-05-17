package tcp.microservices.actuator.service;

import java.util.Optional;

public interface IConfigService {  
    void testRefresh(Optional<String> name);  
} 