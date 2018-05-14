package com.ust.sagaeventsourcing.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ust.sagaeventsourcing.saga.SagaData;

/**
 * Class that represent event data in a avenet sourcing scenario
 * Structure:
 * {
	  "timestamp" : 1523001841078,
	  "application" : "mi application",
	  "saga" : "my saga1",
	  "eventName" : "my event1_1",
	  "status" : "COMMITED",
	  "data" : {
	    ... ANY DATA
	  },
	  "id_transaccion" : "88a925ba-67c3-4b75-98a6-ec4fd2fe8143"
   }
 * @author fcabrero
 * @param <T>
 */
public class Eventide<T> extends SagaData {
	
	// Metodos comunes de eventos //
	private String eventName;
	private String status;
	private T data;
	
	protected Eventide() {
		super();
	}
	
	protected Eventide(String eventName, T data) {
		super();
		this.eventName = eventName;
		this.data = data;
	}

	public String getEventName() {
		return eventName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}
	
	@Override
	public String toStorageFormat() throws Exception {		
		// Devolver en el formato adecuado (JSON) ...//
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(this);
	}	
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Eventide { ").
		append(super.toString()).
		append("[eventName]:").append(this.eventName).		
		append(" [status]:").append(this.status).
		append(" [data]:").append(this.data).
		append(" }");
		return sb.toString();
	}
	
//	public  fromStorageFormat() throws Exception {		
//		// Devolver en el formato adecuado (JSON) ...//
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		return ow.writeValueAsString(this);
//	}	
	
	
}
