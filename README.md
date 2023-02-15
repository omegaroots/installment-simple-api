# Project Setup


Just build the project using gradle build or ./gradlew build. This project is using java 17 and spring boot, so if
using gradle locally make sure it is 7.4+.

# Running Locally

## Using docker
docker run -d -p  80:8080 -e "SPRING_PROFILES_ACTIVE=local" docker.io/mrmendes/charge-api:latest

## Using Intellij
Remember to add the spring profile to local

#Production

It was deployed into an EC2 machine through dockerhub, os inside the EC2 use the command:

docker run -p 8080:8080 -e charge/mendes

To access it on the EC2 instance:
http://ec2-15-188-64-151.eu-west-3.compute.amazonaws.com/api/
Ex:
http://ec2-15-188-64-151.eu-west-3.compute.amazonaws.com/api/installment/1

#Endpoints
GET::/api/installment/{id}
POST::/api/installment

# TO-DO
* Implement a spring-security or any other token based authorization (like oauth, etc)
  * Implement a pipeline to deploy the application through any CI/CD Tool (Jenkins, github action, etc)