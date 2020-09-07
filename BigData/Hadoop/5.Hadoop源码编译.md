# 5. Hadoop源码编译

## 5.1 前期准备工作

1. 连接网络
2. jar包准备（hadoop源码、JDK8、maven、ant -- build工具，打包、protobuf -- 序列化的框架）

## 5.2 jar包及编辑工具准备

1. 配置Java

2. 配置maven

   > tar -zxvf appache-maven-3.6.3.bin.tar.gz -C /opt/module
   >
   > cd maven
   >
   > vim conf/setting.xml
   >
   >     <!-- 配置阿里云 -->
   >     <mirrior>
   >         <id>nexus-aliyun</id>
   >         <mirrorOf>central</mirrorOf>
   >         <name>Nexus aliyun</name>
   >         <url>http://maven.aliyun.com/nexus/content/groups/public</url>
   >     </mirror>
   > 配置环境变量
   >
   > vim /etc/profile
   >
   > export MAVEN_HOME=/opt/module/apache-maven-3.6.3
   > export PATH=$PATH:$MAVEN_HOME/bin
   >
   > 
   >
   > 查看
   >
   > mvn -version

3. 配置ant

   > tar zxvf apache-ant-1.10.8-bin.tar.gz -C /opt/module/
   >
   > 
   >
   > 配置环境变量
   >
   > export ANT_HOME=/opt/module/apache-ant-1.10.8
   > export PATH=$PATH:$ANT_HOME/bin
   >
   > 
   >
   > 查看
   >
   > ant -version

4. 安装glibc-headers和g++（centos8已自动安装）

   > yum install glibc-headers
   >
   > yum install gcc-c++

5. 安装make和cmake

   > yum install make(centso8 已自动安装)
   >
   > yum install cmake

6. 配置protobuf

   >tar -zxvf protobuf-all-3.12.0.tar.gz -C /opt/module
   >
   >cd protobuf-3.12.0
   >
   >./configure
   >
   >make
   >
   >make check
   >
   >make install
   >
   >ldconfig
   >
   >
   >
   >配置环境变量
   >
   >vim /etc/profile
   >
   >##LD_LIBRARY_PATH
   >export LD_LIBRATY_PATH=/opt/module/protobuf-3.12.0
   >export PATH=$PATH:$LD_LIBRARY_PATH
   >
   >
   >
   >查看
   >
   >protoc --version

7. 安装openssl库 -- centos8 已自动安装

   > yum install openssl-devel

8. 安装ncurses-devel

   > yum install ncurses-devel

## 5.3 源码编译

1. 解压

   > tar zxvf hadoop-3.2.1-src.tar.gz -C /opt/module

2. 进入主目录

   > cd /opt/module/hadoop-3.2.1-src

3. 通过maven执行编译命令

   > mvn package -Pdist,native -DskipTests -Dtar

4. 成功

   时间较久，最终全部为SUCCESS

5. 编译过程中常见问题及解决办法

   1. MAVEN install 时JVM内存溢出

      处理方式：在环境配置文件中和maven执行文件均可调整MAVEN_OPT的heap大小。（详情见MAVEN编译JVM调优问题）

   2. 编译期间maven报错，可能是网络阻塞问题导致的依赖库下载不完整导致，多次执行命令（一次通过比较难）

      > mvn package -Pdist,native -DskipTests -Dtar

   3. 报ant\protobuf等错误，插件下载不完成或插件版本有问题