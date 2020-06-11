#基础镜像为openjdk:8u242-jdk
FROM openjdk:8u242-jdk

#签名 作者信息
MAINTAINER author "1337515006@qq.com"

#创建目录(在镜像上创建)
RUN rm -rf /home/zcjtest/demo*

# 添加jar
ADD ./demo.jar /opt/zcjtest/demo/demo.jar

# 添加自定义文件
COPY /customfile /opt/zcjtest/demo/customfile

# 映射日志目录
VOLUME ["/home/zcjtest/logs/demo","/home/zcjtest/logs/demo"]

# 暴露端口，自定义
EXPOSE 8091

# 默认工作目录
WORKDIR /opt/zcjtest/demo/

# 启动命令
CMD ["java", "-Xms256m", "-Xmx512m", "-Xss256k", "-jar", "demo.jar"]