#!/bin/sh
apt update
apt install -y curl

git clone https://github.com/cse364-unist/projects-group7.git
git checkout milestone2
cd faceswap
pip install -r requirements.txt
cd ..
mvn package
mvn test
mvn jacoco:report
java -jar target/cse364-project-1.0-SNAPSHOT.jar
