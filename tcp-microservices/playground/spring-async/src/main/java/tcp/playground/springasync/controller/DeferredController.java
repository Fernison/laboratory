package tcp.playground.springasync.controller;  

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcp.playground.springasync.service.IConfigService; 

@RestController
@RequestMapping("/deferred")
public class DeferredController {  
    
	private static final Logger log = LoggerFactory.getLogger(DeferredController.class); 
	  
	@Autowired
	private IConfigService service;
	
	@GetMapping(path = "/complete")
	public Future<String> get() {
	  CompletableFuture<String> future = new CompletableFuture<>();
	  future.complete("not actually in the background");
	  try {
		Thread.sleep(2000);
	  } catch (InterruptedException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  return future;
	}
	
	@GetMapping(path = "/supply")
	public Future<String> get(@RequestParam String input) {
	  CompletableFuture<String> future = new CompletableFuture<>();
	  return CompletableFuture.supplyAsync(() -> "in the background");
	}
	
	@GetMapping(path = "/chained")
	public String getChained() throws InterruptedException, ExecutionException {
      CompletableFuture<Boolean> boolean1= service.veryLongMethod();
      CompletableFuture<Boolean> boolean2= service.veryLongMethod();
      CompletableFuture<Boolean> boolean3= service.veryLongMethod();
      CompletableFuture.allOf(boolean1,boolean2,boolean3).join();
	  return new String(""+boolean1.get()+""+boolean2.get()+""+boolean3.get());
	}
 } 