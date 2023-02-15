# Read Me First

docker build -t charge/mendes .


docker run  -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=local" charge/mendes


http://ec2-15-188-64-151.eu-west-3.compute.amazonaws.com/api/
