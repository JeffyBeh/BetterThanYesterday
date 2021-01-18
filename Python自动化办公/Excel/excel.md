### 1. 创建文件

```python
# 创建文件
import pandas as pd

# 创建空文件，自动创建索引
# df = pd.DataFrame()
df = pd.DataFrame('ID':[1,2,3], 'Name':['Tim', 'Victor', 'Nick'])
# 将ID设置为索引
df = df.setIndex('ID')

df.to_excel("c:/tmp/output.xlsx")
```

>xlrd.biffh.XLRDError: Excel xlsx file； not supported
>
>原因是最近xlrd更新到了2.0.1版本，只支持.xls文件。所以pandas.read_excel(‘xxx.xlsx’)会报错。
>
>可以安装旧版xlrd，在cmd中运行：
>
>pip uninstall xlrd
>pip install xlrd==1.2.0
>
>也可以用openpyxl代替xlrd打开.xlsx文件：
>
>df=pandas.read_excel(‘data.xlsx’,engine=‘openpyxl’)

### 2. 读取文件

```python
import pandas as pd

# 读取文件 -- 默认自动去除前面空行
# people = pd.reade_excel("c:/temp/people.xlsx")
# 读取并指定header -- 去除第一行脏数据
# people = pd.read_excel("c:/temp/people.xlsx"，header=1)
# 指定索引index
people = pd.read_excel("c:/temp/people.xlsx" index_col='ID')
# 不设置header -- 自动生成0，1，2... 索引
people = pd.reade_excel("c:/temp/people.xlsx"，header=None)
# 指定header
people.columns = ['ID', 'Type', 'Title']

# 获取行列数
print(people.shape)

# 获取列名 -- 默认读取第0行
print(people.columns)

# 获取整个文件数据
# print(people)
# 获取前N数据（包含列名），默认5行
print(people.head(3))
# 获取最后3行数据（包含列名），默认5行
print(people.tail(3))

# 设置索引列
# people = people.setIndex['ID']
# 直接修改当前对象 -- 如果设置索引列，则该列就不属于columns，两者区别对待，并在下一次读取时候默认再次生成索引列
people.setIndex['ID', inplace=true]

# 输出文件
pd.to_excel("c:/temp/output.xlsx")
```

### 3. 行、列、单元格

**pandas中行和列都是序列**

```python
import pandas as pd

d = {'x':100, 'y':200, 'z':300}
# 序列化
#   将key转为index，value转为data
s1 = pd.Series(d)

print(s1.index)
# data弃用
# print(s1.data)
```

```python
import pandas as pd

L1 = [100, 200, 300]
L2 = ['x', 'y', 'z']

# L1作为data， L2作为index
s1 = pd.Series(L1, index=L2)
# s1 = pd.Serier([100, 200, 300], index=['x', 'y', 'z'])
```

自定义行和列

```python
import pandas as pd

s1 = pd.Series([1, 2, 3], index=[1, 2, 3], name='A')
s2 = pd.Series([10, 20, 30], index=[1, 2, 3], name='B')
s3 = pd.Series([100, 200, 300], index=[1, 2, 3], name='C')

# 将序列以字典的形式添加为列, 序列名为列名
df = pd.DataFrame({s1.name:s1, s2.name:s2, s3.name:s3})

# 以list的形式添加则为行，序列名为行号
df = pd.DataFrame([s1, s2, s3])
```

### 4. 数据区域读取/填充数据

```python
import pandas as pd
from datetime import date, timedeta

def add_month(d, md):
    mon = d.month + md
    yd = mon // 12

    return date(d.year + yd, mon % 12 + 1 if mon // 12 > 0 else mon % 12, d.day)

'''
	如果包含空行和空列则会出现读取错误
		通过使用skiprows（忽略空行）和usecols（确定使用哪几列）来确定区域范围
		
  空单元格默认被读取为NaN（dtype=float64) -- 当输入整数时默认会转换为浮点数
  可以使用dtype参数以字典的形式却定列数据类型，一般都修改为str
''' 
books = pd.read_excel('C:/Users/Administrator/Desktop/input.xlsx', engine='openpyxl',
                      skiprows=3, usecols='C:F', index_col=None, dtype={'ID': str, 'inStore': str, 'Date': str})

start = date(2020, 1, 1)
for i in range(20):
    # book['col_name'].at[index]
    books['ID'].at[i] = i + 1
    books['inStore'].at[i] = 'Yes' if i % 2 == 0 else 'No'
    # 日期递增
    # books['Date'].at[i] = start + timedelta(days=i)
    # 年份递增
    # books['Date'].at[i] = date(start.year + i, start.month, start.day)
    # book.at[i, 'col_name']
    books.at[i, 'Date'] = start = add_month(start, 4)
print(books)

books.set_index('ID', inplace=True)
books.to_excel('C:/Users/Administrator/Desktop/output.xlsx')
print('Done')
```

