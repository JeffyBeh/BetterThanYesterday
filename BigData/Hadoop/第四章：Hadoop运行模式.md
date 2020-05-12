# 4. Hadoop运行模式

### 4.1 本地模式

#### 4.1.1 官方案例: grep

>$ mkdir input
>$ cp etc/hadoop/*.xml input
>$ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar grep input output 'dfs[a-z.]+'
>$ cat output/*	

#### 4.1.2 官方案例: WordCount

>$ mkdir wcinput
>$ touch wcinput/wc.input
>$ vim wcinput/wc.input(input some words you want)
>$ hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-eample-3.2.1.jar wordcount wcinput wcoutput
>$ cat wcoutput/*

### 4.2 伪分布式模式

#### 4.2.1 启动HDFS并运行MapReduce程序

##### 1. 配置文件

cd /opt/module/hadoop.*/etc/hadoop

* 配置：hadoop-env.sh

  修改JAVA_HOME

- 配置：croe-site.xml

  ```xml
  <!-- 指定HDFS中的NameNode的地址 -->
  <configuration>
      <property>
          <name>fs.defaultFS</name>
          <value>hdfs://hadoop100:9000</value>
      </property>
  
  
  <!-- 指定Hadoop运行时产生文件的存储目录 -->
  	<proterty>
  		<name>hadoop.tmp.dir</name>
     		<value>/opt/module/hadoop-3.2.1/data/tmp</value>
  	</proterty>
  </configuration>
  ```

- 配置： hdfs-site.xml

  ```xml
  <!-- 指定HDFS副本的数量 -->
  <property>
  	<name>dfs.replication</name>
      <value>1</value>
  </property>
  
  <!-- 配置端口（如果没有配置不能访问50070）-->
  <property>
      <name>dfs.http.address</name>
      <value>0.0.0.0:50070</value>
  </property>
  ```

##### 2. 启动集群

- 格式化NameNode（首次启动时格式化，之后不要总格式化）

  > bin/hdfs namenode -format

- 启动NameNode

  > sbin/hadoop-deamon.sh start namenode

- 启动DataNode

  > sbin/hadoop-daemon.sh start datanode

* 访问ip:50070

##### 3. 执行命令

Utilities/Browse the file system

* 创建文件夹

  > bin/hdfs dfs -mkdir -p(递归创建) /usr/bijf/input

* 查看文件

  > bin/hdfs dfs -ls -R(递归查看)

* 上传本地文件 

  > bin/hdfs dfs -put wcinput/wc.input /usr/jeffy/input

* 文件生成在hadoop服务器上

  > hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-eample-3.2.1.jar wordcount /user/bijf/input  /user/bijf/output

* 查看文件内容

  > bin/hdfs dfs -cat /user/bijf/output/part-r-0000

##### 4. 重新格式化注意事项

1. 查看并关闭进程
2. 清除相关文件 -- data、logs
3. 格式化

##### 5. 查看日志

cat logs/hadoop-jeffy-datanode-dbClone0.log

- 其中标签错误等异常都有相关日志

##### 6. 为什么不能总是格式化nameNode

cat data/tmp/dfs/name/current/VERSION

>  clusterID=CID-0b1eef0d-d743-477c-86ec-51ff6e79e5b1

cat data/tmp/dfs/data/current/VERSION

> clusterID=CID-0b1eef0d-d743-477c-86ec-51ff6e79e5b1

nameNode与dateNode的clusterID需要保持一致来进行通讯，如果格式化nameNode那么两个clusterId不同则无法进行通讯导致出现问题

![nameNode格式化](.\res\nameNode格式化.png)

#### 4.2.2 启动YARN并运行MapReduce程序

##### 1. 分析

- 配置集群在YARN上运行
- 启动、测试（功能测试和性能测试）集群增、删、查
- 在YARN上执行WordCount案例

##### 2. 执行步骤

######  2.1 配置集群

https://blog.csdn.net/qq_41515513/article/details/101873098

* -- 配置：yarn-env.sh -- 配置JAVA_HOME

* 配置：yarn-site.xml

  ```xml
  <!-- Reducer 获取数据的方式 -->
  <property>
  	<name>yarn.nodemanager.aux-services</name>
      <value>mapreduce_shuffle</value></value>
  </property>
  
  <!-- 执行YARN的ResourceManager的地址 -->
  <property>
      <name>yarn.resourcemanager.hostname</name>
      <value>dbClone0</value>
  </property>
  ```

* 配置：mapred-env.sh -- 配置JAVA_HOME

* 配置：（对mapred-site.xml.template重命名为）mapred-site.xml

  // mv mapred-site.xml.template mapred-site.xml

  ```xml
  <!-- 指定MR运行在YARN上 -->
  <property>
  	<name>mapreduce.framework.name</name>
      <value>yarn</value>
  </property>
  
  
  <!-- 这是3.2以上版本需要增加配置的，不配置运行mapreduce任务可能会有问题，记得使用自己的路径 -->
  <property>
      <name>mapreduce.application.classpath</name>
      <value>
          /opt/module/hadoop-3.2.1/etc/hadoop,
          /opt/module/hadoop-3.2.1/share/hadoop/common/*,
          /opt/module/hadoop-3.2.1/share/hadoop/common/lib/*,
          /opt/module/hadoop-3.2.1/share/hadoop/hdfs/*,
          /opt/module/hadoop-3.2.1/share/hadoop/hdfs/lib/*,
          /opt/module/hadoop-3.2.1/share/hadoop/mapreduce/*,
          /opt/module/hadoop-3.2.1/share/hadoop/mapreduce/lib/*,
          /opt/module/hadoop-3.2.1/share/hadoop/yarn/*,
          /opt/module/hadoop-3.2.1/share/hadoop/yarn/lib/*
      </value>
  </property>
  ```

###### 2.2 启动集群

- 启动ResourceManager

  > <!-- sbin/yarn-daemon.sh start resourcemanager -->
  >
  > yarn --daemon start resourcemanager

- 启动NodeManager

  ><!-- sbin/yarn-daemon.sh start nodemanager -->
  >
  >yarn --daemon start nodemanager

- 查看mapreduce运行进程

  > ip:8088

#### 4.2.3 配置历史服务器

##### 1. 配置：mapred-site.xml

    <!-- 历史服务端地址 -->
    <property>
        <name>mapreduce.jobhistory.address</name>
        <value>dbClone0:10020</value>
    </property>
    
    <!-- 历史服务器web端地址 -->
    <property>
        <name>mapreduce.jobhistory.webapp.address</name>
        <value>dbClone0:19888</value>
    </property>

##### 2. 启动历史服务器

> <!-- sbin/mr-jobhistory-daemon.sh start historyserver -->
>
> mapred --daemon start historyserver