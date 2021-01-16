## Flume

### 命令

```bash
# 启动命令，nohub：该命令可以在退出账户或关闭终端后继续运行相应的进程，即不挂起，不挂断的运行命令。
nohup /opt/module/flume/bin/flume-ng agent --name a1 --conf-file /opt/module/flume/conf/file_flume_kafka.conf
```

### 启动脚本
```shell script
#! /bin/bash

case $1 in 
"start") {
    for i in vm01 vm02 vm03
    do
      echo "======== 启动 $i 采集flume =========="
      ssh $i "nohup /opt/module/flume/bin/flume-ng agent --name a1 --conf-file /opt/module/flume/conf/file_flume_kafka.conf
-Dflume.root.logger=INFO,LOGFILE 1>/opt/module/flume/log1.txt 2>&1 &"
    done
};;

"stop") {
    for i in vm01 vm02 vm03
    do
        echo "======== 停止 $i 采集flume =========="
        ssh $i "ps -ef | grep file_flume_kafka | grep -v grep | awk '{print \$2}' | xargs -n1 kill -9"
    done
};;
esac



# nohup：该命令可以在退出账户或关闭终端后继续运行相应的进程，即不挂起，不挂断的运行命令。
# awk: 默认分隔符为空格。
# xargs: 表示取出前面命令运行的结果，作为后面命令的输入参数。

```

### 配置

#### `flume-env.sh`

```bash
export JAVA_HOME=/usr/local/java
```

#### `file_flume_kafka.conf`

```text
# 各组件名
a1.sources = r1
a1.channels = c1

# 描述source
a1.sources.r1.type = TAILDIR
a1.sources.r1.filegroups = f1
a1.sources.r1.filegroups.f1 = /opt/module/applog/log/app.*
a1.sources.r1.positionFile = /opt/module/flume/taildir_position.json
a1.sources.r1.interceptors = i1
a1.sources.r1.interceptors.i1.type =  com.yzxu.flume.interceptor.ETLInterceptor$Builder

# 描述Channel
a1.channels.c1.type = org.apache.flume.channel.kafka.KafkaChannel
a1.channels.c1.kafka.bootstrap.servers = vm01:9092,vm02:9092,vm03:9092
a1.channels.c1.kafka.topic = topic_log
a1.channels.c1.parseAsFlumeEvent = false

# 绑定source和channel以及sink和channel的关系
a1.sources.r1.channels = c1
```

#### channel选择
   
   - file Channel：基于磁盘，可靠性高、性能差；
   - memory Channel：基于内存，可靠性差，性能高；
   - kafka Channel：数据存在kafka中，基于磁盘，可靠性高，性能高（kafka Channel > memory channel + kafka sink）;
