package com.ust.sagaeventsourcing.test;

import com.ust.sagaeventsourcing.event.EventHandlerMethod;
import com.ust.sagaeventsourcing.event.EventSubscriber;
import com.ust.sagaeventsourcing.event.Eventide;

@EventSubscriber(saga="my saga2")
public class TestEventHandler2 {

	@EventHandlerMethod(event= "my event2_1", status="UPDATE OK")
	public Eventide<TestSimpleData> operacion2_1(Eventide<TestSimpleData> event) {
		return null;
	}

	@EventHandlerMethod(event= "my event2_1", status="UPDATE KO")
	public Eventide<TestSimpleData> operacion2_1bis(Eventide<TestSimpleData> event) {
		return null;
	}
	
}
