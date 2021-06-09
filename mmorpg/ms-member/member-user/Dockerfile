FROM anapsix/alpine-java:8u201b09_server-jre_nashorn

MAINTAINER 1719341471@qq.com

WORKDIR /usr/local/src

CMD ["java","-jar","rpg-eureka-server.jar"]

EXPOSE 42000
ENV APP_IP 81.71.36.134
ENV APP_HOST ms.rpg-member-user.com
ENV APP_NAME rpg-member-user
ENV APP_PORT 42000

ENV SERVER_USERNAME cainiao
ENV SERVER_PASSWORD 123456
ENV SERVER_HOST ms.rpg-eureka-server.com

ADD memberUser/rpg-member-user.jar rpg-member-user.jar