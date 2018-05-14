package com.ust.sagaeventsourcing.ms.initsrv.service;

import com.ust.sagaeventsourcing.ms.initsrv.api.dto.SimpleDto;

public interface ISimpleService {  
    String print(SimpleDto simpleDto) throws Exception;  
} 