### 5. 函数填充、计算列

```python
import pandas as pd


def add_2(x):
    return + 2
  
  
books = pd.read_excel('c:/users/administrator/desktop/input.xlsx', engine='openpyxl')
# 计算价格，此时操作符重载，进行列计算
books['Price'] = books['TotalPrice'] * books['Discount']
# books['TotalPrice']列每个单元格均*0.8 
# books['Price'] = books['TotalPrice'] * 0.8

# 通过循环读取单元格对指定范围进行计算
# for i in range(5, 16):
#     books['Price'].at[i]  =  books['TotalPrice'].at[i] * books['Discount'].at[i]

# 修改价格，直接操作当前单元格
# books['TotalPrice'] += 2

# 通过apply(被调用函数名)实现复杂计算
# books['TotalPrice'] = books['TotalPrice'].apply(add_2)
# 可以使用lambda表达式
books['TotalPrice'] = books['TotalPrice'].apply(lambda x: x + 2)
print(books)
```

### 6. 排序、多重排序

```python
import pandas as pd

product = pd.read_excel('c:/users/administrator/desktop/input.xlsx', engine='openpyxl', usecols='A:D', index_col='ID')

# 排序，ascending默认为True -- 从小到大（升序）, by可不写
# product.sort_values(by='Price', inplace=True, ascending=True)

# 多重排序--使用列表，ascending同样使用列表对应到排序列表
product.sort_values(['Worthy', 'Price'], ascending=[True, False], inplace=True)
print(product)
```

### 7. 筛选

```python
import pandas as pd

students = pd.read_excel('c:/users/administrator/desktop/students.xlsx', engine='openpyxl')

# 筛选
# students.Age 等价于 students['Age']
students = students.loc[students.Age.apply(lambda age: 18 <= age < 30)] \
    .loc[students.Score.apply(lambda score: 80 <= score <= 100)]

print(students)
```

### 8. 绘制图表 -- 柱状图

```python 
import pandas as pd
import matplotlib.pyplot as plt

field = pd.read_excel('C:/users/administrator/desktop/fields.xlsx', engine='openpyxl')

field = field.sort_values(by='Number', ascending=False)
# 使用pandas内置函数绘图, bar -- 柱状图
# field.plot.bar(x='Field', y='Number', color='green', title='field Number')

# 使用plt函数绘图
plt.bar(field.Field, field.Number, color='orange')
# 设置x坐标字段，rotation表示旋转方向
plt.xticks(field.Field, rotation=45)
plt.xlabel('Field')
plt.ylabel('Number')
plt.title('filed Number', fontsize=18)

# 设置紧凑布局，以便能够显示全部内容
plt.tight_layout()
# 展示绘图内容
plt.show()

print(field)
```

### 9. 分组柱状图深度优化

```python 
import pandas as pd
import matplotlib.pyplot as plt


student = pd.read_excel('c:/users/administrator/desktop/students09.xlsx', engine='openpyxl')
print(student)

# 坐标列名如果为数字则不能使用字符串
student.plot.bar(x='Field', y=[2019, 2020], color=['green', 'orange'])
plt.xlabel('Field', fontweight='bold')
plt.ylabel('Number', fontweight='bold')
plt.title("International Students by Field", fontsize=16, fontweight='bold')
# gca -- Get Current Axes(轴数） -- 获取坐标轴
ax = plt.gca()
# rotation默认以文字中心为轴旋转
# ha（Horizontal alignment）水平对齐 -- 设置中心点
ax.set_xticklabels(student.Field, rotation=45, ha='right')
# Get Current Figure -- 获取图标
f = plt.gcf()
# 边距设置，目前没有必要设置
# f.subplots_adjust(left=0.5, bottom=0.42)
# 设置紧凑布局，显示全部内容，目前没有必要设置
# plt.tight_layout()
plt.show()
```

### 10. 叠加水平柱状图

