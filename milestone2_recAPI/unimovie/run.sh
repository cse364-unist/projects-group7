#!/bin/sh
git clone https://github.com/cse364-unist/projects-group7.git
git checkout milestone2


apt update
apt install -y curl

mongod &

cd milestone2/api/faceswap
pip install -r requirements.txt
cd ..
cd ..
mvn package

mongod --fork --logpath /var/log/mongodb.log

mvn test
mvn jacoco:report
java -jar target/cse364-project-1.0-SNAPSHOT.jar

