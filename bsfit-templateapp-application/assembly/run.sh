#!/bin/sh
#set jvm heap size
JVM_ARGS="-Xss4M -Xms1024M -Xmx4096M -XX:MetaspaceSize=128M -XX:MaxMetaspaceSize=512M"
#设置虚拟机
JVM_ARGS=${JVM_ARGS}" -XX:+UseG1GC"
#打印GC日志
JVM_ARGS=${JVM_ARGS}" -XX:+PrintGCDetails -Xloggc:jvm.log -XX:+PrintGCDateStamps "

## 设置临时目录
TMP_DIR=./tmp
if [ ! -d $TMP_DIR ];then
  mkdir $TMP_DIR
else
  echo tmp dir $TMP_DIR exist
fi
TMP_DIR_ARG=" -Djava.io.tmpdir="${TMP_DIR}

#####  开启jmx，方便使用JVisualVM监控系统
# -Dcom.sun.management.jmxremote.port=1099    这个是配置远程 connection 的端口号的，要确定这个端口没有被占用
# -Dcom.sun.management.jmxremote.ssl=false    指定了 JMX 是否启用 ssl
# -Dcom.sun.management.jmxremote.authenticate=false  指定了JMX 是否启用鉴权（需要用户名，密码鉴权）
# -Djava.rmi.server.hostname=192.168.1.54  这个是配置 server 的 IP 的
#JVM_ARGS=${JVM_ARGS}" -Djava.rmi.server.hostname=192.168.1.54 -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"

### remote debug 开启远程调试
## -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005
#JVM_ARGS=${JVM_ARGS}" -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 "

# 获取启动jar包
JAR_NAME=`ls | grep jar`
path=$(cd "$(dirname "$0")"; pwd)
cd $path

nohup java ${JVM_ARGS} ${TMP_DIR_ARG} -jar ${JAR_NAME} > /dev/null 2>error.log &

echo $! > tpid
echo Start Success!
