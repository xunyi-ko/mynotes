shiro作为安全框架，主要功能是拦截页面。拦截未登录，拦截没有权限的页面
基础使用时，主要使用config和realm两个文件

首先在pom.xml中加入
    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-spring</artifactId>
        <version>1.4.0</version>
    </dependency>
其次，编写自己的ShiroConfig类（配置）
编写自己的ShiroRealm类（认证、获取用户权限）
在需要的情况下，可以自定义拦截器，并加入ShiroConfig的拦截器列表中
