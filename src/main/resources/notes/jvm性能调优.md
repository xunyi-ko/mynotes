# jvm性能调优
记录性能调优工具使用方式及调优时关注的重点。记录我的使用经验

## java visualvm
工具包的位置在jdk/bin中。双击打开

要对其他主机的项目进行监控时，项目启动时需要带有vm参数
```jvm
-Dcom.sun.management.jmxremote.port=20001 // 端口号
-Dcom.sun.management.jmxremote.ssl=false // 是否使用ssl
-Dcom.sun.management.jmxremote.authenticate=false // 是否需要密码
```
启动项目后，打开visualvm，右键点击远程，添加远程主机，输入ip地址。

右键新添加的远程主机，添加JMX连接，输入ip及端口号，确定即可

