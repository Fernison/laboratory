package tcp.microservices.atomikos.service2.service;

import java.util.Optional;

public interface IService2 {  
    String doSomething(Optional<String> param) throws Exception;  
} 