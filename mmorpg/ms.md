##安装docker
//安装工具
yum install -y yum-utils   device-mapper-persistent-data   lvm2
//配置安装源
yum-config-manager   --add-repo    https://download.docker.com/linux/centos/docker-ce.repo
//安装docker
yum -y install docker-ce
设置开机启动
systemctl enable docker
ubunto系统
apt-get update
apt-get remove docker docker-engine docker.io containerd runc
apt-get install \
apt-transport-https \
ca-certificates \
curl \
gnupg \
lsb-release
//
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
//
echo \
"deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
$(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
//
apt-get update
apt-get install docker-ce docker-ce-cli containerd.io

apt-cache madison docker-ce
//
https://docs.docker.com/engine/install/ubuntu/





//设置自定义网络
docker network create --driver bridge rpg-bridge
设置微服务的路由
vim /etc/hosts
//添加路由
127.0.0.1  ms.rpg-eureka-server.com
127.0.0.1  ms.rpg-cloud-gateway.com
127.0.0.1  ms.rpg-member-user.com


//-----------------------------------华丽分割线---------------------------------------
//旧版docker需要移除的包
apt-get remove docker docker-engine docker.io containerd runc
//更新apt
apt-get update
//安装依赖
apt-get install \
apt-transport-https \
ca-certificates \
curl \
gnupg \
lsb-release
// Add Docker’s official GPG key:
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
//更新yum源
echo \
"deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
$(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
// 更新
apt-get update
//安装
apt-get install docker-ce docker-ce-cli containerd.io

//------------------安装mysql5.7-------------
下载包
https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-server_5.7.34-1ubuntu16.04_amd64.deb-bundle.tar
//解压
cd /usr/local/src
mkdir mysql5.7
tar -xvf mysql-server_5.7.34-1ubuntu16.04_amd64.deb-bundle.tar -C mysql5.7
cd mysql5.7
// 安装mysql 
apt-get install libaio1
//可选输入
apt-get -f install
dpkg -i mysql-common_5.7.34-1ubuntu16.04_amd64.deb
dpkg -i libmysqlclient20_5.7.34-1ubuntu16.04_amd64.deb
dpkg -i libmysqlclient-dev_5.7.34-1ubuntu16.04_amd64.deb
dpkg -i libmysqld-dev_5.7.34-1ubuntu16.04_amd64.deb
dpkg -i mysql-community-client_5.7.34-1ubuntu16.04_amd64.deb
dpkg -i mysql-client_5.7.34-1ubuntu16.04_amd64.deb

dpkg -i mysql-community-server_5.7.34-1ubuntu16.04_amd64.deb
apt-get -f install
dpkg -i mysql-server_5.7.34-1ubuntu16.04_amd64.deb
dpkg -i mysql-community-source_5.7.34-1ubuntu16.04_amd64.deb
dpkg -i mysql-community-test_5.7.34-1ubuntu16.04_amd64.deb
dpkg -i mysql-testsuite_5.7.34-1ubuntu16.04_amd64.deb

//vim /etc/mysql/mysql.conf.d/mysqld.cnf
添加如下  character_set_server=utf8

查看字符
mysql>show variables like "char%";

开启远程访问
GRANT ALL PRIVILEGES ON *.* TO '$username'@'%' IDENTIFIED BY '$password' WITH GRANT OPTION;
立即生效
FLUSH PRIVILEGES;
//退出mysql
exit  
//-----------------------ubuntu安装ftp-----------------------
apt-get update
apt-get install vsftpd

systemctl start vsftpd
systemctl enable vsftpd
service vsftpd start

//放开防火墙
ufw allow 20/tcp
ufw allow 21/tcp
ufw status

//配置ftp
//备份配置文件
cp /etc/vsftpd.conf /etc/vsftpd.conf.orig
编辑配置文件
vi /etc/vsftpd.conf
//参考配置 https://blog.csdn.net/qq_46175823/article/details/112301897

service vsftpd restart
//
mkdir /var/ftp
useradd -d /home -s/sbin/nologin ftpuser
passwd ftpuser  ftpuserpassword
//授权
chmod 777 /home

chgrp ftpuser /home

chown ftpuser /home

//--------------------------安装nginx--------------------------------
apt-get install nginx
nginx -v
service nginx start
//nginx 配置目录
/usr/sbin/nginx：主程序
/etc/nginx：存放配置文件
/usr/share/nginx：存放静态文件
/var/log/nginx：存放日志

//卸载nginx  
apt-get --purge autoremove nginx
//----------------------------------安装redis----------------
wget http://download.redis.io/releases/redis-5.0.0.tar.gz
tar -xvf redis-5.0.0.tar.gz
cd redis-5.0.0
#编译 
make
&
make test
报错tcl版本过低   apt install tcl

//修改 redis.conf
cd src
//启动
./redis-server  ../redis.conf
//本地连接
./redis-cli -h 127.0.0.1 -p 6379
验证redis
auth redis@hxkj.com
//关闭redis
ps -ef|grep redis
kill - p port
//-------------------------nginx-------------------------------





//-------------------------------------------安装docker Compose -------------------
curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

chmod +x /usr/local/bin/docker-compose

卸载
rm /usr/local/bin/docker-compose

//----------------------部署微服务-------------
cd /usr/local/src/package
docker build -t rpg-eureka-server:1.0 -f /usr/local/src/package/eurekaServer/Dockerfile .
docker run --name=rpg-eureka-server-C0 -itd -p 40000:40000 rpg-eureka-server:1.0

docker logs rpg-eureka-server-C0

docker stop rpg-eureka-server-C0
docker rm rpg-eureka-server-C0
docker rmi rpg-eureka-server:1.0

//docker rm `docker ps -a -q`
//docker rmi `docker images -q`
//docker stop `docker ps -a -q`