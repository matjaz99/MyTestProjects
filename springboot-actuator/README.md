# Springboot actuator metrics (Prometheus)

Example of actuator endpoints. Springboot 1.5 and 2.x have slightly different implementations.

To support prometheus metrics, you need to add micrometer.io libraries (see pom.xml).


The biggest challenge when working with gauges was that once a metric is registered in registry it stays there forever, even if you don't receive this metric at all.
So the registry should be cleared of all gauges (for selected metric) and then re-filled again with fresh metrics.


https://www.baeldung.com/micrometer
https://www.baeldung.com/spring-boot-actuators
https://micrometer.io/docs/ref/spring/1.5
https://github.com/micrometer-metrics/micrometer


http://localhost:8083/animals/all

http://localhost:8083/metrics

http://localhost:8083/prometheus




Maybe you can try prometheus client instead of micrometer:

https://medium.com/@aloksinghal/monitoring-spring-boot-api-response-times-with-prometheus-4101bbb80305


