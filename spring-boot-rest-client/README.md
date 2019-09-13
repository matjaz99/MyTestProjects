# Spring boot rest client-server example - Client side

This example shows how to write a REST client and put a web gui on top of it.
REST client is implemented in Spring Boot and Thymeleaf is used for GUI.


To make a client work, you also need to start the REST server with all the business logic and DB handling:

[Here](../spring-boot-rest-impl/README.md)


The REST server is already running on port 8080, the web server of gui should be set to 8081 to avoid conflicting ports (in application.yml).


Server (REST):

http://localhost:8080/api/tasks

Client server (GUI):

http://localhost:8081/






