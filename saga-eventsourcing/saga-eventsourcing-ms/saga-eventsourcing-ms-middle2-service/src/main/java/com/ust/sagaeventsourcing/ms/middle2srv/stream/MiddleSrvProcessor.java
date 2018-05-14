package com.ust.sagaeventsourcing.ms.middle2srv.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MiddleSrvProcessor {
	
	String WRITE = "write-stream";
	String READ = "read-stream";

    @Output(WRITE)
    MessageChannel outputEvent();
    
    @Input(READ)
    SubscribableChannel inputEvent();
    
}
