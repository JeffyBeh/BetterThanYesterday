# 3. HDFS客户端操作

## 3.1 HDFS客户端环境准备

1. 根据操作系统拷贝对应的编译后的Hadoop jar包到非中文路径

2. 配置HADOOP_HOME环境变量

3. 创建maven工程

4. 添加依赖

   ```xml
   <dependencies>
       <dependency>
           <groupId>org.junit.platform</groupId>
           <artifactId>junit-platform-launcher</artifactId>
           <version>1.6.2</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>org.junit.jupiter</groupId>
           <artifactId>junit-jupiter-engine</artifactId>
           <version>5.6.2</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>org.junit.vintage</groupId>
           <artifactId>junit-vintage-engine</artifactId>
           <version>5.6.2</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>org.apache.logging.log4j</groupId>
           <artifactId>log4j-core</artifactId>
           <version>2.13.2</version>
       </dependency>
       <dependency>
           <groupId>org.apache.hadoop</groupId>
           <artifactId>hadoop-common</artifactId>
           <version>3.2.1</version>
       </dependency>
       <dependency>
           <groupId>org.apache.hadoop</groupId>
           <artifactId>hadoop-client</artifactId>
           <version>3.2.1</version>
       </dependency>
       <dependency>
           <groupId>org.apache.hadoop</groupId>
           <artifactId>hadoop-hdfs</artifactId>
           <version>3.2.1</version>
       </dependency>
   </dependencies>
   ```

5. .../java/main下创建包（pers.jeffy.hdfs）并创建java文件（HDFSClient.java）

6. .../java/resources下创建log4j.properties文件

   ```peoperties
   log4j.rootLogger=INFO, stdout
   log4j.appender.stdout=org.apache.log4j.ConsoleAppender
   log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
   log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m%n
   log4j.appender.logfile=org.apache.log4j.FileAppender
   log4j.appender.logfile.File=target/spring.log
   log4j.appender.logfile.layout=org.apache.log4j.PatternLayout
   log4j.appender.logfile.layout.ConversionPattern=%d %p [%c] - %m%n
   ```

### 3.2 HDFS的API操作

#### 3.2.1 HDFS文件上传（测试参数优先级）

- 编写源码

  ```java
  // 文件上传
  @Test
  public void testCopyFromLocalFile() throws URISyntaxException, IOException, InterruptedException {
  
      // 1.获取fs对象
      Configuration conf = new Configuration();
  	// 设置副本数
      // conf.set("dfs.replication", "2");
      FileSystem fs =  FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");
  
      // 2.上传文件
      fs.copyFromLocalFile(new Path("E:\\testFile.txt"), new Path("/testFile.txt"));
  
      // 3.关闭资源
      fs.close();
  }
  ```

