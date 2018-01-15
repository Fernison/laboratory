package ust.laboratory.sortingservice.storage;

import java.util.List;
import java.util.UUID;

import ust.laboratory.sortingservice.api.dto.Execution;

public abstract class Storage {

	private String name;
	private int type;
	
	protected static final int MEMORY=1;
	protected static final int REDIS=1;
	protected static final int OTHER=99;
	
	public Storage(String name, int type) {
		this.name=name;
		this.type=type;
	}
	
	public abstract void save(Execution execution) throws Exception;
	
	public abstract Execution getByUid(UUID uuid) throws Exception;
	
	public abstract List<Execution> getAll() throws Exception;

	public abstract void clear() throws Exception;

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getType() {
		return type;
	}

	protected void setType(int type) {
		this.type = type;
	}
		
}
