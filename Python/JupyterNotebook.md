### 生成配置文件

jupyter notebook --generate-config

### 配置默认目录

删除快捷方式属性中的变量 -- 删除"%USERPROFILE%"

> C:\ProgramData\Anaconda3\python.exe C:\ProgramData\Anaconda3\cwp.py C:\ProgramData\Anaconda3 C:\ProgramData\Anaconda3\python.exe C:\ProgramData\Anaconda3\Scripts\jupyter-notebook-script.py "%USERPROFILE%/"

配置C:\Users\Administrator\.jupyter\jupyter_notebook_config.py文件中的c.NotebookApp.notebook_dir属性

```cmd
 NotebookApp] Exception while loading config file C:\Users\Administrator\.jupyter\jupyter_notebook_config.py
    Traceback (most recent call last):
      File "C:\mysoftware\install\anacoda\lib\site-packages\traitlets\config\application.py", line 562, in _load_config_files
        config = loader.load_config()
      File "C:\mysoftware\install\anacoda\lib\site-packages\traitlets\config\loader.py", line 457, in load_config
        self._read_file_as_dict()
      File "C:\mysoftware\install\anacoda\lib\site-packages\traitlets\config\loader.py", line 489, in _read_file_as_dict
        py3compat.execfile(conf_filename, namespace)
      File "C:\mysoftware\install\anacoda\lib\site-packages\ipython_genutils\py3compat.py", line 198, in execfile
        exec(compiler(f.read(), fname, 'exec'), glob, loc)
      File "C:\Users\Administrator\.jupyter\jupyter_notebook_config.py", line 214
        c.NotebookApp.notebook_dir = 'D:\nanligong\mianshi\project\python\'
                                                                          ^
    SyntaxError: EOL while scanning string literal
```

**配置文件路径末尾不能有\\**

### 添加虚拟环境到kernel中

1. 切换环境到虚拟环境

2. 安装ipykernel

   conda install ipykernel

3. 将虚拟环境安装到kernel中

   ipython kernel install --user --name=env_name

### 自动补全功能

https://github.com/ipython-contrib/jupyter_contrib_nbextensions

$$
G_x = \begin{matrix}
        -1 & 0 & +1 \\
        -2 & 0 & +2 \\
        -1 & 0 & +1
        \end{matrix} * A
    and
    G_y = \begin{matrix}
        -1 & -2 & -1 \\
        0 & 0 & 0 \\
        +1 & +2 & +1
        \end{matrix} * A
$$