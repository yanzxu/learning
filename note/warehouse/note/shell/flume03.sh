#! /bin/bash

case $1 in
"start") {
for i in vm03
do
    echo " --------启动 $i 消费 flume-------"
    ssh $i "nohup /opt/module/flume/bin/flume-ng agent --conf-file /opt/module/flume/conf/kafka_flume_hdfs.conf --name a1 -Dflume.root.logger=INFO,LOGFILE 1>/opt/module/flume/log2.txt 2>&1  &"
done
};;

"stop") {
for i in vm03
do
    echo " --------停止 $i 消费 flume-------"
    ssh $i "ps -ef | grep kafka_flume_hdfs | grep -v grep | awk '{print \$2}' | xargs -n1 kill"
done
};;
esac