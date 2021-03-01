## Hive

### 配置

#### `hive-site.xml`
```xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?> <configuration>
<property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://vm01:3306/metastore?useSSL=false</value>
</property>
<property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.jdbc.Driver</value>
</property>
<property>
    <name>javax.jdo.option.ConnectionUserName</name>
    <value>root</value>
</property>
<property>
    <name>javax.jdo.option.ConnectionPassword</name>
    <value>906700211</value>
</property>
<property>
    <name>hive.metastore.warehouse.dir</name>
    <value>/user/hive/warehouse</value>
</property>
<property>
    <name>hive.metastore.schema.verification</name>
    <value>false</value>
</property>
<property>
    <name>hive.server2.thrift.port</name>
    <value>10000</value>
</property>
<property>
    <name>hive.server2.thrift.bind.host</name>
    <value>vm01</value>
</property>
<property>
    <name>hive.metastore.event.db.notification.api.auth</name>
    <value>false</value>
</property>
<property>
    <name>hive.cli.print.header</name>
    <value>true</value>
</property>
<property>
    <name>hive.cli.print.current.db</name>
    <value>true</value>
</property>
</configuration>
```

### Hive on Spark

#### 新建配置文件 `spark-defaults.conf`
```
spark.master yarn
spark.eventLog.enabled true
spark.eventLog.dir hdfs://vm01:8020/spark-history
spark.executor.memory 1g
spark.driver.memory 1g
```

#### `hive-site.xml`
```xml
<configutation>
<!--Spark 依赖位置(注意:端口号 8020 必须和 nameNode 的端口号一致)-->
<property>
   <name>spark.yarn.jars</name>
   <value>hdfs://vm01:8020/spark-jars/*</value>
</property>
<!--Hive 执行引擎-->
<property>
   <name>hive.execution.engine</name>
   <value>spark</value>
</property>
<!--Hive 和 Spark 连接超时时间-->
<property>
   <name>hive.spark.client.connect.timeout</name>
   <value>10000ms</value>
</property>
<property>
  <name>spark.home</name>
  <value>/opt/module/spark/spark-3.0.0-bin-hadoop3.2</value>
</property>
<property>
   <name>spark.executor.extraClassPath</name>
   <value>/opt/module/hive/lib</value>
   <description>配置spark 用到的hive的jar包</description>
</property>
<property>
   <name>spark.serializer</name>
   <value>org.apache.spark.serializer.KryoSerializer</value>
   <description>配置spark的序列化类</description>
</property>
</configutation>
```

#### `spark-env.sh`
```bash
SPARK_DIST_CLASSPATH=$(/opt/module/hadoop-3.1.3/bin/hadoop classpath)
```