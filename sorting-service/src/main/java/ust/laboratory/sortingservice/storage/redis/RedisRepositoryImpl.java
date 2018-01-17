package ust.laboratory.sortingservice.storage.redis;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ust.laboratory.sortingservice.api.dto.Execution;

@Repository
public class RedisRepositoryImpl implements IRedisRepository {

    private static final String KEY = "Execution";
	     
    private RedisTemplate<String, Execution> redisTemplate;
    private HashOperations<String, UUID, Execution> hashOps;
 
    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Execution> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    	 
    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }
    
	@Override
	public void save(Execution execution) throws Exception {
		hashOps.put(KEY, execution.getId(), execution);
	}

	@Override
	public Execution getByUid(UUID uuid) throws Exception {
		return (Execution) hashOps.get(KEY, uuid);
	}

	@Override
	public Map<UUID, Execution> getAll() throws Exception {
		return hashOps.entries(KEY);
	}

	@Override
	public void clear() throws Exception {
		hashOps.delete(KEY);
	}

}
