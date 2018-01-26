# REST web services with Jersey


Run project on Tomcat server.


## Hello example

#### Return plain text:
$ curl -X GET http://localhost:8080/restws/rest/hello

$ curl -X GET http://localhost:8080/restws/rest/hello/mmm

#### Return XML:
$ curl -H "Accept: text/xml" -H "Content-Type: text/xml" -X GET http://localhost:8080/restws/rest/hello

#### Return XML:
$ curl -H "Accept: application/xml" -H "Content-Type: application/xml" -X GET http://localhost:8080/restws/rest/hello

#### Return HTML:
$ curl -H "Accept: text/html" -H "Content-Type: text/html" -X GET http://localhost:8080/restws/rest/hello



## Library service example

Basic example of operations: GET, PUT, DELETE

This example implements the following REST methods.


#### Get all books

HTTP GET request:

http://localhost:8080/restws/rest/library


#### Get single book (by id)

HTTP GET request:

http://localhost:8080/restws/rest/library/2


#### Add new book

HTTP PUT request:

http://localhost:8080/restws/rest/library

With body:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<book>
    <author>Svetlana Makarovich</author>
    <available>true</available>
    <id>4</id>
    <title>Machek Muri</title>
</book>
```

Remark: id is not required in request as it will be overwritten with new id.


#### Delete a book

HTTP DELETE request:

http://localhost:8080/restws/rest/library/2








