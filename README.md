# laboratory

## Execution

### SpringBoot application

	- `mvn -pl sorting-service spring-boot:run`
	
### Docker

	- Crear la imagen: `sudo docker build -t fernison/sort-srv .` 
		- `docker build -t fernison/sort-srv -f target/docker/Dockerfile .` si no lo hacemos desde la ruta donde esta el DockerFile.
	- Ejecutar la imagen: `sudo docker run -d --name sort-srv -p 18008:8080 fernison/sort-srv`, donde *18008* es el puerto local y *8080* es el del contenedor.
	- Probar: 
		- Añadir ordenación: `curl -H "Content-Type: application/json" -X POST -d "[2,4,3,6,1]" http://localhost:18008/sorting-service/mergesort`
		- Consultar una ordenación: `curl http://localhost:18008/sorting-service/mergesort/executions/65b14099-779f-443d-bae9-0b7f7c27694d` (el id)
		- Consultar todas las ordenaciones: `curl http://localhost:18008/sorting-service/mergesort/executions`
	
## Code analysis
### FindBug as Maven Plugin

	- Parece que no tiene mucha evolución
	- `mvn site`
	- Consultar: "/target/site/findbugs.html"
	
### FindBug as Maven Plugin

	- `mvn site`
	- Consultar: "/target/site/pmd.html"

### Jacoco as Maven Plugin

	- Se ejecuta al ejecutar los test
	- `mvn clean package`
	- Consultar: /target/site/index.html
	
## REST API Documentation

	- Usando el plugin Maven de Swagger2
	- Para verlo en formato JSON, "http://localhost:18008/sorting-service/v2/api-docs"
	- Para verlo en SwagerUI, "http://localhost:18008/sorting-service/swagger-ui.html"

