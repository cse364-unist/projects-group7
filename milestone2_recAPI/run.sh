#!/bin/bash

#start mongodb
mongod &

#Run ‘cd milestone1’
cd milestone1 || exit

# make jacoco:report
#mvn jacoco:report

#Run 'mvn package' command.
mvn package

#Run your code with Java commands.
java -jar ./target/cse364-project-1.0-SNAPSHOT.jar --server.port=8080
