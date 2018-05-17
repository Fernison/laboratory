package tcp.microservices.couchbase.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcp.microservices.couchbase.api.dto.UserDto;
import tcp.microservices.couchbase.persistence.entities.User;
import tcp.microservices.couchbase.persistence.repositories.UserRepository;
import tcp.microservices.couchbase.service.IServiceCouchbaseService;

@Service 
public class ServiceCouchbaseServiceImpl implements IServiceCouchbaseService {  
    
	private static final Logger log = LoggerFactory.getLogger(ServiceCouchbaseServiceImpl.class); 

	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(UserDto couchbaseDto) throws Exception {
		log.debug("ServiceCouchbaseServiceImpl.save {}", couchbaseDto);
		User user=new User();
		user.setId(UUID.randomUUID().toString());
		user.setFirstName(couchbaseDto.getData1());
		user.setLastName(couchbaseDto.getData2());
		log.debug("ServiceCouchbaseServiceImpl.User {}", user);
		userRepository.save(user);
	}

	@Override
	public List<UserDto> findAll() throws Exception {
		log.debug("ServiceCouchbaseServiceImpl.findAll {}");
		List<UserDto> listUser=new ArrayList<UserDto>();
		userRepository.findAll().forEach(e -> {			
			log.info(e.toString());
			UserDto userAux=new UserDto();
			userAux.setData1(e.getFirstName());
			userAux.setData2(e.getLastName());
			userAux.setId(e.getId());
			listUser.add(userAux);
		});
		log.debug("ServiceCouchbaseServiceImpl.findAll. Return {}", listUser);
		return listUser;
	}
} 