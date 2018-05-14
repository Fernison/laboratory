package ust.laboratory.sortingservice.storage;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ust.laboratory.sortingservice.api.dto.Execution;
import ust.laboratory.sortingservice.storage.redis.IRedisRepository;

public final class RedisStorage extends Storage {

	private static final Logger Log = LoggerFactory.getLogger(RedisStorage.class);

	@Autowired
	private IRedisRepository redisRepo;
	
	public RedisStorage() {
		super("REDIS-"+UUID.randomUUID(), Storage.REDIS);
	}
	
	@Override
	public void save(Execution execution) throws Exception {
		redisRepo.save(execution);
		Log.debug("Storage name {}", getName());
	}
	
	@Override
	public Execution getByUid(UUID uuid) throws Exception {
		return redisRepo.getByUid(uuid);
	}
	
	@Override
	public List<Execution> getAll() throws Exception {
		List<Execution> list = redisRepo.getAll().values().stream().collect(Collectors.toList());
		return list;
	}
	
	@Override
	public void clear() throws Exception {
		redisRepo.clear();
	}
	
}
