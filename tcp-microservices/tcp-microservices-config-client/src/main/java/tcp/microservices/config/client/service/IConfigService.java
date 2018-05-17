package tcp.microservices.config.client.service;

import java.util.Optional;

public interface IConfigService {  
    void testRefresh(Optional<String> name);  
} 