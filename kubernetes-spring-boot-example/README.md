## Ejecución en Kubernetes

	- Interes: 
		- Ejecutar una shell en un pod sin "bash": `sudo kubectl exec srv1-srv2 -i -t -- sh`
		- Para subir imagenes a Docker Hub:
		 	- Se crea un repositorio en Docker Hub con nombre [repo]. Por ejemplo, fernison/service1
		 	- Se construye la imagen: `sudo docker build . -t [nombre_imagen]` [nombre_imagen] coincide con el [repo]
			- En la consola hacer login en Docker Hub: `sudo docker login`
			- Se consulta el Image ID de la imagen  con el comando: `sudo docker images`
			- Se asocia esta imagen al repositorio en Docker Hub: `sudo docker tag [IMAGE ID] [nombre_imagen]`
			- Subir la imagen al repositorio: `sudo docker push [nombre_imagen]`
			- Borrar una imagen por si hay un error: `sudo docker rmi [nombre_imagen]`		
	
	- Escenarios:
		- Los ficheros en la carpeta */home/ubuntu/kubernetes-course/mytests*
		- Escenario 1:
			- 1 pod con dos contendores (uno por servicio)
			- 1 servicio mapeado a servicio 1 
			- Ficheros: *srv1-srv2-pod.yml* y *srv1-srv2-service.yml*
			- Crear pod: `sudo kubectl create -f srv1-srv2-pod.yml`
			- Crear servicio: `sudo kubectl create -f srv1-srv2-service.yml`
			- Probar: `curl http://127.0.0.1:32000/kubernetes-spring-boot-service-1/service1/heinz`
			- Resultado: OK. Al estar los dos contenedores en el mismo pod, comparten el mismo nombre de maquina, en este caso "srv1-srv2", tal y como se especifica en "metadata.name" en el descriptor del pod.
			
		- Escenario 2:
			- 1 pod con dos contendores (uno por servicio)
			- 1 servicio mapeado a servicio 1 
			- Con ConfigMap con variables de entorno			
			- Ficheros: *srv1-srv2-env-pod.yml, srv1-srv2-service.yml*
			[NOTA: NO SE COMO CONFIGURARLO PARA QUE FUNCIONE COGIENDO LAS PROPIEDADES DE UN PROPERTIES (myapp.properties)]
			- Crear ConfigMap: `sudo kubectl create configmap myapp-config --from-literal=myconfig.service2_name=srv1-srv2  --from-literal=myconfig.service2_url=http://srv1-srv2:9090.
			- Consultar el configmap creado: `sudo kubectl get configmap myapp-config -o yaml`
			- Crear pod: `sudo kubectl create -f srv1-srv2-env-pod.yml`. En este pod se hace referencia a las properties como: "myapp.[property]"
			- Crear servicio: `sudo kubectl create -f srv1-srv2-service.yml`
			- Resultado: OK. Coge las properties de las variables de entorno que se han definido: "SERVICE" y "URL".
					
		
	- Necesrio
	
	-

