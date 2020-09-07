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
   >
   >
   >
   >另：（上述安装后无法安装ssl等模块）
   >
   >dnf install bzip2-devel expat-devel gdbm-devel \
   > ncurses-devel openssl-devel readline-devel(识别退格等，不安装则直接识别为码值) \
   >  sqlite-devel tk-devel xz-devel zlib-devel wget

2. 下载tar包并解压

3. 检查编译环境

   > **# ./configure prefix=/usr/local/python3.8 --enable-optimization**

4. 编译安装

   > **\# make -j 4 && make install**

5. 配置环境变量

   > export PATH=$PATH:/usr/local/python3.8/bin

   配置环境变量后，需通过python3.8启动（.../bin/python3.8），如果希望通过python或使用python3启动，可以修改/usr/bin/pythno3及/etc/alternatives/python3的链接到.../bin/python3.8，（原python3链接为/usr/bin/python3.8）

#### 10.GBK 编码问题

查看gbk编码文件(终端默认编码为utf-8，查看gbk文件出现乱码，将gbk转utf-8查看即可)

```bash
$cat xxx.xx | iconv -f gbk -t utf-8
```

vim 出现乱码

```bash
:set fileencoding
latin
# vim 自动编码不包含GBK/GB2312, 没有找到匹配编码时为latin1（ASCII)
# 临时修改
:e++enc=cp936 # cp936即为GBK
# 或
:edit++enc=cp936
# 永久修改
vim vimrc
set fileenc odings=utf-8,gb18030,gbk,gb2312,big5,latin1 # 加入gbk编码格式，选择需要编码格式添加即可
```

##### 11. 网络

- NetworkManager

  systemctl stauts NetworkManager

  systemctl start NetworkManager

  systemctl stop NetworkManager

  systemctl restart NetworkManager

- 查询被管理的端口

  nmcli dev status

- 不被管理 修改 ifcfg-\*

  NM_CONTROLLED=no

##### 13. SSL_ERROR

curl: (35) OpenSSL SSL_connect: SSL_ERROR_SYSCALL in connection to raw.githubusercontent.com:443 

解决方案：移除代理

```bash
$ git config --global --unset http.proxy
$ git config --global --unset https.proxy
```

#### 14. 递归删除指定文件、文件夹

```bash
# 删除 ./ 下名称为.log后缀的所有文件
$ find ./ -name *.log -type f -print -exec rm -rf {} \;
./ -- 指定目录
-name *.log -- 指定文件名
-type f(文件) -- 指定格式
-print -- 输出找到的符合要求文件
-exec rm -rf {} \;  -- 对查询结果执行操作（执行删除操作)  {}  \;为固定格式
```

