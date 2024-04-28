#!/bin/sh
mongod --fork --logpath /var/log/mongod.log

sleep 5

apt update
apt install -y curl
pip install -r requirements.txt
cd ./milestone1
mvn package
java -jar target/cse364-project-1.0-SNAPSHOT.jar

