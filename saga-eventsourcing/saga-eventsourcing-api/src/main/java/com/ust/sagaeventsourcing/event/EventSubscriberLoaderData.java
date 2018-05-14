package com.ust.sagaeventsourcing.event;

import java.lang.reflect.Method;

public class EventSubscriberLoaderData {

	private Object bean;
	private Method method;

	public EventSubscriberLoaderData(Object bean, Method method) {
		super();
		this.bean = bean;
		this.method = method;
	}
	
	public Object getBean() {
		return bean;
	}

	public Method getMethod() {
		return method;
	}

	@Override
	public String toString() {
		return "EventSubscriberLoaderData [method=" + method.getName() + "]";
	}	
	
}
