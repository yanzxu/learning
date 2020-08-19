# Spark

### Spark核心组件

#### Driver

​	Spark驱动器节点，用于执行Spark任务中的main方法，负责实际代码的执行工作。Driver在Spark作业执行时主要负责：

1. 将用户程序转换为job；
2. 在Executor之间调度task；
3. 跟踪Executor的执行情况；
4. 通过UI展示查询运行情况；

#### Executor

​	Spark Executor是集群中worker节点中的一个JVM进程，负责Spark job中运行具体的task，task彼此之间相互独立。Spark应用程序启动时，Executor节点被同时启动，并且始终伴随着整个Spark应用的生命周期而存在。如果有Executor节点发生了故障或崩溃，spark应用也可以继续执行，会将出错节点上的任务调度到其他Executor节点上继续运行。

​	Executor有两个核心功能：

1. 负责运行组成spark应用的task，并将结果返回给Driver进程；
2. 他们通过自身的块管理器(Block Manager)为用户程序中要求缓存的RDD提供内存式存储。RDD是直接缓存在Executor进程内的，因此任务可以在运行时充分利用缓存数据加速计算。

#### Master & Worker

​	Spark集群的独立部署环境中，不需要依赖其他的资源调度框架，自身就实现了资源调度功能，所以环境中还有其他两个核心组件：Master 和Worker，这里的Master是一个进程，主要负责资源的调度和分配，并进行集群的监控等职责，类似于yarn中的RM，而worker也是进程，一个worker运行在集群中的一台服务器上，有Master分配资源对数据进行并行的处理和计算，类似于yarn的NM；

#### ApplicationMaster

​	Hadoop用户向yarn集群提交应用程序时，提交程序中应包含ApplicationMaster，用于向资源调度器申请执行任务的container，运行用户自己的程序任务job，监控整个任务的执行，跟踪整个任务的状态，处理任务失败等异常情况。

​	简而言之，RM(资源)和Dirver(计算)之间的解耦靠的就是AM；



### 核心概念

#### Executor 与 Core

​	Spark Executor是集群中worker节点的一个进程，主要用于计算。在提交应用中，可以提供指定计算节点的个数，以及对应的资源。这里的资源一般指的是worker节点Executor的内存大小和使用的虚拟CPU核数。

####  DAG

​	DAG主要分为两种，Job间的有向无环(Tez) 和 Job内部的有向无环(Spark)。



### 任务提交流程

​	![image-20200819103048307](/Users/yzxu/Library/Application Support/typora-user-images/image-20200819103048307.png)



### Spark核心编程

​	Spark计算框架为了能够进行高并发和高吞吐的数据处理，封装了三种数据结构，用于处理不同的应用情景，三大数据结构：

1. RDD：弹性分布式数据集
2. 累加器：分布式共享只写变量
3. 广播变量：分布式共享只读变量

#### RDD

##### 什么是RDD

​	RDD：弹性分布式数据集，是spark中最基本的数据处理模型。代码中是一个抽象类，它代表一个弹性的、不可变、可分区、里面的元素可并行计算的集合。RDD存储的计算逻辑不可变。

- 弹性：
   - 存储的弹性：内存与磁盘自动切换；
   - 容错的弹性：数据丢失可以自动恢复；
   - 计算的弹性：计算出错重试机制；
   - 分片的弹性：可根据需要重新分片；
- 分布式：数据存储在大数据集群不同节点上
- 数据集：RDD封装了计算逻辑，并不保存数据
- 数据抽象：RDD是一个抽象类，需要子类具体实现
- 不可变：RDD封装了计算逻辑，是不可改变的，想要改变，只能产生新的RDD，在新的RDD里面封装计算逻辑。

##### 