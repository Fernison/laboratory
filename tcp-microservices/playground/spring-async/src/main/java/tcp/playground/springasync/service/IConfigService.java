package tcp.playground.springasync.service;

import java.util.concurrent.CompletableFuture;

public interface IConfigService {  
	public  CompletableFuture<Boolean> veryLongMethod();
	
	public  void voidVeryLongMethod();
}  