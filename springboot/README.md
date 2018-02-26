# Spring boot

#### Hello example

Run main method in HelloExample

Method hello() annotated with @ResponseBody will return plain html.

Go to: http://localhost:8080/hello

http://localhost:8080/hello?name=John

Maven dependencies:
- spring-boot-starter-web


#### Greeting example

This example uses Thymeleaf for creating GUI.

Run main method in GreetingController.

Method greetingForm() is called when GET request is received. The method opens the form template.

Open in web browser:

http://localhost:8080/greeting

Method greeting() is called when POST request is received after submitting the data

Maven dependencies:
- spring-boot-starter-web
- spring-boot-starter-thymeleaf


#### Maven

Building

To build a package that contains all dependencies (jar files), run:

mvn mvn clean install spring-boot:repackage



#### Thymeleaf Eclipse plugin

Copy this:

http://www.thymeleaf.org/eclipse-plugin-update-site/

to download eclipse plugin. It enables to view html files in embedded browser.

