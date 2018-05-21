FROM debian:jessie

ENV JAVA_HOME /jre1.8.0_171
ENV PATH /apache-flume-1.8.0-bin/bin:/jre1.8.0_171/bin:$PATH
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update -qq
RUN apt-get install -q -y --no-install-recommends wget

RUN wget --no-check-certificate -c --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u171-b11/512cd62ec5174c3487ac17c61aaa89e8/jre-8u171-linux-x64.tar.gz
RUN tar -zxvf jre-8u171-linux-x64.tar.gz

RUN wget -c http://archive.apache.org/dist/flume/1.8.0/apache-flume-1.8.0-bin.tar.gz
RUN tar -zxvf apache-flume-1.8.0-bin.tar.gz

ADD ./additional_jars/kafka_2.10-0.9.0.1/libs /apache-flume-1.8.0-bin/lib
ADD ./additional_jars/flume-ingestion/stratio-sinks/stratio-jdbc-sink/target/stratio-jdbc-sink-0.8.0-SNAPSHOT.jar /apache-flume-1.8.0-bin/lib
ADD ./additional_jars/mysql-connector-java-5.1.46/mysql-connector-java-5.1.46-bin.jar /apache-flume-1.8.0-bin/lib
ADD ./additional_jars/jOOQ-3.10.6/jOOQ-lib/jooq-3.10.6.jar /apache-flume-1.8.0-bin/lib
ADD ./config/flume.conf /apache-flume-1.8.0-bin/conf/flume.conf

ADD start-flume.sh /apache-flume-1.8.0-bin/bin/start-flume

ENTRYPOINT [ "start-flume" ]