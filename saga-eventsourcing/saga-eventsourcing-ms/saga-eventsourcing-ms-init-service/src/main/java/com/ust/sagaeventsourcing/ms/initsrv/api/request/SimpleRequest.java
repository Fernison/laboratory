package com.ust.sagaeventsourcing.ms.initsrv.api.request;

import com.ust.sagaeventsourcing.ms.common.api.request.BaseRequest;
import com.ust.sagaeventsourcing.ms.initsrv.api.dto.SimpleDto;

public class SimpleRequest extends BaseRequest {

	transient private static final long serialVersionUID = 4773358733927312308L;

	private SimpleDto data;
		
	public SimpleDto getData() {
		return data;
	}

	public void setData(SimpleDto data) {
		this.data = data;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("SimpleRequest{");
		sb.append(super.toString());
		sb.append(this.data);
		sb.append("}");
		return sb.toString();
	}
	
	/*
	private String cadena;
	private long numero;
	
	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("SimpleRequest{");
		sb.append(super.toString());
		sb.append(cadena+":"+numero);
		sb.append("}");
		return sb.toString();
	}
	*/
}
