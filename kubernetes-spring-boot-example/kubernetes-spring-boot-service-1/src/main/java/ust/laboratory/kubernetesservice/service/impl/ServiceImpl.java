package ust.laboratory.kubernetesservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ust.laboratory.kubernetesservice.service.IFeignServiceClient;
import ust.laboratory.kubernetesservice.service.IService;

@Service
public class ServiceImpl implements IService {
 
	private static final Logger Log = LoggerFactory.getLogger(ServiceImpl.class);
	
	@Autowired
	private IFeignServiceClient client;
	
	public String sayEcho(String name) {
		return client.sayRemoteHello(name);
	}
	
}
