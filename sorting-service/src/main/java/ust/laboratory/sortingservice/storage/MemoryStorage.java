package ust.laboratory.sortingservice.storage;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ust.laboratory.sortingservice.api.dto.Execution;

public final class MemoryStorage extends Storage {

	private static final Logger log = LoggerFactory.getLogger(MemoryStorage.class);

	private HashMap<UUID, Execution> storage=new HashMap<UUID, Execution>();
	
	public MemoryStorage() {
		super();
	}

	@Override
	public void save(Execution execution) {
		storage.put(execution.getId(), execution);
		log.debug("Storage {}",storage.size());
	}
	
	@Override
	public Execution getByUid(UUID uuid) {
		return storage.get(uuid);
	}
	
	@Override
	public List<Execution> getAll() {
		List<Execution> list = storage.values().stream().collect(Collectors.toList());
		return list;
	}
	
	
}
