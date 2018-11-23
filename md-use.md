#实现在 Eclipse 中编写 Markdown 文件
>摘要： 本文介绍了使用 Eclipse Markdown Editor 插件，来实现在 Eclipse 中编写 Markdown 文件。 Eclipse Markdown Editor 的介绍 Eclipse Markdown Editor 是一款 Eclipse 插件，可以实现 .md 和 .txt 文件的 Markdown 语法高亮，并提供 HTML 预览。
本文介绍了使用 Eclipse Markdown Editor 插件，来实现在 Eclipse 中编写 Markdown 文件。
Eclipse Markdown Editor 的介绍
Eclipse Markdown Editor 是一款 Eclipse 插件，可以实现 .md 和 .txt 文件的 Markdown 语法高亮，并提供 HTML 预览。经过测试，可以满足一些基本的 Markdown 语法。
##1.Eclipse上安装Markdown插件
>与一般的 Eclipse 插件安装方法类似，无非是在线安装（Eclipse Marketplace）和离线包形式安装。
从 Eclipse Marketplace 安装
在 Eclipse 中选择 (Help > Install New Software)
URL 为 http://winterwell.com/software/updatesite/
选择最新版本
离线包形式安装
下载插件包：https://github.com/winterstein/Eclipse-Markdown-Editor-Plugin/releases
解压到本地，以 .link 文件方式来安装。其中 .link 指向了解压后的插件，而后把 .link 文件放到 Eclipse 安装目录的 dropins 目录下即可。可以参考http://www.waylau.com/about-optimizer-for-eclipse/
使用
插件安装成后，可以使用 Markdown Editor 查看、编辑 Markdown 文件
##2.Mrkdown使用规则：
#正文：
Markdown 语法速查表
###1 标题与文字格式
>####标题
# 这是 H1 <一级标题>
## 这是 H2 <二级标题>
### 这是 H3 <三级标题>
#### 这是 H4 <四级标题>
##### 这是 H5 <五级标题>
###### 这是 H6 <六级标题>
####### 这是 H7 <七级标题>

=========================================================
> #### 文字格式，强调

*斜体*
_斜体_
**加粗**
__加粗__

- **这是文字粗体格式**
- *这是文字斜体格式*
- ~~在文字上添加删除线~~

#### 转义
* \\
* \`
* \~
* \*
* \_
* \-
* \+
* \.
* \!
###2 列表
####无序列表
* 项目1
* 项目2
* 项目3
+ 项目4
+ 项目5
+ 项目6
- 项目7
- 项目8
- 项目9
####有序列表
1. 项目1
2. 项目2
3. 项目3
   * 项目1
   * 项目2
###3 其它
####图片
![图片名称](https://img0.bdstatic.com/static/searchresult/img/logo-2X_b99594a.png)

[百度图片]:(https://img0.bdstatic.com/static/searchresult/img/logo-2X_b99594a.png)
[百度图片]: https://img0.bdstatic.com/static/searchresult/img/logo-2X_b99594a.png

参数式图片，这里是 [百度图片]

####链接
[链接名称](http://baidu.com)点击可以跳入指定网站
* [带连接的列表1](http://www.baidu.com)点击可以跳转到指定页面
* [带连接的列表2](http://www.baidu.com)点击可以跳转到指定页面
* [带连接的列表3](http://www.baidu.com "说明，跳转到百度") 点击可以跳转到指定页面，可以添加说明，即title
#### 参数式
[name]:http://www.baidu.com "名称"
[home]: http://www.baidu.com "首页"
[也支持中文]: /home/name "随便写点"
这里是[name]，这里是[home]，这里是[也支持中文]
***
这就好理解了，就是把链接当成参数，适合多出使用相同链接的场景，注意参数的对应关系，参数定义时，这3种写法都可以：
[foo]: http://example.com/ "Optional Title Here"
[foo]: http://example.com/ 'Optional Title Here'
[foo]: http://example.com/ (Optional Title Here)
还支持这种写法，如果你不想混淆的话：
[foo]: <http://example.com/> "Optional Title Here"
####引用
> 一级引用
>> 二级引用
>>> 三级引用
>>>> 四级引用
>>>>> 五级引用
>>>>>> 六级引用
>>>>>>> 七级引用
>>>>>>>> 八级引用
>>>>>>>>> 九级引用
>>>>>>>>>> 可以无限极引用
>- > 第一行引用文字
>- > 第二行引用文字
####分割线
***
******
---
___
* * *
- - - - - - - - - - 
_ _ _ _ _ _ _ _ _ _ _ _
####代码
第一种 单行用 ``

`<hello world>` 代码块高亮

第二种 多行用3个```
```ruby（可以写注释）
  def add(a, b)
    return a + b
  end
```
####表格
 | name | age | sex |
 |:---:|:---:|:---:|
 |tomcat|20|公|
 |jerry|18|母|
 
  表头1  | 表头2
  ------------- | -------------
 单元格内容  | 单元格内容
 单元格内容l  | 单元格内容
 
学号 | 姓名 | 分数
- | - | - 
 小明 | 男 | 75
 小明 | 男 | 75
 小明 | 男 | 75

