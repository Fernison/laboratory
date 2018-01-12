package ust.laboratory.sortingservice.storage;

import java.util.List;
import java.util.UUID;

import ust.laboratory.sortingservice.api.dto.Execution;

public abstract class Storage {

	public abstract void save(Execution execution);
	
	public abstract Execution getByUid(UUID uuid);
	
	public abstract List<Execution> getAll();
	
}
