FROM openjdk:8-jre

RUN mkdir -p /app/xml

COPY target/weather-metrics-1.0.jar /app/weather-metrics-1.0.jar

WORKDIR /app

EXPOSE 9885

CMD ["java", "-jar", "weather-metrics*.jar"]