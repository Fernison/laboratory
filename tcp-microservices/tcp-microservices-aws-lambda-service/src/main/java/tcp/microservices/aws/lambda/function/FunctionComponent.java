package tcp.microservices.aws.lambda.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;

import tcp.microservices.aws.lambda.dto.AwsProxyRequestDto;
import tcp.microservices.aws.lambda.dto.AwsProxyResponseDto;
import tcp.microservices.aws.lambda.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FunctionComponent {
    static Logger log = LoggerFactory.getLogger(FunctionComponent.class);

    @Autowired
    Environment environment;

    @Value(value = "${configtest.value}")
    private String configValue;

    public AwsProxyResponseDto processRequest(AwsProxyRequestDto input, Context context) {
        log.info("In the hello function: "+context.getAwsRequestId()+"-"+context.getClientContext()+"-"+
        		context.getFunctionName()+"-"+context.getFunctionVersion()+"-"+context.getIdentity()+
        		context.getInvokedFunctionArn()+"-"+context.getMemoryLimitInMB()+"-"+context.getRemainingTimeInMillis());
        log.info("Test value here = "+ configValue);
        log.info("env value = " + environment.getProperty("configtest.value"));
        Map<String,String> mapa=new HashMap<String,String>();
        mapa.put("Content-Type", "application/json");
        
        // Respuesta //
        String json=null;
        ObjectMapper objectMapper = new ObjectMapper();
		try {
	        // Leer JSON //
	        UserDto userDtoIn=objectMapper.readValue(input.getBody(), UserDto.class);	        
	        // Generar JSON //
			json = objectMapper.writeValueAsString(new UserDto(userDtoIn.getName()+" response", userDtoIn.getAge()+30));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
        return new AwsProxyResponseDto(false, 200, mapa, json);
    }
    
}
