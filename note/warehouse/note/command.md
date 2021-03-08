#### Mysql

 - 启动：
    ```bash
    service mysql  restart
    service mysqld  restart
    ```
  
#### Hive
 - 启动：`bin/hive`
 - 启动服务端：`nohup ./bin/hiveserver2 1>/opt/module/hive/log/hiveserver2.log 2>&1  &`


#### yarn
- 查看日志：`yarn logs -applicationId application_1614326267018_0046`