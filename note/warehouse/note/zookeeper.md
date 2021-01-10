## Zookeeper

### 配置

#### 创建编号
1. 创建文件 `/opt/module/zookeeper-3.5.7/zkData/myid`，注意：编号要唯一。
2. 创建配置文件：`zoo.cfg`;
3. 修改配置信息：
    ```text
   dataDir=/opt/module/zookeeper-3.5.7/zkData
   
    server.1=vm01:2888:3888
    server.2=vm02:2888:3888
    server.3=vm03:2888:3888
    ```
4. 参数解读：`server.A=B:C:D`
    1. A: 表示第几号服务器，即myid中配置的数字；zk启动时读取此文件，拿到数据后与`zoo.cfg`中的配置进行对比从而判断哪个是server。
    2. B: 服务器地址；
    3. C: Follower与集群Leader交换信息的端口；
    4. D: Leader挂掉后，重新选举Leader时服务器相互通信的端口；
   
### 启动
1. ``xcall /opt/module/zookeeper-3.5.7/bin/zkServer.sh start``
2. ``xcall /opt/module/zookeeper-3.5.7/bin/zkServer.sh status`` 