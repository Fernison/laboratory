FROM openjdk:8-jre-alpine

ENV JAVA_OPTS "-server -Xms256m -Xmx1024m -Xmn256m -Xss2m -Dfile.encoding=UTF-8"
ENV SERVER_PORT 8080
ENV SPRING_PROFILES_ACTIVE ${PROFILE}

EXPOSE ${SERVER_PORT}

VOLUME /tmp

COPY ${project.artifactId}-${packageClassifier}.jar app.jar

RUN sh -c 'touch /app.jar'

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]