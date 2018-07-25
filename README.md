# Springboot_Netty
在SpringBoot的基础上整合Netty实现收发数据，并将数据存储到数据库。

## 功能
对接telnet测试以及其他TCP客户端（如下位机）
## How to use
1. 利用IDE（IDEA）或者 Maven运行 com.todorex.SpringbootNettyApplication
    ```
    $ mvn spring-boot:run
    ```
2. 通过telnet连接服务端
    ```
    $ telnet localhost 9527
    Trying ::1...
    Connected to localhost.
    Escape character is '^]'.
    Your channel is build
    ```
3. 利用telnet发送如下信息(rex这个人22岁了)
    ```
    Trying ::1...
    Connected to localhost.
    Escape character is '^]'.
    Your channel is build
    rex::23
    ```
4. 在服务端收到信息并将rex这个用户插入数据库过后，你将收到如下信息(返回rex用户在数据库的ID)
    ```
    Trying ::1...
    Connected to localhost.
    Escape character is '^]'.
    Your channel is build
    rex::23
    OK, UserId=1
    ```
   
## 参考
1. [netty-spring-example](https://github.com/zbum/netty-spring-example)
