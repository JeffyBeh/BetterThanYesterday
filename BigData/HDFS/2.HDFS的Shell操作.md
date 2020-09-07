# 2. HDFS的Shell操作

#### 1. 基本语法

bin/hadoop fs 基本命令	OR	bin/hdfs dfs 基本命令

dfs是fs的实现类

#### 2. 命令

bin/hadoop fs

bin/hdfs dfs

#### 3. 常用命令

0. 启动Hadoop集群
    $ sbin/start-dfs.sh
    $ sbin/start-yarn.sh
    
1. -help：帮助

   $ hadoop fs -help rm

2. -ls：显示目录信息

   $ hadoop fs -ls /

   $ hadoop fs -ls -R(递归) /   #

3. -mkdir：创建文件夹

   $ hadoop fs -mkdir -p(递归)  /sanguo/shuguo

4. -copyFromLocal, -moveFromLocal：复制/剪切本地文件粘贴到HDFS(存在则报错)

   $ touch zhao.txt | echo zhaozilong >> zhao.txt

   $ hadoop fs -moveFromLocal /sanguo/shuguo

5. -appendToFile：将文件追加到已存在的文件末尾（HDFS不支持修改）

   $ touch zhao.txt | echo  zhaoyun >> zhao.txt

   $ hadoop fs -appendToFile ./zhao.txt /sanguo/shuguo/zhao.txt

6. -cat：查看文件内容

   $ hadoop fs -cat /sanguo/shuguo/zhao.txt

7. -chgrp, -chown, -chmod：修改权限

   $ hadoop fs -chown :jeffy /sanguo/shuguo/zhao.txt

8. -copyToLocal：复制HDFS文件到本地(存在则报错)

   $  hadoop fs -appendToFile /sanguo/shuguo/zhao.txt ./

9. -cp：HDFS内部复制

   $ hadoop fs -cp /sanguo/shuguo/zhao.txt /sanguo

10. -mv：HDFS内部剪切

    $ hadoop fs -mv /sanguo/shuguo/zhao.txt /

11. -get：等同于-copyToLocal

    $ hadoop fs -get /sanguo/shuguo/shao.txt ./

12. -getmerge：合并下载多个文件，如HDFS目录/xxx/下有多个文件log1、log2...

    $ hadoop fs -getmerge /sanguo/shuguo/\* ./wuhujiang.txt

13. -put：等同于-copyFromLocal

    $ hadoop fs -put ./License.txt /sanguo/shuguo

14. -tail：查看文件末尾

    $ hadoop fs -tail /sanguo/shuguo/zhao.txt

    $ hadoop fs -tail -f /sanguo/shuguo/zhao.txt

15. -rm：删除文件/文件夹

    $ hadoop fs -rm /sanguo/zhao.txt

16. -rmdir：删除空目录

    $ hadoop fs -mkdir /sanguo/shuguo/jingzhou

    $ hadoop fs -rmdir /sanguo/shuguo/jingzhou

17. -du：统计文件大小信息

    $ hadoop fs -du -h(优化单位) -s(目录总计--不加则分开统计子目录/文件) /

18. -setrep：（repclication）设置副本数

    $ hadoop fs -setrep 

