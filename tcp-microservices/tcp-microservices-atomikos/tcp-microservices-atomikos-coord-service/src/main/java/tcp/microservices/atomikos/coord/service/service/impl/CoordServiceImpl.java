package tcp.microservices.atomikos.coord.service.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.atomikos.icatch.tcc.rest.MimeTypes;
import com.atomikos.icatch.tcc.rest.TransactionProvider;
import com.atomikos.tcc.rest.ParticipantLink;
import com.atomikos.tcc.rest.Transaction;

import tcp.microservices.atomikos.coord.service.service.ICoordService;

@Service 
public class CoordServiceImpl implements ICoordService {  
    
	private static final Logger log = LoggerFactory.getLogger(CoordServiceImpl.class); 

	private static List coordinatorProviders = new ArrayList();

	static {
		coordinatorProviders.add(new JacksonJsonProvider());
		coordinatorProviders.add(new TransactionProvider());
	}
	
    @Value("${app.service1.url}")     
    private String service1Url;   
    
    @Value("${app.service2.url}")     
    private String service2Url;   
    
	@Autowired     
    private RestTemplate restTemplate;    	
	
	@Value("${app.coord.server.url}") 
	private String COORDINATOR_BASE_URL;

	private Transaction transaction;

	@Override
	public String coordinate(Optional<String> param) throws Exception {
		Map<String, String> params=null;
		HttpHeaders headers = null;   
    	HttpEntity<?> httpEntity = null; 
    	final ResponseEntity<String> result1;
    	final ResponseEntity<String> result2;
		transaction = new Transaction();
    	ParticipantLink participantLink1 =null;
    	ParticipantLink participantLink2 =null;
		try {
			// Llamada al primer MS //
			// Crear participant link //
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, 40);
			participantLink1 = new ParticipantLink(service1Url+"/service1?param=" + param.get(), calendar.getTimeInMillis());
	    	String url=null;
	    	if (param.isPresent()) {             
	    		url = UriComponentsBuilder.fromUriString(service1Url+"/service1").
	    				queryParam("param", param.get()).build().encode().toString();         
	    	}  
	    	params = new HashMap<>();         
	    	if (param.isPresent()) params.put("param", param.get()); 
	    	headers = new HttpHeaders();   
	    	httpEntity = new HttpEntity<>(headers);          
	    	result1=restTemplate.exchange(url, HttpMethod.GET,
	        				httpEntity, String.class, params); 
	    	
	        //log.info("[RESULT Service1]:{}", result1);
			transaction.getParticipantLinks().add(participantLink1);
    	} catch(Exception e) {
    		// Llamar a Cancel //    		
    		cancel();
    		throw e;
    	}
    	try {
    		// Llamada al segundo MS //
			// Crear participant link //
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, 40);
			participantLink2 = new ParticipantLink(service2Url+"/service2?param=" + param.get(), calendar.getTimeInMillis());    		
	    	String url=null;
	    	if (param.isPresent()) {             
	    		url = UriComponentsBuilder.fromUriString(service2Url+"/service2").
	    				queryParam("param", param.get()).build().encode().toString();         
	    	}  
	    	params.clear();
	    	if (param.isPresent()) params.put("param", param.get()); 
	    	headers = new HttpHeaders();   
	    	httpEntity = new HttpEntity<>(headers);          
	    	result2=restTemplate.exchange(url, HttpMethod.GET,
	        				httpEntity, String.class, params); 	
	    	
	        //log.info("[RESULT Service2]:{}", result2);       
			transaction.getParticipantLinks().add(participantLink2);
		} catch(Exception e) {
			// Llamar a Cancel //
			cancel();			
			throw e;
		}    	
    	confirm();
        return result1.getBody()+":::"+result2.getBody();  
    }
	
	private void confirm() {
        //log.info("CONFIRMAR");       
    	// Confirmar //
		WebClient coordinator = WebClient.create(COORDINATOR_BASE_URL, coordinatorProviders);
		Response resp = coordinator.path("/coordinator/confirm").
				accept(MimeTypes.MIME_TYPE_COORDINATOR_JSON).
				type(MimeTypes.MIME_TYPE_COORDINATOR_JSON)
				.put(transaction);
        //log.info("FIN CONFIRMAR");       		
	}
	
	private void cancel() {
        //log.info("CANCELAR");       
    	// Cancelar //
		WebClient coordinator = WebClient.create(COORDINATOR_BASE_URL, coordinatorProviders);
		Response resp = coordinator.path("/coordinator/cancel").
				accept(MimeTypes.MIME_TYPE_COORDINATOR_JSON).
				type(MimeTypes.MIME_TYPE_COORDINATOR_JSON)
				.put(transaction);
        //log.info("FIN CANCELAR");       				
	}

} 