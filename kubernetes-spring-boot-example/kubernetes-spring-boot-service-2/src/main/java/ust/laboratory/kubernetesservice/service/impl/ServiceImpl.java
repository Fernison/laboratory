package ust.laboratory.kubernetesservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ust.laboratory.kubernetesservice.service.IService;

@Service
public class ServiceImpl implements IService {
 
	private static final Logger Log = LoggerFactory.getLogger(ServiceImpl.class);
		
	public String sayEcho(String name) {
		Log.debug("Service 2 invoked with data {}", name);
		return "Hello from beyond "+name+"!!!";
	}
	
}
