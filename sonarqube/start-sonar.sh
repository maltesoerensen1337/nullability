#!/bin/bash
SQ_DATA=$(pwd)/data
mkdir -p $SQ_DATA
docker run -d --name sonarqube \
	-v $SQ_DATA:/opt/sonarqube/data \
	-p 9000:9000 \
	sonarqube:8.3-community
