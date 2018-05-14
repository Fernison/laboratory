package com.ust.sagaeventsourcing.ms.middle1srv.conf;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.ust.sagaeventsourcing.ms.middle1srv.stream.MiddleSrvProcessor;

@EnableBinding(value = {MiddleSrvProcessor.class})
public class KafkaConfiguration {

}