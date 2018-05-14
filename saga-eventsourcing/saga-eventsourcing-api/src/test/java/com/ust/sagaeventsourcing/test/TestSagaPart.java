package com.ust.sagaeventsourcing.test;

import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.saga.InitSaga;
import com.ust.sagaeventsourcing.saga.SagaInitiator;

public class TestSagaPart extends SagaInitiator<Eventide<TestSimpleData>,TestSimpleData> {
 
	@Override
	@InitSaga(application="mi app", saga="mi saga")
	public Eventide<TestSimpleData> initiateSaga(TestSimpleData simpleData) {
		TestEvent myEvent=new TestEvent("event name", simpleData);
		myEvent.setId_transaccion("MY ID TRANSACTION");
		myEvent.setStatus("COMMITED");
		return myEvent;
	}
	
}
