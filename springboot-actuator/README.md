# Springboot actuator metrics (Prometheus)

Example of actuator endpoints. Springboot 1.5 and 2.x have slightly different implementations.

Add dependencies
Add some properties
Add custom metrics

To support prometheus metrics, you need to add micrometer.io libraries (see pom.xml).


https://www.baeldung.com/spring-boot-actuators
https://micrometer.io/docs/ref/spring/1.5



http://localhost:8083/rest/all

http://localhost:8083/metrics

http://localhost:8083/prometheus
