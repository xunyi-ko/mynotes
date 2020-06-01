# docker-compose 搭建 redis 集群
## 前言
在工作的时候，使用了 redis 的脚本功能，据说对集群的支持不是很好，于是想要试一下在集群环境到底怎么用。由于公司电脑没有虚拟机，只有 docker， 于是就开始跟着菜鸟教程准备开始搭建。

## 环境
windows10， docker 三件套

## 正文
### 下载镜像
启动 `docker`，下载 `redis` 最新镜像
```
docker pull redis:latest
```

### 设置共享文件夹
打开 `virtualbox` ，点击设置，共享文件夹，编辑共享文件夹的地址和名字。原来默认的是在 `C:/Users`，我改为了我自己的 `E:/docker/Docker Toolbox` ，名字设为 `docker`

### 准备配置文件
根据教程，从github上复制配置文件 [redis.conf](https://github.com/antirez/redis/blob/unstable/redis.conf) ，并做相应的修改。名字命名从 `redis-6391.conf` 到 `redis-6396.conf`，文件放在 `E:/docker/Docker Toolbox/redis/conf` 下
```
# 由于是在同一台机器上的，所以 port 从 6391 到 6396，cluster-config-file 也要不同的名字
port 6391
cluster-enabled yes
cluster-config-file nodes-6379.conf
cluster-node-timeout 15000
```

### 准备启动脚本
创建 `redis.sh` 文件，内容为
```
redis-server /config/redis-${PORT}.conf
```
也放在 `E:/docker/Docker Toolbox/redis/conf` 文件夹下

### docker-compose.yml
从教程上复制过来，并做一定修改。

```
version: "3.6"
services:
  redis-master1: 
    image: redis # 镜像
    container_name: redis-master1 # 容器名
    working_dir: /config # 设置工作地址，即 docker exec 时，刚进入时的地址
    environment: 
     - PORT=6391 # 设置环境变量
    ports: 
     - "6391:6391" 
     - "16391:16391"
    stdin_open: true
    networks: 
      redis-master:
        ipv4_address: 172.50.0.2
    tty: true
    privileged: true 
    volumes: ["/docker/redis/config:/config"] # 设置共享文件夹，即将宿主机的文件夹与容器的文件夹共享
    entrypoint: # 启动时的执行命令
     - /bin/bash
     - redis.sh
  redis-master2: 
    image: redis 
    container_name: redis-master2 
    working_dir: /config 
    environment: 
     - PORT=6392 
    ports: 
     - "6392:6392" 
     - "16392:16392" 
    stdin_open: true 
    networks: 
      redis-master:
        ipv4_address: 172.50.0.3
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"]
    entrypoint:
     - /bin/bash
     - redis.sh
  redis-master3: 
    image: redis
    container_name: redis-master3
    working_dir: /config
    environment:
     - PORT=6393
    ports:
     - "6393:6393"
     - "16393:16393"
    stdin_open: true
    networks: 
      redis-master:
        ipv4_address: 172.50.0.4
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"] 
    entrypoint:
     - /bin/bash
     - redis.sh
  redis-slave1:
    image: redis
    container_name: redis-slave1
    working_dir: /config
    environment:
     - PORT=6394
    networks:
      redis-slave:
        ipv4_address: 172.30.0.2
    ports:
     - "6394:6394"
     - "16394:16394"
    stdin_open: true
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"]
    entrypoint:
     - /bin/bash
     - redis.sh
  redis-slave2:
    image: redis
    working_dir: /config
    container_name: redis-slave2
    environment:
     - PORT=6395
    ports:
     - "6395:6395"
     - "16395:16395"
    stdin_open: true
    networks:
      redis-slave:
        ipv4_address: 172.30.0.3
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"]
    entrypoint:
     - /bin/bash
     - redis.sh
  redis-slave3:
    image: redis
    container_name: redis-slave3
    working_dir: /config
    environment:
     - PORT=6396
    ports:
     - "6396:6396"
     - "16396:16396"
    stdin_open: true
    networks:
      redis-slave:
        ipv4_address: 172.30.0.4
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"]
    entrypoint:
     - /bin/bash
     - redis.sh
networks:
  redis-master:
    driver: bridge
    ipam:
      driver: default
      config:
       - subnet: 172.50.0.0/16
  redis-slave:
    driver: bridge
    ipam:
      driver: default
      config:
       - subnet: 172.30.0.0/16
```

### 启动
执行 `docker-compose up -d`

注意，可能会碰上以下问题:
1. gbk... 啥的，删除中文即可
2. file or directory is not found， 共享文件夹设置的问题，先再 `virtualbox` 中设置
然后在 `docker-compose.yml` 中写上合适的volumes
3. 属性配置错误，如
```
Reading the configuration file, at line 472
>>>'repl-diskless-load disabled'
Bad directive or wrong number of arguments
```
应该是redis版本比较旧，所以没有这个属性。可以选择用更新的redis，或者注释掉这个属性

docker 会创建六个 redis 容器，接下来去使用 `redis-cli` 创建集群

```
redis-cli --cluster create 192.168.99.100:6391 192.168.99.100:6392 192.168.99.100:6393 192.168.99.100:6394 192.168.99.100:6395 192.168.99.100:6396 --cluster-replicas 1
```

### 使用
接下来用 `redis-cli` 登录使用
```
# redis-cli -c -h 192.168.99.100 -p 6391
192.168.99.100:6391> set test 1
-> Redirected to slot [6918] located at 172.50.0.1:6394
(error) MISCONF Redis is configured to save RDB snapshots, but it is currently not able to persist on disk. Commands that may modify the data set are disabled, because this instance is configured to report errors during writes if RDB snapshotting fails (stop-writes-on-bgsave-error option). Please check the Redis logs for details about the RDB error.
```
提示是写入rdb失败，应该是因为所有的 `redis` 放在同一个文件夹下，导致写的文件也是同一个，所以报错，就把上面的共享文件夹分成六个，各自使用

重复上面的操作
```
# redis-cli -c -h 192.168.99.100 -p 6391
192.168.99.100:6391> set test 1
-> Redirected to slot [6918] located at 172.50.0.1:6392
OK
172.50.0.1:6392> get test
"1"
172.50.0.1:6392> exit
# redis-cli -c -h 192.168.99.100 -p 6394
192.168.99.100:6394> get test
-> Redirected to slot [6918] located at 172.30.0.1:6392
"1"
```
集群部署成功

### java中使用
springboot连接 redis 集群，提示172.50.0.1连接不上。用 cmd ping 也不通

在管理员模式下，打开cmd， 执行
```
route add 172.50.0.0 mask 255.255.255.0 192.168.99.100
route add 172.30.0.0 mask 255.255.255.0 192.168.99.100
```
再次连接，成功

### 测试master宕机
停止 `redis-master1` 容器，再查看集群情况
```
# docker 中执行
docker stop redis-master1
# 进入 redis 容器
docker exec -it redis-master2 /bin/bash
cd /usr/local/bin
redis-cli -c -h 192.168.99.100 -p 6392
info Replication
```

发现不太行，connected_slaves:0，原以为是因为主节点和从节点连接不畅通，但是通过从节点，可以连到主节点。
最终在 `Kitematic` 中找到答案。

打开 `Kitematic` ，选择 `redis-master2`，错误信息
```
6:M 26 May 2020 08:24:56.386 * Replica 172.50.0.1:6396 asks for synchronization
6:M 26 May 2020 08:24:56.386 * Full resync requested by replica 172.50.0.1:6396
6:M 26 May 2020 08:24:56.386 * Starting BGSAVE for SYNC with target: disk
6:M 26 May 2020 08:24:56.387 * Background saving started by pid 36
36:C 26 May 2020 08:24:56.391 * DB saved on disk
36:C 26 May 2020 08:24:56.392 * RDB: 0 MB of memory used by copy-on-write
6:M 26 May 2020 08:24:56.486 * Background saving terminated with success
6:M 26 May 2020 08:24:56.489 * Synchronization with replica 172.50.0.1:6396 succeeded
6:M 26 May 2020 08:24:56.490 # Connection with replica 172.50.0.1:6396 lost.
```
`redis-slave3` 错误信息如下
```
6:S 26 May 2020 07:36:00.610 * Connecting to MASTER 172.30.0.1:6391
6:S 26 May 2020 07:36:00.610 * MASTER <-> REPLICA sync started
6:S 26 May 2020 07:36:00.610 * Non blocking connect for SYNC fired the event.
6:S 26 May 2020 07:36:00.610 * Master replied to PING, replication can continue...
6:S 26 May 2020 07:36:00.611 * Partial resynchronization not possible (no cached master)
6:S 26 May 2020 07:36:00.613 * Full resync from master: bb40547ad681be278142c061fbb6ea4605fcc08f:98
6:S 26 May 2020 07:36:00.709 * MASTER <-> REPLICA sync: receiving 175 bytes from master to disk
6:S 26 May 2020 07:36:00.710 * MASTER <-> REPLICA sync: Flushing old data
6:S 26 May 2020 07:36:00.710 * MASTER <-> REPLICA sync: Loading DB in memory
6:S 26 May 2020 07:36:00.712 # Failed trying to rename the temp DB into dump.rdb in MASTER <-> REPLICA synchronization: Text file busy
```
就字面来看，是文件被占用，导致第一次全量复制时就失败了，之后一直要全量复制，一直失败。

于是重新启动，查看第一次的错误信息
```
6:M 26 May 2020 08:24:29.014 # WARNING: The TCP backlog setting of 511 cannot be enforced because /proc/sys/net/core/somaxconn is set to the lower value of 128.
6:M 26 May 2020 08:24:29.014 # Server initialized
6:M 26 May 2020 08:24:29.014 # WARNING overcommit_memory is set to 0! Background save may fail under low memory condition. To fix this issue add 'vm.overcommit_memory = 1' to /etc/sysctl.conf and then reboot or run the command 'sysctl vm.overcommit_memory=1' for this to take effect.
```
感觉问题可能出在这。面向搜索引擎编程，于是在 `redis.sh` 前加了两行
```
echo 1 > /proc/sys/vm/overcommit_memory
echo 511 > /proc/sys/net/core/somaxconn
```
重启。这两个警告没了，但是错误继续。。。

经过一系列尝试，发现，可能是因为这个共享文件夹的问题。windows系统共享文件夹，导致需要同步到windows中，导致的文件繁忙。将work_dir注释掉再试一次

```
# 删除容器
docker-compose down
# 重新创建容器
docker-compose up -d
# 如上，再次创建集群，同步成功
```

所以最终的配置文件应该是

```
#docker-compose.yml
version: "3"
services:
  redis-master1: 
    image: redis:4.0
    container_name: redis-master1 
    environment: 
     - PORT=6391 
    ports: 
     - "6391:6391" 
     - "16391:16391" 
    stdin_open: true 
    networks: 
      redis-master:
        ipv4_address: 172.50.0.2
    tty: true
    privileged: true 
    volumes: ["/docker/redis/config:/config"]
    entrypoint: 
     - /bin/bash
     - /config/redis.sh
  redis-master2:
    image: redis:4.0
    container_name: redis-master2
    environment:
     - PORT=6392
    networks:
      redis-master:
        ipv4_address: 172.50.0.3
    ports:
     - "6392:6392"
     - "16392:16392"
    stdin_open: true
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"]
    entrypoint:
     - /bin/bash
     - /config/redis.sh
  redis-master3:
    image: redis:4.0
    container_name: redis-master3
    environment:
     - PORT=6393
    networks:
      redis-master:
        ipv4_address: 172.50.0.4
    ports:
     - "6393:6393"
     - "16393:16393"
    stdin_open: true
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"]
    entrypoint:
     - /bin/bash
     - /config/redis.sh
  redis-slave1:
    image: redis:4.0
    container_name: redis-slave1
    environment:
     - PORT=6394
    networks:
      redis-slave:
        ipv4_address: 172.30.0.2
    ports:
     - "6394:6394"
     - "16394:16394"
    stdin_open: true
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"]
    entrypoint:
     - /bin/bash
     - /config/redis.sh
  redis-slave2:
    image: redis:4.0
    container_name: redis-slave2
    environment:
     - PORT=6395
    ports:
     - "6395:6395"
     - "16395:16395"
    stdin_open: true
    networks:
      redis-slave:
        ipv4_address: 172.30.0.3
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"]
    entrypoint:
     - /bin/bash
     - /config/redis.sh
  redis-slave3:
    image: redis:4.0
    container_name: redis-slave3
    environment:
     - PORT=6396
    ports:
     - "6396:6396"
     - "16396:16396"
    stdin_open: true
    networks:
      redis-slave:
        ipv4_address: 172.30.0.4
    tty: true
    privileged: true
    volumes: ["/docker/redis/config:/config"]
    entrypoint:
     - /bin/bash
     - /config/redis.sh
networks:
  redis-master:
    driver: bridge 
    ipam:
      driver: default
      config:
       - subnet: 172.50.0.0/16
  redis-slave:
    driver: bridge
    ipam:
      driver: default
      config:
       - subnet: 172.30.0.0/16
```

windows 中使用，奇奇怪怪的问题真多。。。