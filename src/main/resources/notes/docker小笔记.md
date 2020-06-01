# docker小笔记
## 下载
网上有很多教程，根据自己电脑版本下载，我的电脑是windows10 家庭版，所以是下载了三件套

## 启动
打开 Docker Quickstart Terminal，首先会自动下载一个iso。  
会比较慢，可以自己下载到 C:/Users/{用户名}/.docker/machine/cache 中  
然后就会自动创建一个default容器，自动启动了

## 配置加速器
    -- 进入docker容器的配置界面
    docker-machine ssh default
    -- 切换到配置文件夹
    cd /etc/docker
    -- 编辑配置文件daemon.json 如果没有就创建一个
    vi daemon.json
    
    -- daemon.json的内容，这个地址是网易的加速器，也可以选择阿里或者其他的
    {
      "registry-mirrors":[
        "http://hub-mirror.c.163.com"
      ]
    }
    -- 退出后重启
    exit
    docker-machine restart default
    
    -- 查看 docker 信息， 如果看到配置的加速器网址，则说明配置是成功的，可以试着下载一下镜像
    docker info

## 下载镜像
执行 `docker images` 可以查看已有镜像  
执行 `docker pull nginx:latest` 等，可以下载镜像，`:latest`代表最新版， 默认即为最新版

    -- nginx
    docker pull nginx
    -- redis
    docker pull redis
    -- zookeeper
    docker pull zookeeper
    -- kafka
    docker pull wurstmeister/kafka

## 创建容器
使用镜像创建容器  
执行 `docker ps` ，可以查看启动中的容器  
执行 `docker ps -a` ，可以查看所有容器

### 创建 kafka 容器
kafka 需要在 zookeeper 的基础上创建，因此要先创建 zookeeper 容器

    docker run -itd --name server-zookeeper -p 2181:2181 zookeeper

执行 `docker ps` 查看，zookeeper已经启动成功了

创建kafka容器

    docker run -d --name server-kafka 
    -p 9092:9092 --link server-zookeeper 
    -e KAFKA_ZOOKEEPER_CONNECT=server-zookeeper:2181/kafka
    -e KAFKA_ADVERTISED_HOST_NAME=192.168.99.100
    -e KAFKA_ADVERTISED_PORT=9092
     wurstmeister/kafka:latest

kafka 如果启动一会就停，可能是因为zookeeper没启动，或者 kafka 自身配置的 zookeeper ip 、 port 有错

### 创建 oracle 容器
首先下载镜像，选择的是阿里的镜像，比较大，要耐心等待下

```
docker pull registry.cn-hangzhou.aliyuncs.com/helowin/oracle_11g
```

创建容器
```
docker -itd --name server-oracle -p 1521:1521 registry.cn-hangzhou.aliyuncs.com/helowin/oracle_11g
```

设置用户信息
```
# 进入容器
docker exec -it server-oracle /bin/bash
# 使用root用户
su root
# 输入密码
helowin

# 这两步不用
# 切换到命令的目录
cd /home/oracle/app/oracle/product/11.2.0/dbhome_2
# 创建软连接
ln -s sqlplus /usr/bin


# 切换回oracle
su - oracle
# 连接oracle
sqlplus /nolog
# conn /as sysdba
# 创建用户
create user test identified by test;
# 修改用户密码
alter user test identified by root;
# 设置用户权限
grant connect,resource,dba to test;
```
用Navicat for Oracle连接数据库
```
host:192.168.99.100
port:1521
service name:helowinXDB
username:test
password:root
```

