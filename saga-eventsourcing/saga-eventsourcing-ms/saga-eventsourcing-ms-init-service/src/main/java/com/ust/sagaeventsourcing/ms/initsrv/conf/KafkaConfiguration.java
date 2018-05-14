package com.ust.sagaeventsourcing.ms.initsrv.conf;

import org.springframework.cloud.stream.annotation.EnableBinding;
import com.ust.sagaeventsourcing.ms.initsrv.stream.InitSrvProcessor;

@EnableBinding(value = {InitSrvProcessor.class})
public class KafkaConfiguration {

}