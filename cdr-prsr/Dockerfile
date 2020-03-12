FROM openjdk:8-jre

RUN mkdir -p /opt/cdr

COPY target/cdr-prsr-1.0-jar-with-dependencies.jar /opt/cdr-prsr-1.0-jar-with-dependencies.jar

WORKDIR /opt

CMD java -jar cdr-prsr-1.0-jar-with-dependencies.jar