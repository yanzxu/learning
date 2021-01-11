#!/bin/bash

if [ $# -lt 1  ]
then
  echo "==== No Args Input ======"
  exit;
fi
case $1 in
"start")
  echo "===== 启动 kafka 集群 ====="

  for i in vm01 vm02 vm03
  do
    echo "============== $i ========="
    ssh $i "/opt/module/kafka/bin/kafka-server-start.sh -daemon /opt/module/kafka/config/server.properties"
  done

  ;;
"stop")
  echo "===== 停止 kafka 集群 ====="

  for i in vm01 vm02 vm03
  do
    ssh $i "/opt/module/kafka/bin/kafka-server-stop.sh stop"
  done
  ;;
*)

  echo "====== Input Args Error ======"
esac