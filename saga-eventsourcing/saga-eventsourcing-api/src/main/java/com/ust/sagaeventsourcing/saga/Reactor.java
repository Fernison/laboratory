package com.ust.sagaeventsourcing.saga;

public interface Reactor<T> {
	
	public void notifyEvent(T event);

}