- 将hdfs-site.xml拷贝到项目的根目录下

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
  
  <configuration>
  	<property>
      	<name>dfs.replication</name>
          <value>1</value>
      </property>
  </configuration>
  ```

- 参数优先级

  客户端**代码**中的值 > **ClassPath**下用户自定义的配置文件 > **服务器**上的配置文件 > 默认配置

#### 3.2.2 文件下载

- 源码

  ```java
  // 文件下载
  @Test
  public void testCopyToLocalFile() throws URISyntaxException, InterruptedException, IOException {
  
      // 1.获取fs对象
      Configuration conf = new Configuration();
  
      FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");
  
      // 2.下载文件
      /*
       * @param delSrc 是否删除源文件
       * @param src 文件源
       * @param target 目标
       * @param useRawLocalFileSystem 是否使用本地模式  -- 使用则没有crc校验文件
       */
      fs.copyToLocalFile(false, new Path("/testFile_2.txt"), new Path("C:\\Users\\Administrator\\Desktop"), true);
  
      // 3. 关闭资源
      fs.close();
  }
  ```

#### 3.2.3 文件删除

- 源码

  ```java
  // 文件删除
  @Test
  public void testDelete() throws IOException, URISyntaxException, InterruptedException {
  
      // 1.获取fs对象
      Configuration conf = new Configuration();
      FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");
  
      // 2.删除文件
      boolean result = fs.deleteOnExit(new Path("/0611"));
      System.out.println(result);
      
      // 3.关闭资源
      fs.close();
  }
  ```

#### 3.2.4 文件更名

- 源码

  ```java
  // 文件更名
  @Test
  public void testRename() throws URISyntaxException, IOException, InterruptedException {
  
      // 1.获取fs对象
      Configuration conf = new Configuration();
      FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");
  
      // 2.删除文件
      fs.rename(new Path("/testFile_1.txt"), new Path("/testFile_1.1.txt"));
  
      // 3.关闭资源
      fs.close();
  }
  ```

#### 3.2.5 查看文件详细信息

```java
// 查看文件详细信息 -- 文件名称、权限、长度、块信息
@Test
public void testListFile() throws IOException, URISyntaxException, InterruptedException {

    // 1.获取fs对象
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

    // 2.获取文件详细信息
    /*
     * @param pathString
     * @param recursive
     */
    RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

    while(listFiles.hasNext()){
        LocatedFileStatus fileStatus = listFiles.next();

        // 文件名
        System.out.println(fileStatus.getPath().getName());
        // 文件权限
        System.out.println(fileStatus.getPermission());
        // 文件长度
        System.out.println(fileStatus.getLen());
        // 文件块信息 -- 根据位置信息获取副本存在哪台主机上
        BlockLocation[] blockLocations = fileStatus.getBlockLocations();
        for(BlockLocation blockLocation:blockLocations){

            String[] hosts = blockLocation.getHosts();

            for(String host:hosts){
                System.out.println(host);
            }
        }
        System.out.println("-----------------分割线----------------");
    }

    // 3.关闭资源
    fs.close();
}
```

#### 3.2.6

```java
// 判断是文件还是文件夹
@Test
public void testListStatus() throws IOException, URISyntaxException, InterruptedException {

    // 1.获取fs对象
    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

    // 2.判断操作
    FileStatus[] fileStatuses = fs.listStatus(new Path("/"));

    for(FileStatus fileStatus:fileStatuses){

        if(fileStatus.isFile()){
            // 是否为文件
            System.out.println("f: " + fileStatus.getPath().getName());
        } else if(fileStatus.isDirectory()){
            //是否为文件夹
            System.out.println("d: " + fileStatus.getPath().getName());
        }
    }

    // 3.关闭资源
    fs.close();
}
```

- 结果：

  > f: testFile.txt
  > f: testFile_1.1.txt
  > d: usr
  > f: wc.input

#### 3.3 HDFS的I/O流操作

​	API操作是由HDFS系统框架封装好的，如果我们想自己实现3.2中的API操作则可以采用IO流方式实现数据的上传和下载。

##### 3.3.1 HDFS文件上传

1. 需求：把本地E盘中的xiaoxx.txt上传到HDFS根目录

2. 源码

   ```java
   // 把本地E盘中的testFile.txt上传到HDFS根目录
   @Test
   public void putFileToHDFS() throws URISyntaxException, IOException, InterruptedException {
   
       // 1.获取fs对象
       Configuration conf = new Configuration();
       FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");
   
       // 2.获取输入流
       FileInputStream fis = new FileInputStream(new File("e:/testFile.txt"));
   
       // 3.获取输出流
       FSDataOutputStream fos = fs.create(new Path("/xiaoxxx.txt"));
   
       // 4.流的对拷
       IOUtils.copyBytes(fis,fos,conf);
   
       // 5.关闭资源
       IOUtils.closeStream(fos);
       IOUtils.closeStream(fis);
       fs.close();
   }
   ```

##### 3.3.2 HDFS文件下载

1. 需求：从HDFS上下载xiaoxx.txt文件到本地的e盘

2. 源码

   ```java
   // 从HDFS上下载xiaoxxx.txt文件到本地的e盘
   @Test
   public void getFileFromHDFS() throws IOException, URISyntaxException, InterruptedException {
   
       // 1.获取fs对象
       Configuration conf = new Configuration();
       FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");
   
       // 2.获取输入流
       FSDataInputStream fis = fs.open(new Path("/xiaoxxx.txt"));
   
       // 3.获取输出流
       FileOutputStream fos = new FileOutputStream(new File("e:/xiaoxx.txt"));
   
       // 4.流的对拷
       IOUtils.copyBytes(fis,fos,conf);
   
       // 5.关闭资源
       IOUtils.closeStream(fos);
       IOUtils.closeStream(fis);
       fs.close();
   }
   ```

#### 3.3.3 文件的定位读取

1. 需求：获取HDFS上的某一个文件块

2. 源码：

   ```java
   // 下载第一块
   @Test
   public void readFileSeed1() throws URISyntaxException, IOException, InterruptedException {
   
       // 1.获取对象
       Configuration conf = new Configuration();
       FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");
   
       // 2.获取输入流
       FSDataInputStream fis = fs.open(new Path("/hadoop-3.2.1.tar.gz"));
   
       // 3.获取输出流
       FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-3.2.1.tar.gz.part1"));
   
       // 4.流的对拷 -- 只考128M
       byte[] buf = new byte[1024];
       for (int i = 0; i < 1024 * 128; i++) {
   
           fis.read(buf);
           fos.write(buf);
       }
   
       // 5.关闭资源
       IOUtils.closeStream(fos);
       IOUtils.closeStream(fis);
       fs.close();
   }
   
   // 下载第二块
   @Test
   public void feadFileSeek2() throws IOException, URISyntaxException, InterruptedException {
   
       // 1.获取fs对象
       Configuration conf = new Configuration();
       FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");
   
       // 2.获取输入流
       FSDataInputStream fis = fs.open(new Path("/hadoop-3.2.1.tar.gz"));
   
       // 3.设置读取的起点 -- 128M
       fis.seek(1024*1024*128);
   
       // 4.获取输出流
       FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-3.2.1.tar.gz.part2"));
   
       // 5.流的对拷
       IOUtils.copyBytes(fis,fos, conf);
   
       // 6.关闭资源
       IOUtils.closeStream(fos);
       IOUtils.closeStream(fis);
       fs.close();
   }
   ```

   