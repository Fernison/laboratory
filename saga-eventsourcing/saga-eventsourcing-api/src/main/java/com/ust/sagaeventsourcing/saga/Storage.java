package com.ust.sagaeventsourcing.saga;

/**
 * Class that represents the storage where sagadata is stored
 * @author fcabrero
 *
 */
public abstract class Storage<T> {

	private String name;
	private int type;
	
	public Storage(String name, int type) {
		this.name=name;
		this.type=type;
	}
	
	public abstract void store(T data) throws Exception;
	
	protected String getName() {
		return name;
	}

	protected int getType() {
		return type;
	}

}
