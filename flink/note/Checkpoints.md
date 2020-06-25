# Checkpoint & SavePoint
1. [Flink中的状态管理](https://yq.aliyun.com/articles/225623)
1. [Flink的状态管理和检查点机制(https://blog.jrwang.me/2017/flink-state-checkpoint/)



- State: 具体的task/operator的状态；
- Checkpoint：Flink Job在特定时刻的全局快照状态，即包含了所有的state；
- Keyed State：基于KeyedStream上的状态，这个状态是跟特定的key绑定的，对KeyedStream流上的每一个key，可能都对应一个state。。
- Operator State：跟一个特定operator的一个并发实例绑定，整个operator只对应一个state。在一个operator上，可能会有很多个key，从而对应多个keyed state。

### 原始状态和Flink托管状态 (Raw and Managed State)
- Keyed State和Operator State，可以以两种形式存在：原始状态(用户自行管理)和托管状态(Flink框架进行管理)。
- 原始状态：Raw State，用户自行管理；
- 托管状态：ValueState，ListState，MapState，Flink框架进行管理；