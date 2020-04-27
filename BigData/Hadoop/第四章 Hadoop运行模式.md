# 4. Hadoop运行模式

### 官方案例: grep

>$ mkdir input
>$ cp etc/hadoop/*.xml input
>$ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar grep input output 'dfs[a-z.]+'
>$ cat output/*

### 官方案例: WordCount

>$ mkdir wcinput
>$ touch wcinput/wc.input
>$ vim wcinput/wc.input(input some words you want)
>$ hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-eample-3.2.1.jar wordcount wcinput wcoutput
>$ cat wcoutput/*



