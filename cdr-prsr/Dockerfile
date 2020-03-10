FROM openjdk:8-jre

RUN mkdir -p /opt/cdr

COPY target/weather-metrics-1.0-jar-with-dependencies.jar /app/weather-metrics-1.0-jar-with-dependencies.jar
COPY arso-locations.xml /app/cfg/arso-locations.xml

WORKDIR /app

EXPOSE 9885

CMD java -jar weather-metrics-1.0-jar-with-dependencies.jar