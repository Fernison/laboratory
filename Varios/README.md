# VARIOS

## Docker
-	Los volúmenes se crean para definir espacios en el host que puedan ser reutilizados por distintos contenedores. En Windows no se pueden definir volúmenes que apunten a directorios locales.
-	Crear volumen: `docker volume create myvol`
-	Usar en contenedor: `docker run --name some-mongo --mount source=myvol1,target=/data/db -p 27018:27017 -d mongo`



## MongoDB
-	Arrancar MongoDB en Windows montando un volumen: `docker run --name some-mongo -v //d/temp/datamongo:/data/db -p 27017:27017 -d mongo`

	*OJO: En Windows. Esto monta un volumen en la estructura de ficheros interna que Docker tiene en el host. No apunta a la ruta indicada en la instrucción. Sí hay persistencia pero no se ve reflejada en ese fichero. Si queremos que apunte, el volumen se tiene que montar con "d:/temp/datamongo"*	
	
- Ver "https://hub.docker.com/_/mongo/"
	
## Tomcat

-	Arrancar Tomcat  en Windows montando un volumen: `docker run --name some-tomcat -v d:/temp/data_tomcat:/usr/local/tomcat/webapps -p 8888:8080 -d tomcat`

	Monta la carpeta "d:/temp/data_tomcat" del host Windows en "/usr/local/tomcat/webapps" del contenedor, con lo cual le podemos pasar los War que queremos desplegar sin necesidad de tener que construir la imagen cada vez con un nuevo jar.

-	Ver "https://hub.docker.com/_/tomcat/"

## Jenkins

-	Arrancar Jenkins con volumen: `docker run --name jenkins -d -v jenkins_home:/var/jenkins_home -p 28080:8080 -p 50000:50000 jenkins/jenkins`
-	Credenciales: "admin/2216478644c94aa8bea1de7bb08d5088"

-	Ver "https://hub.docker.com/r/jenkins/jenkins/"



