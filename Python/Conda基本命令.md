#### 环境管理

- 创建环境

  conda create [--no-default-packages] {--name|-n} env_name package[=package_version]

  e.g. conda create --name python38 python=3.8 scipy=0.15 numpy(不指定版本则安装默认 -- 一般为最新)

  ​	--no-default-packages: 不安装默认安装包

  虚拟环境地址

  ​	使用conda命令安装虚拟环境路径： %userprofile%/.conda/envs

  ​	使用anaconda或pycharm创建的虚拟环境路径：%anaconda_home%/envs

- 激活环境

  conda activate env_name # for Windows

  source activate env_name # for Linux & Mac

- 查看python版本(当前环境)

  python --version

- 退出当前环境

  conda deactivate env_name # for Windows

  source deactivate env_name # for Linux & Mac

- 删除环境

  conda remove --name env_name --all

#### 包管理

- 查看conda版本信息

  conda [-V|--version|info]

- 安装包

  conda install package_name

- 查看当前环境已安装的包

  \# 最新版的conda是从site-packages文件夹中搜索已经安装的包，不依赖于pip，因此可以显示出通过各种方式安装的包)

  conda list

- 查看指定环境的已安装包

  conda list -n env_name

- 查看安装包信息

  conda search pachage_name

- 指定环境安装package

  \# 可以通过-c指定通过某个channel安装

  conda install -n env_name package_name

- 更新包

  conda update [-n env_name] package_name

- 删除包

  conda remove [-n env_name] package_name

- 更新conda

  conda update conda

- 更新anaconda

  conda update anaconda

- 更新python

  \# 只更新小版本，如，3.7.1只能升级到3.7.x，不能升级到3.8

  conda update python

###  源

#### Windows

- 添加源

  conda config --add channels url

  如，清华源

  conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
  conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/conda-forge 
  conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/msys2/

  中科大源

  conda config --add channels https://mirrors.ustc.edu.cn/anaconda/pkgs/main/
  conda config --add channels https://mirrors.ustc.edu.cn/anaconda/pkgs/free/
  conda config --add channels https://mirrors.ustc.edu.cn/anaconda/cloud/conda-forge/
  conda config --add channels https://mirrors.ustc.edu.cn/anaconda/cloud/msys2/
  conda config --add channels https://mirrors.ustc.edu.cn/anaconda/cloud/bioconda/
  conda config --add channels https://mirrors.ustc.edu.cn/anaconda/cloud/menpo/

- 设置搜索时显示通道地址

  conda config --set show_channel_urls yes

#### Linux

vim ~/.condarc

```
channels:
  - https://mirrors.ustc.edu.cn/anaconda/pkgs/main/
  - https://mirrors.ustc.edu.cn/anaconda/cloud/conda-forge/
  - https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
  - defaults
show_channel_urls: true
```

e.g. Windows环境也可以去家目录中直接编辑.condarc文件

