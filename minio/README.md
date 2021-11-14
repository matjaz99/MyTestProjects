# MinIO

### Documentation

https://docs.min.io/docs/java-client-api-reference.html


### Run Minio in Docker

Before deploying stack, create data directory for minio:

./data/minio


Deploy the stack:

$ docker stack deploy -c compose-minio.yml minio


Minio GUI:

http://promvm:9000/


