package ust.laboratory.sortingservice.storage.redis;

import java.util.Map;
import java.util.UUID;

import ust.laboratory.sortingservice.api.dto.Execution;

public interface IRedisRepository {

	public void save(Execution execution) throws Exception;
	
	public Execution getByUid(UUID uuid) throws Exception;
	
	public Map<UUID, Execution> getAll() throws Exception;

	public void clear() throws Exception;
	
}
