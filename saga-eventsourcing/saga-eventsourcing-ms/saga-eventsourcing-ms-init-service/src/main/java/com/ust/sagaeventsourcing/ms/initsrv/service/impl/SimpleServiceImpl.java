package com.ust.sagaeventsourcing.ms.initsrv.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sagaeventsourcing.ms.initsrv.api.dto.SimpleDto;
import com.ust.sagaeventsourcing.ms.initsrv.service.ISimpleService;

@Service
public class SimpleServiceImpl implements ISimpleService {  
    
	private static final Logger log = LoggerFactory.getLogger(SimpleServiceImpl.class); 

	@Autowired
	private MySagaPart sagaPart;
	
    @Override     
    public String print(SimpleDto simpleDto) throws Exception {
		final String result = "DATA: " + simpleDto;
    	log.info("[RESULT]:{}", result);     
    	sagaPart.initiateSaga(simpleDto);
        return result;  
    }
      
} 