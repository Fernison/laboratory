package tcp.microservices.sleuth.service1.service;

import java.util.Optional;

public interface ISleuthService1 {  
    String send(Optional<String> name);  
} 