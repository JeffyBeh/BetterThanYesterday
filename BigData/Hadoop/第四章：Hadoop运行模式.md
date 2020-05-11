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

#### 4.2.1 配置文件

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

#### 4.2.2 启动集群

- 格式化NameNode（首次启动时格式化，之后不要总格式化）

  > bin/hdfs namenode -format

- 启动NameNode

  > sbin/hadoop-deamon.sh start namenode

- 启动DataNode

  > sbin/hadoop-daemon.sh start datanode

* 访问ip:50070

#### 4.2.3 执行命令

Utilities/Browse the file system

* 创建文件夹

  > bin/hdfs dfs -mkdir -p(递归创建) /usr/jeffy/input

* 查看文件

  > bin/hdfs dfs -ls -R(递归查看)

* 上传本地文件

  > bin/hdfs dfs -put wcinput/wc.input /usr/jeffy/input