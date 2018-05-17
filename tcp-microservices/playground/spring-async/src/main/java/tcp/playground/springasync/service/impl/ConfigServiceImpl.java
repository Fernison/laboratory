package tcp.playground.springasync.service.impl;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tcp.playground.springasync.service.IConfigService;

@Service
public class ConfigServiceImpl implements IConfigService {  
    
	@Async
	public  CompletableFuture<Boolean> veryLongMethod()  {
	    long rd=new Random().nextInt(10)*1000;
	    System.out.println("veryLongMethod "+rd);
	    try {
	        Thread.sleep(rd);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Terminado "+rd);
	    return CompletableFuture.completedFuture(true);
	}
	
	@Async
	public void voidVeryLongMethod() {
	    long rd=new Random().nextInt(10)*1000;
	    System.out.println(Thread.currentThread().getName());
	    System.out.println("voidVeryLongMethod "+rd);	    
	    try {
	        Thread.sleep(rd);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    System.out.println("voidVeryLongMethod "+rd);	    
	}
} 