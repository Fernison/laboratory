package tcp.microservices.simple.client.service;

import java.util.Optional;

public interface IConfigService {  
    void testRefresh(Optional<String> name);  
} 