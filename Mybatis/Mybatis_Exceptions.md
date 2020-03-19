#### 1.连接数据库（mysql80）异常

- 问题：

  > com.mysql.cj.exceptions.WrongArgumentException: Malformed database URL, failed to parse the main URL sections.

- 解决方案

  >```java
  >//原来的URL
  >private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/db_ext_test?characterEncoding=UTF-8&amp;allowMultiQueries=true";
  >
  >//改后的URL：
  >//    指定时区：serverTimezone=GMT
  >//    指定是否用ssl连接：useSSL=false
  >private static final String JDBC_URL1 = "jdbc:mysql://127.0.0.1:3306/db_ext_test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
  >```



