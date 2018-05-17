package tcp.microservices.netflix.feign.client;

import java.util.Optional;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * Usando una URL completa
@FeignClient(name = "${app.service2.feign_name}", url = "${app.service2.host}", 
			fallback = tcp.microservices.netflix.feign.client.impl.FeignFallbackImpl.class)
*/
/*
 * Resolviendo el host del servicio por Eureka. Se pone el ID de Eureka en el nombre del FeignClient
 */
@FeignClient(name = "${app.service2.feign_name}", fallback = tcp.microservices.netflix.feign.client.impl.FeignFallbackImpl.class)
public interface IFeignClient {  
    
	// Con parámetros opcionales //
	@RequestMapping(method = RequestMethod.GET, value="${app.service2.url}")	
	public String doSomething(Optional<String> param) throws Exception;
	
	// Sin parámetros opcionales //
	@RequestMapping(method = RequestMethod.GET, value="${app.service2.url}")	
	public String doSomething2() throws Exception;
	

} 

