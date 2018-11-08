package ust.laboratory.sparkjava;  

import static spark.Spark.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {  

	private static final Logger log = LoggerFactory.getLogger(Application.class); 

//	private static final int HTTP_BAD_REQUEST = 400;
	
	public static void main(String[] args) {
        get("/hello", (req, res) -> { 
            log.info("GET");            
            return "Hello World Spark App. Request: "+req.body();        
        });
        
        post("/hello", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            RequestData data = mapper.readValue(request.body(), RequestData.class);
            log.info("Data:"+data);            
            String id = "UUID.randomUUID()";
            response.status(200);
            response.type("application/json");
            ResponseData respData=new ResponseData(id); 
            return mapper.writeValueAsString(respData);
        });
    }
    
} 
