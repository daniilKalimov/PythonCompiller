#!/bin/bash
./gradlew clean bootJar
docker build -t itransition.com/rpc-server:2.0 .
