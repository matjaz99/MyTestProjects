# REST web services with Jersey



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



## library example

http://localhost:8080/restws/rest/library







