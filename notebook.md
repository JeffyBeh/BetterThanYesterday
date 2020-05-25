### 1. centos切换jdk版本

* alternatives --config java

### 2. centos JAVA_HOME与Java -version 版本不对应

which java 

ll /usr/bin | grep java 

删除/usr/bin/java及/etc/alternatives/java

> https://blog.csdn.net/weixin_37550903/article/details/80672580

### 3. 恢复.swp文件

vim -r .swp

编辑完保存，删除swp文件

### 4. 查看java进程

jps

如果jps无法执行，说明java配置有问题

### 5. 普通给用户添加sudo权限

su root -> vim /etc/sudoers

### 6. the ssh server rejected your password

systemctl restart sshd  (重启sshd服务)

#### 7. vim 设置tab缩进

vim /etc/vimrc

```bash
 " tab宽度设置为4个空格
 set tabstop=4
 " 设置tab所占的列数，当输入tab时，设为4个空格宽度
 set softtabstop=4
 " 缩进使用3个空格宽度
 set shiftwidth=4
 " 扩展tab为空格
 set noexpandtab


粘贴文本时
:set paste 可以取消缩进
-- INSERT(paste) --
:set nopaste 取消
```

#### 8. centos8重启网络服务

```shell
$ nmcli c reload (ens33)
$ nmcli d reapply ens33
$ nmcli d connect ens33
```

#### 9. 安装python3.8

centos8中默认安装的python3.6.8

1. 插件准备

   >**#yum install gcc gcc-c++**
   >
   >**#yum -y install gcc automake autoconf libtool make**
   >
   >**#yum groupinstall -y 'Development Tools'**
   >
   >**\# yum install -y gcc openssl-devel bzip2-devel libffi-devel**

2. 下载tar包并解压

3. 检查编译环境

   > **# ./configure prefix=/usr/local/python3.8 --enable-optimization**

4. 编译安装

   > **\# make -j 4 && make install**

5. 配置环境变量

   > export PATH=$PATH:/usr/local/python3.8/bin

   配置环境变量后，需通过python3.8启动（.../bin/python3.8），如果希望通过python或使用python3启动，可以修改/usr/bin/pythno3及/etc/alternatives/python3的链接到.../bin/python3.8，（原python3链接为/usr/bin/python3.8）