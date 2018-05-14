package com.ust.sagaeventsourcing.ms.middle2srv.conf;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.ust.sagaeventsourcing.ms.middle2srv.stream.MiddleSrvProcessor;

@EnableBinding(value = {MiddleSrvProcessor.class})
public class KafkaConfiguration {

}