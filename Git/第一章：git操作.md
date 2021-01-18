# 1. git操作

### 1. git底层概念

#### 1.1. .git目录

* hooks -- 客户端或服务端的钩子脚本（类似js中的回调函数）

  * 可以自己写脚本

* info  -- 包含全局性的排除文件  

* logs -- 日志

* objects -- 目录存储所有数据内容

* refs -- 目录存储指向数据（分支）的提交对象的指针

* config -- 配置文件

* description -- 显示对仓库的描述信息

* HEAD -- 文件指示目前被检出的分支

* index -- 文件保存暂存区信息


#### 1.2. git对象

​		git的核心部分是一个简单的键值对数据库。你可以向该数据库插入任意类型的内容，它会返回一个值，通过该键值可以在任意时刻再次检索内容

- 向数据库写入内容，并返回键值

  - 命令：echo "test content" | git hash-object -w --stdin

    ​	-w 选项指示 hash-object命令存储数据对象；若不指定此选项，则仅返回键值

    ​	--stdin(standard input)选项则指示该命令从标准输入读取内容；若不指定则需要在命令中指定待存储的路径

    ​		git bash-object -w filePath：存文件

    ​		git hash-object filePath：仅返回对应键值

    存储后则在./git/object/中生成键值前两位及剩余位数的文件夹及文件，文件内容为被压缩的键值

- 根据键值拉取文件内容

  - 命令：git cat-file -p hashKey

    ​	-p 查看内容（value)

    ​	-t 查看hashKey的类型 ll 