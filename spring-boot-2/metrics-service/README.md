# Ejemplo de integración Spring (con AOP y SpEL) con InfluxDB y visualización en Grafana

## Descripción

	Aplicación que publica 2 endpoints anotados con anotaciones que almacenan información de forma asíncrona en InfluxDB. 
	A cada anotación se le pasan, como mínimo:
	-	Measure de Influx.
	-	Tag. Opcional
	-	Campos. Se obtienen el objeto de la petición RequestData a partir de sus atributos. Opcional
	-	Operaciones. Operaciones con los parámetros del objeto de la petición. Opcional 
	Por ejemplo, '@Measurable(measurement="mymeas",
			operations={"avg=(value2 + value3)/2", "avg2=(value2*3 + value3*3)/2"})'
		envíaría la siguiente petición a InfluxDB: 'mymeas avg=xxxx,avg2=yyyy', donde "xxxx" y "yyyy" dependen de los valores de los atributos del objeto RequestData

## Compilar

	-	`mvn clean package -DskipTests=true`
	
## Docker-compose	

	-	`docker-compose up -d --build`

## Docker

### InfluxDB

	-	Arrancar: `docker run --name=influxdb -v influxdb:/var/lib/influxd -d -p 8086:8086 influxdb`
	- 	Entrar en el CLI de InfluxDB:
		-	(Desde PowerShell)`docker exec -it influxdb influx`
	-	Ver las BBDDs:
		-	`show databases`
	-	Crear una BBDD:
		-	`create database "My_Database"`
	-	Crear una BBDD:
		-	`use "My_Database"`
	- 	Escribir una métrica: `curl -i -XPOST "http://localhost:8086/write?db=My_Database" --data-binary 'mymeas,mytag=2 myfield=215'`

### Grafana

	-	Arrancar: `docker run -d --name=grafana -p 3000:3000 grafana/grafana`
	-	Entrar: 'http://localhost:3000'
	-	Credenciales: 'admin/admin'. Si no, probad 'admin/secreta'.
	
