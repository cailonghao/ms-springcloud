cd /usr/local/src/package
docker build -t rpg-member-user:1.0 -f /usr/local/src/package/memberUser/Dockerfile .
docker run --name=rpg-member-user-C0 -itd -p 42000:42000 rpg-member-user:1.0

docker logs rpg-member-user-C0

docker stop rpg-member-user-C0
docker rm rpg-member-user-C0
docker rmi rpg-member-user:1.0