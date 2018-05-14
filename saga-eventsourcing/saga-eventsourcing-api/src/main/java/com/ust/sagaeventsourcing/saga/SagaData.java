package com.ust.sagaeventsourcing.saga;

import java.util.Date;

/**
 * Class that represents Saga object data
 * @author fcabrero
 *
 */
public abstract class SagaData {

	private String id_transaction;
	private Date timestamp;
	private String application;
	private String saga;

	protected SagaData() {
		//id_transaction=""+UUID.randomUUID(); // No se crea por defecto //
		timestamp=new Date();
	}
	
	public abstract String toStorageFormat() throws Exception;

	// TODO: Debería encontrar la forma de hacer que no se pueda modificar //
	public void setApplication(String application) {
		this.application = application;
	}

	// TODO: Debería encontrar la forma de hacer que no se pueda modificar //
	public void setSaga(String saga) {
		this.saga = saga;
	}

	public String getId_transaccion() {
		return id_transaction;
	}
	
	public void setId_transaccion(String id_transaction) {
		this.id_transaction=id_transaction;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getApplication() {
		return application;
	}

	public String getSaga() {
		return saga;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("SagaData { ").
		append("[id_transaction]:").append(this.id_transaction).		
		append(" [timestamp]:").append(this.timestamp).
		append(" [application]:").append(this.application).
		append(" [saga]:").append(this.saga).
		append(" }");
		return sb.toString();
	}
}
