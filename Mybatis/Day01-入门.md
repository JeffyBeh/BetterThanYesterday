- ### 什么是框架

  - 开发中的一套解决方案，不同的框架解决不同的问题
  - 优点
    - 封装细节，简化开发，提高效率

- ### 三层架构

  - 表现层
    - 用于展示数据
  - 业务层
    - 处理业务需求
  - 持久层
    - 和数据库交互

- ### 持久层技术解决方案

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

- ###  mybaits概述

  - mybatis是一个持久层框架，是用java编写

  - 它封装了jdbc操作的很多细节，使开发者只需要关注sql语句本身而无需关注注册驱动、创建连接等繁杂的操作

  - 它本身用了ORM思想实现了结果集的封装

    - ORM -- Object Relational Mapping(对象关系映射)

      ​	把数据库表和实体类及实体类的属性对应起来，让我们可以操作实体类来实现操作数据库