## ODS（用户行为数据）

### 创建日志表：ods_log
1. 创建支持lzo压缩的分区表
```shell script
create external table ods_log ('line' string)
partitioned by ('dt' string) -- 按照时间创建分区
stored as -- 指定存储方式读取数据采用LzoTextInputFormat; 
  inputformat 'com.hadoop.mapred.DeprecatedLzoTextInputFormat' 
  outputformat 'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
location '/warehouse/gmall/ods/ods_log'
```

2. 加载数据
```shell script
load data inpath '/origin_data/gmall/log/topic_log/2020-06-14'
input table ods_log partition(dt=2020-06-14);
```

3. 查看是否加载成功：`select * from ods_log limit 2;`

4. 为lzo压缩文件创建索引
```shell script
hadoop jar /opt/module/hadoop-3.1.3/share/hadoop/common/hadoop-lzo-0.4.20.jar 
com.hadoop.compression.lzo.DistributedLzoIndexer -Dmapreduce.job.queuename=hive 
/warehouse/gmall/ods/ods_log/dt=2020-06-14
```