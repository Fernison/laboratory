package ust.laboratory.sortingservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import ust.laboratory.sortingservice.api.dto.Execution;

@Configuration
@ComponentScan("ust.laboratory.sortingservice")
public class RedisConfiguration {

    @Value("${spring.redis.host: localhost}")
    private String host;
    @Value("${spring.redis.port: 6379}")
    private String port;
    
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
	    jedisConFactory.setHostName(host);
	    jedisConFactory.setPort(Integer.parseInt(port));
	    return jedisConFactory;
	}
	
	@Bean
	public RedisTemplate<String, Execution> redisTemplate() {
	    final RedisTemplate<String, Execution> template = new RedisTemplate<String, Execution>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    template.setValueSerializer(new GenericToStringSerializer<Execution>(Execution.class));
	    return template;
	}
	
}
