package tcp.microservices.aws.lambda.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;

import tcp.microservices.aws.lambda.db.Customer;
import tcp.microservices.aws.lambda.db.CustomerRepository;
import tcp.microservices.aws.lambda.dto.AwsProxyRequestDto;
import tcp.microservices.aws.lambda.dto.AwsProxyResponseDto;
import tcp.microservices.aws.lambda.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FunctionComponentJPA {
    static Logger log = LoggerFactory.getLogger(FunctionComponentJPA.class);

    @Autowired
    CustomerRepository repository;

    private String name;
    
    public AwsProxyResponseDto processRequest(AwsProxyRequestDto input, Context context) {
        // JPA //
    	log.info("Antes de insert...........");
        repository.save(new Customer("Heinz","Muander"));
        repository.save(new Customer("Krispin","Clander"));
        Iterable<Customer> list=repository.findAll();
        log.info("Find all.....");
        list.forEach(e -> log.info("Customer: " + e)); 
        log.info("Find findByLastName.....");
        list=repository.findByLastName("Clander");
        list.forEach(e -> {
        	log.info("Customer: " + e);
        	name=new String(e.getFirstName()+" "+e.getLastName());
        }); 
        // Respuesta //
        Map<String,String> mapa=new HashMap<String,String>();
        mapa.put("Content-Type", "application/json");
        String json=null;
        ObjectMapper objectMapper = new ObjectMapper();
    	try {
            // Leer JSON //
            UserDto userDtoIn=new UserDto(name, 50);	        
            // Generar JSON //
    		json = objectMapper.writeValueAsString(userDtoIn);
    	} catch (Exception e) {
    		log.error(e.getMessage());
    	}
        return new AwsProxyResponseDto(false, 200, mapa, json);
    }
      
}
