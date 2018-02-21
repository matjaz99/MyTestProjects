# Spring boot rest client-server example

Based on examples:

http://g00glen00b.be/producing-rest-apis-with-spring/
https://g00glen00b.be/consuming-rest-apis-with-spring/

[see also](spring-boot-rest-client/README.md)

Run main method in TaskController class.

## Test REST API

#### Get all tasks (GET)

http://localhost:8080/api/tasks

#### Create new task (POST)

http://localhost:8080/api/tasks

with body:

```json
{
  "description": "Testing the new REST API"
}
```

#### Modify task (PUT)

http://localhost:8080/api/tasks/1

with body:

```json
{
  "description": "Testing the new REST API",
  "completed": true
}
```

#### Delete task (DELETE)

http://localhost:8080/api/tasks/1


