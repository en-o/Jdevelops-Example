# docker build -t tannnn/test_project:1.0-SNAPSHOT .
# docker run -d -p 9001:9001  --name pdap -v /home/java/logs:/logs -e ACTIVE="prod" tannnn/test_project:1.0-SNAPSHOT
# docker run -d -p 9001:9001 --name pdap  -v D:\docker\docker-file\贵州中医药\java:/logs -e ACTIVE="prod" tannnn/test_project:1.0-SNAPSHOT
# 基础镜像使用java
# FROM java:8
# https://hub.docker.com/_/openjdk?tab=tags&page=1&ordering=last_updated&name=8
FROM openjdk:8u312-slim-buster
# 作者
MAINTAINER tan


#定义时区参数
ENV TZ=Asia/Shanghai

#设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone


VOLUME /tmp


#    environment:
#      # TODO 根据自己的配置修改，配置项参考源码文件：/xxl-job/xxl-job-admin/src/main/resources/application.properties
#      PARAMS: "--spring.datasource.url=jdbc:mysql://mysql:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai&allowMultiQueries=true&useSSL=false
#               --spring.datasource.username=root
#               --spring.datasource.password=detabespeter
#               --server.servlet.context-path=/job
#               --spring.mail.host=smtp.qq.com
#               --spring.mail.port=25
#               --spring.mail.username=3316459837@qq.com
#               --spring.mail.from=3316459837@qq.com
#               --spring.mail.password=powhyzpqnppqcjbg
#               --xxl.job.accessToken=default_token
#               --msg.dd.ding-ding-robot[0].dingWebhookUrl=https://oapi.dingtalk.com/robot/send?access_token=c86061d249ede49348c2c999cb60d701e8707c69781da31012ad7a0c6b497b39
#               --msg.dd.ding-ding-robot[0].dingSecret=SEC4b8e53f3dc1d68c2ed977c96c2dce3242036828609e24d1363a7c8bf6b597a74"

ENV PARAMS=""ACTIVE
ENV ACTIVE=""

# 将jar 包添加到容器中并更名
ADD ./target/test_project-1.0-SNAPSHOT.jar app.jar

# 暴露端口
EXPOSE 9003

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $ACTIVE $PARAMS"]
