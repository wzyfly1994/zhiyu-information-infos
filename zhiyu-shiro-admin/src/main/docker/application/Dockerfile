FROM java:8
VOLUME /tmp
ADD *.jar app.jar
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
ADD ./run.sh /tmp
RUN chmod a+x /tmp/run.sh
# JVM虚拟机参数
ENV JAVA_OPTS=""
# 分布式配置中心分组名称
ENV ACM_GROUP=""
# 分布式配置中心端点名称
ENV ACM_END_POINT=""
# 分布式配置中心文件类型
ENV ACM_FILE_EXTENSION="yaml"
# 分布式配置中心RAM角色名称
ENV ACM_RAM_ROLE_NAME=""
# 分布式配置中心命名空间
ENV ACM_NAMESPACE=""
# 应用端口
ENV SERVER_PORT="8099"
ENTRYPOINT [ "sh", "-c", "/tmp/run.sh" ]