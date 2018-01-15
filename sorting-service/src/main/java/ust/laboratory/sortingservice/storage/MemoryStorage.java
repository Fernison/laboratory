package ust.laboratory.sortingservice.storage;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ust.laboratory.sortingservice.api.dto.Execution;

public final class MemoryStorage extends Storage {

	private static final Logger Log = LoggerFactory.getLogger(MemoryStorage.class);

	private HashMap<UUID, Execution> storage=new HashMap<UUID, Execution>();
	
	public MemoryStorage() {
		super("MEMORY-"+UUID.randomUUID(), Storage.MEMORY);
	}
	
	@Override
	public void save(Execution execution) throws Exception {
		storage.put(execution.getId(), execution);
		Log.debug("Storage name {}", getName());
	}
	
	@Override
	public Execution getByUid(UUID uuid) throws Exception {
		return storage.get(uuid);
	}
	
	@Override
	public List<Execution> getAll() throws Exception {
		List<Execution> list = storage.values().stream().collect(Collectors.toList());
		return list;
	}
	
	@Override
	public void clear() throws Exception {
		storage.clear();
	}
	
}
