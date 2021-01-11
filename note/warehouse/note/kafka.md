## Kafka

### 命令

- 启动：`/opt/module/kafka/bin/kafka-server-start.sh config/server.properties &`

- 停止：`/opt/module/kafka/bin/kafka-server-stop.sh stop`

- 列出所有topic：`/opt/module/kafka/bin/kafka-topics.sh --zookeeper vm01:2181/kafka --list`

- 创建topic：`/opt/module/kafka/bin/kafka-topics.sh --zookeeper vm01:2181,vm02:2181,vm03:2181/kafka --create --replication-factor 3 --partitions 1 --topic first`

- 删除topic：`/opt/module/kafka/bin/kafka-topics.sh --zookeeper vm01:2181/kafka --delete --topic first`

- 发送消息：`/opt/module/kafka/bin/kafka-console-producer.sh --broker-list vm01:9092 --topic first`

- 消费消息：`/opt/module/kafka/bin/kafka-console-consumer.sh --zookeeper vm01:2181/kafka --from-beginning --topic first`
  
- 查看topic：`/opt/module/kafka/bin/kafka-topics.sh --zookeeper vm01:2181/kafka --describe --topic first`
    

### 配置
#### `server.properties` 增加配置

```properties
# 全局唯一
broker.id=0

delete.topic.enable=true

log.dirs=/opt/module/kafka/logs

zookeeper.connect=vm01:2181,vm02:2181,vm03:2181/kafka
```