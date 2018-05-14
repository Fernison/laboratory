package com.ust.sagaeventsourcing.test;

public class TestSimpleData {

	private int id;
	private String name;
	
	public TestSimpleData() {
		super();
	}
	
	public TestSimpleData(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SimpleData [id=" + id + ", name=" + name + "]";
	}
		
}
