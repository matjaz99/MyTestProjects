#!/bin/bash

cd ../hashcode-worker
docker build -t matjaz99/hashcode-worker:1.0 .
docker push matjaz99/hashcode-worker:1.0

cd ../hashcode-manager
docker build -t matjaz99/hashcode-manager:1.0 .
docker push matjaz99/hashcode-manager:1.0

docker stack deploy -c compose-hashcode.yml hc
