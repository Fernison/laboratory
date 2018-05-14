package com.ust.sagaeventsourcing.ms.initsrv.test.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class kafkamsg {

	@Id
	private String msg;     
	
	public kafkamsg() {
		super();
	}

	public String getDescripcion() {
		return msg;
	}

	public void setDescripcion(String descripcion) {
		this.msg = descripcion;
	}

	@Override
	public String toString() {
		return "Kafkamsg [msg=" + msg + "]";
	}

}
