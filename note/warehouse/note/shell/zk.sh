#!/bin/bash

case $1 in
"start")
  for i in vm01 vm02 vm03
  do
    echo "======== start $i ======="
    ssh $i  "/opt/module/zookeeper-3.5.7/bin/zkServer.sh start"
  done
;;

"stop")
  for i in vm01 vm02 vm03
  do
    echo "======== stop $i ======="
    ssh $i  "/opt/module/zookeeper-3.5.7/bin/zkServer.sh stop"
  done
;;

"status")
  for i in vm01 vm02 vm03
  do
    echo "======== status $i ======="
    ssh $i  "/opt/module/zookeeper-3.5.7/bin/zkServer.sh status"
  done
;;
esac