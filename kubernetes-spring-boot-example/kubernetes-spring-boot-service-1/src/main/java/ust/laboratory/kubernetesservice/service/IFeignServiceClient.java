package ust.laboratory.kubernetesservice.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${app.service2_name: SERVICE:service}", url = "${app.service2_url: http://service:9090}", 
			fallback = ust.laboratory.kubernetesservice.service.impl.FeignFallbackImpl.class)
//@FeignClient(name = "${SERVICE:service}", url = "${URL:http://service:9090}", 
//			fallback = ust.laboratory.kubernetesservice.service.impl.FeignFallbackImpl.class)
public interface IFeignServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/kubernetes-spring-boot-service-2/service2/{name}")
	String sayRemoteHello(@PathVariable("name") String name);
	
}
