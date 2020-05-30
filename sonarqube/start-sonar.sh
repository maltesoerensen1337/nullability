#!/bin/bash
SQ_DATA=$(pwd)/volumes/data
SQ_EXTENSIONS=$(pwd)/volumes/extensions
mkdir -p $SQ_DATA
mkdir -p $SQ_EXTENSIONS
docker run -d --name sonarqube \
	-v $SQ_DATA:/opt/sonarqube/data \
	-v $SQ_EXTENSIONS:/opt/sonarqube/extensions \
	-p 9000:9000 \
	sonarqube:8.3-community
