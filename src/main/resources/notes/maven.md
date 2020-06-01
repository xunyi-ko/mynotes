# maven
## 概念：
一个项目管理工具。可以帮助构建工程，管理jar包，编译代码，自动运行单元测试，打包，生成报表部署项目，生成web站点.解决了依赖管理

## 下载安装
配置环境变量

## 命令
mvn clean：删除target目录
mvn compile：编译
mvn test：编译test文件夹下的java文件
mvn package：编译所有文件，并打包
mvn install：完成以上的事外，将项目放在本地仓库

## 导入jar包
创建一个maven-web工程
在pom.xml中添加
```xml
<dependencies>
    <dependency>
        <groupId></groupId>
        <artifactId></artifactId>
        <version></version>
    </dependency>
</dependencies>
```

## 启动项目
在右侧maven生命周期中，输入tomcat:run

## jar包冲突异常
    在dependency标签下添加<scope>provided</scope>表示只在编译时起作用

## 修改运行环境
可以使用如下方式修改配置
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
                <port>80</port>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compile-plugin</artifactId>
            <configuration>
                <target>1.8</target>
                <source>1.8</source>
                <encoding>UTF-8</encoding>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## springboot工程打成启动的jar包
需要添加
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## 打成工具包
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <target>1.8</target>
                <source>1.8</source>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## 将工具包上传到远程服务器
```
1.配置maven
在maven setting.xml中添加
<servers>
    <server>
        <id>releases</id>
        <username>admin</username>
        <password>admin</password>
    </server>
    <server>
        <id>snapshots</id>
        <username>admin</username>
        <password>admin</password>
    </server>
</servers>
2.配置pom文件
<distributionManagement>
    <repository>
        <!--此id要与setting.xml里面server的id对应-->
        <id>releases</id>
        <name>releases Repository</name>
        <url>url</url>
    </repository>
    <snapshotRepository>
        <id>snapshots</id>
        <name>snapshots</name>
        <url>url</url>
    </snapshotRepository>
</distributionManagement>
3.在对应的项目文件夹下输入命令
mvn clean deploy -Dskip.test=true
```

