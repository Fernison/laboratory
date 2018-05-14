package com.ust.sagaeventsourcing.ms.initsrv.api.dto;

import java.io.Serializable;

public class SimpleDto implements Serializable {

	transient private static final long serialVersionUID = 1134554639503238072L;

	private Long id;
	private String data1;
	private String data2;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder()
		.append("SimpleDto{[id]:").append(this.id)
		.append("[data1]:").append(this.data1)
		.append("[data2]:").append(this.data2)
		.append("}");
		return sb.toString();
	}
}
