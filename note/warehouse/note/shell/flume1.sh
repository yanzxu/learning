#! /bin/bash

case $1 in
"start") {
    for i in vm01 vm02 vm03
    do
      echo "======== 启动 $i 采集flume =========="
      ssh $i "nohup /opt/module/flume/bin/flume-ng agent --name a1 --conf-file /opt/module/flume/conf/file_flume_kafka.conf
      -Dflume.root.logger=INFO,LOGFILE 1>/opt/module/flume/log1.txt 2>&1  &"
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
