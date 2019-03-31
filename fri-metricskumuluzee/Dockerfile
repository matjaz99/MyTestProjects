FROM openjdk:8u171-jre-alpine


COPY target/*.jar /usr/src/myapp/myapp.jar
WORKDIR /usr/src/myapp

EXPOSE 8091

CMD java -jar myapp.jar
