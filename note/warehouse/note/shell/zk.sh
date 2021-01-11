#!/bin/bash

case $1 in
"start")
  echo "===== 启动 zookeeper 集群 ====="

  for i in vm01 vm02 vm03
  do
    echo "======== start $i ======="
    ssh $i  "/opt/module/zookeeper-3.5.7/bin/zkServer.sh start"
  done
;;

"stop")
  echo "===== 停止 zookeeper 集群 ====="

  for i in vm01 vm02 vm03
  do
    echo "======== stop $i ======="
    ssh $i  "/opt/module/zookeeper-3.5.7/bin/zkServer.sh stop"
  done
;;

"status")
  echo "===== zookeeper 状态 ====="

  for i in vm01 vm02 vm03
  do
    echo "======== status $i ======="
    ssh $i  "/opt/module/zookeeper-3.5.7/bin/zkServer.sh status"
  done
;;
esac