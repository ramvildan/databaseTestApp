call mvn clean install -Dmaven.test.skip=true

docker-compose -f docker-compose.yaml build
docker-compose -f docker-compose.yaml up --detach