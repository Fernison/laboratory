package com.ust.sagaeventsourcing.ms.finalsrv.conf;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.ust.sagaeventsourcing.ms.finalsrv.stream.MiddleSrvProcessor;

@EnableBinding(value = {MiddleSrvProcessor.class})
public class KafkaConfiguration {

}