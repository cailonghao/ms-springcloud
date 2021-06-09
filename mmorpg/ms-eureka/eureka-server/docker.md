cd /usr/local/src/package
docker build -t rpg-eureka-server:1.0 -f /usr/local/src/package/eurekaServer/Dockerfile .
docker run --name=rpg-eureka-server-C0 -itd -p 40000:40000 rpg-eureka-server:1.0

docker logs rpg-eureka-server-C0

docker stop rpg-eureka-server-C0
docker rm rpg-eureka-server-C0
docker rmi rpg-eureka-server:1.0