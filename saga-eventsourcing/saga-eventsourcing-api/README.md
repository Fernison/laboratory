## Mejoras en roadmap
	-	Dar la opción de configurar varios eventos para que, si se dan los dos, se dispare un @EventHandlerMethod
	-	En la configuración de la Saga (SagaConfiguration class) que no haya que escribir `getStorage` y `createEventHandlers` que se puedan poner como anotacion en la clase. Por ejemplo: `@SagaConfiguration(storage=KafkaEventStorage.class, eventHandlers=[MyEventHandlers.class, MyEventHandlers2.class, etc. ])`
	
```java
	@Configuration
public class MySagaConfiguration {
	 		
	@SuppressWarnings("rawtypes")
	@Bean
	public Storage getStorage() {
		System.out.println("Starting getStorage ...");
		return new KafkaEventStorage("kafka",0);
	}

	@Bean
	public MyEventHandlers createEventHandlers(){
		System.out.println("Starting createEventHandlers ...");
		return new MyEventHandlers();
	}
}
```
	