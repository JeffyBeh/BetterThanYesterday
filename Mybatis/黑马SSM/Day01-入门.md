- ### 1.什么是框架

  - 开发中的一套解决方案，不同的框架解决不同的问题
  - 优点
    - 封装细节，简化开发，提高效率

- ### 2.三层架构

  - 表现层
    - 用于展示数据
  - 业务层
    - 处理业务需求
  - 持久层
    - 和数据库交互

- ### 3.持久层技术解决方案

  - JDBC技术

    ​	Connection

    ​	PreparedStatement

    ​	ResultSet

  - Spring的JdbcTemplate

      ​	Spring中对jdbc的简单封装

  - Apache的DBUtils

    ​	与Spring中的JdbcTemplate类似，也是对Jdbc的简单封装

  - 以上这些都不是框架

    ​	JDBC是规范

    ​	Spring的JdbcTemplate和Apache的DBUtils都只是工具类

- ###  4.mybaits概述

  - mybatis是一个持久层框架，是用java编写

  - 它封装了jdbc操作的很多细节，使开发者只需要关注sql语句本身而无需关注注册驱动、创建连接等繁杂的操作

  - 它本身用了ORM思想实现了结果集的封装

    - ORM -- Object Relational Mapping(对象关系映射)

      ​	把数据库表和实体类及实体类的属性对应起来，让我们可以操作实体类来实现操作数据库
      
      > ​	user	User
      >
      > ​	id	UserId
      >
      > ​	user_name	userName
      
    - 今日目标

      - 实体类中的属性和数据库中的字段名称保持一致

        > ​	user 	user
        >
        > ​	id	id
        >
        > ​	user_name	user_name

### 5.mybatis的入门

- mybatis的环境搭建

  - 第一步：创建maven工程，并导入坐标

  - 第二步：创建实体类和dao的接口

  - 第三步：创建Mybatis的主配置文件

    ​	SqlMapConfig.xml

  - 第四步：创建映射配置文件

    ​	IUserDao.xml

- 环境搭建的注意事项

  1. 创建IUserDao.xml和IUserDao.java是为了和我们之前的知识保持一致，在Mybatis中它把持久层的操作接口名称和映射文件也叫做Mapper，所以和IUserDao 和 IUserMapper是一样的

  2. 在idea中创建目录的时候和包是不一样的

     ​	package在创建时：a.b.c是三级目录

     ​	directory在创建时：a.b.c是一级目录

  3. Mybaits的映射配置文件位置必须和dao接口的包结构相同

  4. 映射配置文件的mapper标签namespace的取值必须是dao接口的全限定类名

  5. 映射配饰文件的操作配置，id属性的取值必须是dao接口的方法名

     
     
     ***当我们遵从了3， 4， 5点之后，我们在开发中就无须再写dao的实现类***
  
  mybatis的入门案例：
  
  1. 读取配置文件  -- 获取生产图纸
  
  2. 创建SqlSessionFactory工厂  -- 创建生产工厂（1. builder   2. factory）
  
  3. 创建SqlSession  -- 创建生产机器
  
  4. 创建Dao接口的代理对象  -- 创建操作人员
  
  5. 执行dao中的方法  -- 进行生产等操作
  
  6. 释放资源  -- 完成收工
  
     **注意：**不要忘记在映射配置中告知mybatis要封装到哪个实体类中
  
     ​			配置的方式：指定实体类的全限定类名
     
     mybatis基于注解的入门案例：
     
     	1. 把IUserDao.xml移除，在dao接口的方法上使用@Select注解，并且指定SQL语句
      	2. 同时需要在SqlMapConfig.xml中的mapper配置时，使用class属性执行dao接口的全限定类名
  
  **明确：**我们在开发中，都是越简单越好，所以都是采用不写dao实现类的方式，不管使用XML还是注解配置。但是mybatis支持dao实现类。




