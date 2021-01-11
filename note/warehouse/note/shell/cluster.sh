#!/bin/bash

case $1 in
"start") {
    echo "================== 启动 集群 =================="

    # 启动 Zookeeper 集群
    zk start

    # 启动 Hadoop 集群
    hdp start

    # 启动 Kafka 采集集群
    kafka start

    # 启动 Flume 采集集群
    flume01 start

    # 启动 Flume 消费集群
    flume03 start
};;

"stop") {
    echo "================== 停止 集群 =================="

    # 停止 Flume 消费集群
    flume03 stop

    # 停止 Flume 采集集群
    flume01 stop

    # 停止 Kafka 采集集群
    kafka stop

    # 停止 Hadoop 集群
    hdp stop

    # 停止 Zookeeper 集群
    zk stop
};;
esac