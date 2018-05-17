package tcp.microservices.aws.lambda;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tcp.microservices.aws.lambda.dto.AwsProxyRequestDto;
import tcp.microservices.aws.lambda.dto.AwsProxyResponseDto;
import tcp.microservices.aws.lambda.function.FunctionComponentJPA;

@SpringBootApplication
public class LambdaHandlerJPA implements RequestHandler<AwsProxyRequestDto, AwsProxyResponseDto> {
	
	private static final Logger log = LoggerFactory.getLogger(LambdaHandlerJPA.class); 

	@Override
    public AwsProxyResponseDto handleRequest(AwsProxyRequestDto input, Context context) {
		log.debug("************** Init handleRequest LambdaHandlerJPA!!!! ****************");
        SpringBootInvocation springBootInvocation =  new SpringBootInvocation();
        springBootInvocation.run();
        return springBootInvocation.getApplicationContext().getBean(FunctionComponentJPA.class).processRequest(input, context);
    }

	public static void main(String[] args) { 
		log.debug("************** Init Main LambdaHandler!!!! ****************");
        SpringBootInvocation springBootInvocation =  new SpringBootInvocation();
        springBootInvocation.run();
        springBootInvocation.getApplicationContext().getBean(FunctionComponentJPA.class).processRequest(null,null);
	} 

}
