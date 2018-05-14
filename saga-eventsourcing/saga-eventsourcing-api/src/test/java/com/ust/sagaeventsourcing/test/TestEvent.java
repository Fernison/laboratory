package com.ust.sagaeventsourcing.test;

import com.ust.sagaeventsourcing.event.Eventide;

public class TestEvent extends Eventide<TestSimpleData> {

	public TestEvent() {	}
	
	public TestEvent(String eventType, TestSimpleData simpleData) {
		super(eventType, simpleData);
	}

	@Override
	public String toString() {
		return "MyEvent [getId_transaccion()=" + getId_transaccion() + ", getTimestamp()=" + getTimestamp()
				+ ", getApplication()=" + getApplication() + ", getSaga()=" + getSaga() + ", getEventName()="
				+ getEventName() + ", getStatus()=" + getStatus() + ", getData()=" + getData() + "]";
	}

}
