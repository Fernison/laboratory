package com.ust.sagaeventsourcing.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.sagaeventsourcing.conf.EventAutoConfiguration;
import com.ust.sagaeventsourcing.conf.SagaAutoConfiguration;
import com.ust.sagaeventsourcing.event.EventReactor;
import com.ust.sagaeventsourcing.event.EventSubscribersLoader;


@RunWith(SpringRunner.class)
@Import({SagaAutoConfiguration.class, EventAutoConfiguration.class, TestEventSagaConfiguration.class})
@SpringBootTest(classes = TestApplication.class) // Poniendo esto carga los ficheros de conf
public class TestEventSagaApplication {

	@Autowired
	private TestSagaPart sagaPart;

	@Autowired
	private EventReactor reactor;
		
	@Autowired
	private EventSubscribersLoader subsLoader;
		
	private final static String SUBSCRIBER_LIST=
			"{my saga2={my event2_1={UPDATE KO=EventSubscriberLoaderData [method=operacion2_1bis], "
			+ "UPDATE OK=EventSubscriberLoaderData [method=operacion2_1]}}, "
			+ "my saga1={my event1_1={ALTA OK=EventSubscriberLoaderData [method=operacion1_1]}, "
			+ "my event1_2={ALTA KO=EventSubscriberLoaderData [method=operacion1_2]}}}"; 
		
	@Test
	public void shouldLoadAllEventsSubscribers() throws Exception {
		assertEquals(subsLoader.getSubscribers().toString(), SUBSCRIBER_LIST);
	}
	
	@Before
	public void clearEventLog() {
        FileWriter fwOb;
		try {
			fwOb = new FileWriter("Events.log", false);	
	        PrintWriter pwOb = new PrintWriter(fwOb, false);
	        pwOb.flush();
	        pwOb.close();
	        fwOb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
		
	@Test
	public void shouldCreateSagaAndStoreEventInStorage() throws Exception {
		sagaPart.initiateSaga(new TestSimpleData(1, "nombre"));
		// Read event log file //
		Scanner scanner = new Scanner(Paths.get("Events.log"), StandardCharsets.UTF_8.name());
		String content = scanner.useDelimiter("\\A").next();		
		scanner.close();
		assertThat(content).contains("\"application\" : \"mi app\"");
		assertThat(content).contains("\"saga\" : \"mi saga\"");
		assertThat(content).contains("\"eventName\" : \"event name\"");
		assertThat(content).contains("\"status\" : \"COMMITED\"");
		assertThat(content).contains("\"id_transaccion\" : \"MY ID TRANSACTION\"");
	}

	
	@Test
	public void shouldNotifyNewEventBySagaEventAndStatus() throws Exception {
		ExecutorService threadPool=Executors.newFixedThreadPool(10);
		for(int i=0; i<10; i++) {		    
			threadPool.submit(() -> {
				TestEvent myEvent=new TestEvent("my event1_1", new TestSimpleData(3, "notify_"+(new Random().nextInt(100))));
				myEvent.setStatus("ALTA OK");
				myEvent.setSaga("my saga1");
				myEvent.setApplication("mi application");
				myEvent.setId_transaccion("MY ID TRANSACTION");
				reactor.notifyEvent(myEvent);
		    });
		}
		Thread.sleep(35000);
		Scanner scanner = new Scanner(Paths.get("Events.log"), StandardCharsets.UTF_8.name());
		String content = scanner.useDelimiter("\\A").next();
		int count = StringUtils.countOccurrencesOf(content, "\"id_transaccion\" : \"MY ID TRANSACTION\"");
		scanner.close();
		assertEquals(10, count);
	}
	
	@Test(expected = NoSuchElementException.class) 
	public void shouldNotNotifyNewEventBecauseOfWrongSaga() throws Exception {
		ExecutorService threadPool=Executors.newFixedThreadPool(10);
		for(int i=0; i<10; i++) {		    
			threadPool.submit(() -> {
				TestEvent myEvent=new TestEvent("my event1_1", new TestSimpleData(3, "notify_"+(new Random().nextInt(100))));
				myEvent.setStatus("ALTA OK");
				myEvent.setSaga("my saga not valid");
				myEvent.setApplication("mi application");
				myEvent.setId_transaccion("MY ID TRANSACTION");
				reactor.notifyEvent(myEvent);
		    });
		}
		Thread.sleep(10000);
		Scanner scanner = new Scanner(Paths.get("Events.log"), StandardCharsets.UTF_8.name());
		scanner.useDelimiter("\\A").next();
		scanner.close();
	}
	
	@Test(expected = NoSuchElementException.class) 
	public void shouldNotNotifyNewEventBecauseOfWrongEvent() throws Exception {
		ExecutorService threadPool=Executors.newFixedThreadPool(10);
		for(int i=0; i<10; i++) {		    
			threadPool.submit(() -> {
				TestEvent myEvent=new TestEvent("not valid event", new TestSimpleData(3, "notify_"+(new Random().nextInt(100))));
				myEvent.setStatus("ALTA OK");
				myEvent.setSaga("my saga1");
				myEvent.setApplication("mi application");
				myEvent.setId_transaccion("MY ID TRANSACTION");
				reactor.notifyEvent(myEvent);
		    });
		}
		Thread.sleep(10000);
		Scanner scanner = new Scanner(Paths.get("Events.log"), StandardCharsets.UTF_8.name());
		scanner.useDelimiter("\\A").next();
		scanner.close();
	}
	
	@Test(expected = NoSuchElementException.class) 
	public void shouldNotNotifyNewEventBecauseOfWrongStatus() throws Exception {
		ExecutorService threadPool=Executors.newFixedThreadPool(10);
		for(int i=0; i<10; i++) {		    
			threadPool.submit(() -> {
				TestEvent myEvent=new TestEvent("my event1_1", new TestSimpleData(3, "notify_"+(new Random().nextInt(100))));
				myEvent.setStatus("NOT VALID STATUS");
				myEvent.setSaga("my saga1");
				myEvent.setApplication("mi application");
				myEvent.setId_transaccion("MY ID TRANSACTION");
				reactor.notifyEvent(myEvent);
		    });
		}
		Thread.sleep(10000);
		Scanner scanner = new Scanner(Paths.get("Events.log"), StandardCharsets.UTF_8.name());
		scanner.useDelimiter("\\A").next();
		scanner.close();
	}
	
	@Test
	public void shouldNotifyNewJSONEvent() throws Exception {
		String json="	{\r\n" + 
				"		  \"timestamp\" : 1523286345753,\r\n" + 
				"		  \"application\" : \"mi app2\",\r\n" + 
				"		  \"saga\" : \"my saga1\",\r\n" + 
				"		  \"eventName\" : \"my event1_1\",\r\n" + 
				"		  \"status\" : \"ALTA OK\",\r\n" + 
				"		  \"data\" : {\r\n" + 
				"		    \"id\" : 3,\r\n" + 
				"		    \"name\" : \"notify_86\"\r\n" + 
				"		  },\r\n" + 
				"		  \"id_transaccion\" : \"MY ID TRANSACTION JSON\"\r\n" + 
				"		}";
		
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy HH:mm"));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        objectMapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);		
        TestEvent event = objectMapper.readValue(json, TestEvent.class);
        reactor.notifyEvent(event);
        Thread.sleep(10000);
		// Read event log file //
		Scanner scanner = new Scanner(Paths.get("Events.log"), StandardCharsets.UTF_8.name());
		String content = scanner.useDelimiter("\\A").next();		
		scanner.close();
		assertThat(content).contains("\"id_transaccion\" : \"MY ID TRANSACTION JSON\"");
	}	

}