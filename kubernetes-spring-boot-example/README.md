## Ejecución en Kubernetes

	- Interes: 
		- Ejecutar una shell en un pod sin "bash": `sudo kubectl exec srv1-srv2 -i -t -- sh`
		- Obtener URl publica de un servicio Kubernetes: `sudo minikube service [NAME_SERVICE] --url`
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
			- Con ConfigMap con variables de entorno ([NOTA: NO SE COMO CONFIGURARLO PARA QUE FUNCIONE COGIENDO LAS PROPIEDADES DE UN PROPERTIES (myapp.properties)])
			- Ficheros: *srv1-srv2-env-pod.yml, srv1-srv2-service.yml*
			- Crear ConfigMap: `sudo kubectl create configmap myapp-config --from-literal=myconfig.service2_name=srv1-srv2  --from-literal=myconfig.service2_url=http://srv1-srv2:9090`.
			- Consultar el configmap creado: `sudo kubectl get configmap myapp-config -o yaml`
			- Crear pod: `sudo kubectl create -f srv1-srv2-env-pod.yml`. En este pod se hace referencia a las properties como: "myapp.[property]"
			- Crear servicio: `sudo kubectl create -f srv1-srv2-service.yml`
			- Probar: `curl http://127.0.0.1:32000/kubernetes-spring-boot-service-1/service1/heinz`
			- Resultado: OK. Coge las properties de las variables de entorno que se han definido: "SERVICE" y "URL".
					
		- Escenario 3:
			- 2 despliegues cada uno con un contenedor (service1 y service2)
			- 2 servicios, uno por despliegue
			- Con ConfigMap con variables de entorno
			- El fichero application.yml acepta las variables de entorno configuradas:

```yml
app:
  version: "@project.version@"
  service2_name: ${SERVICE}
  service2_url: ${URL}
```

			- Ficheros: *service1-app.yml  service2-app.yml*
			- Crear ConfigMap: `sudo kubectl create configmap myapp-config --from-literal=myconfig.service2_name=service2-service --from-literal=myconfig.service2_url=http://service2-service:9090`. En la URL se pone el nombre del servicio			
			- Consultar el configmap creado: `sudo kubectl get configmap myapp-config -o yaml`
			- Crear servicio1: `sudo kubectl create -f service1-app.yml`
			- Crear servicio2: `sudo kubectl create -f service2-app.yml`
			- Probar: `curl http://127.0.0.1:32000/kubernetes-spring-boot-service-1/service1/heinz`
			-  
	
		- Escenario 4:
			- 1 despliegue con un contenedor (service2)
			- 1 servicio
			- 1 Ingress Controller que redirige peticiones al servicio 2
			- Ficheros: *ingress-service2.yml  service2-app.yml echoservice.yml nginx-ingress-controller.yml*
			- Crear servicio2: `sudo kubectl create -f service2-app.yml`
			- Crear echo server: `sudo kubectl create -f echoservice.yml`
			- Se despliega nginx para que haga de LB: `sudo kubectl create -f nginx-ingress-controller.yml`
			- Crear Ingress Controller: `sudo kubectl create -f ingress-service2.yml`
			- Editar "/etc/hosts" añadiendo "[MINIKUBE_IP] test.host".
			- Probar el Service 2: `curl http://test.host/kubernetes-spring-boot-service-2/service2/heinz -vvvv`
			- Resultado: OK. Redirige las peticiones del purto 80 al puerto del contenedor.

