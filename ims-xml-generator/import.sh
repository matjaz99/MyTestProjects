#!/bin/bash

#curl -H "Content-Type: application/json" -XPOST "localhost:9200/pmon/_doc/_bulk?pretty&refresh" --data-binary "@cdr.json"


COUNTER=1
while [  $COUNTER -lt 101 ]; do
	curl -H "Content-Type: application/json" -XPOST "localhost:9200/pmon/_doc/_bulk?pretty&refresh" --data-binary "@cdr$COUNTER.json"
	echo File $COUNTER imported
	let COUNTER=COUNTER+1
done