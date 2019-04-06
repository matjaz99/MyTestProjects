#!/bin/bash

ID=10

for (( ; ; ))
do
   curl http://localhost:8083/animals/all

   sleep $(( $RANDOM % 2 ))

   curl -X POST -H "Content-Type: application/json" -d '{"name": "Johann", "species": "tiger", "country": "Uganda", "age": 5}' http://localhost:8083/animals/add

   sleep $(( $RANDOM % 3 ))

   curl -X DELETE http://localhost:8083/animals/delete/$ID

   sleep $(( $RANDOM % 2 ))

   curl http://localhost:8083/animals/all

   ((ID++))

   sleep $(( $RANDOM % 8 ))

done
