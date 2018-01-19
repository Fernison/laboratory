package ust.laboratory.kubernetesservice.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${app.service2_name: srv1-srv2}", url = "${app.service2_url: http://srv1-srv2:9090}", 
			fallback = ust.laboratory.kubernetesservice.service.impl.FeignFallbackImpl.class)
public interface IFeignServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/kubernetes-spring-boot-service-2/service2/{name}")
	String sayRemoteHello(@PathVariable("name") String name);
	
}
