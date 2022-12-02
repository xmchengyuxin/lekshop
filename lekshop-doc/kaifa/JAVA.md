# 后端开发规范
### 默认规约
与阿里巴巴java规约一致，如果有需要，可以在idea中以及类似ide中下载对应的阿里巴巴规约

### 规范扩展
1. 类名使用 UpperCamelCase 风格：第一个词的首字母，以及后面每个词的首字母都大写

2. 方法名使用lowerCamelCase风格：第一个词的首字母小写，后面每个词的首字母大写。

3. 常量命名全部大写，单词间用下划线隔开。

4. 控制器需以：Controller结尾，例如：GoodsController。

5. 业务层需以Service结尾，例如：GoodsService。

6. Mapper需以Mapper结尾，例如：GoodsMapper。

### 注释模板
#### 类注释模板
```
  /**
    * @title  {类内容}
    * @author {作者}
    * @date   {date}
    */
 ```
 #### 方法注释模板
```
  /**
    * {方法描述}
    * @author {作者}
    * @date   {date}
    * @param  {参数}
    * @return {返回值}
    */
 ```

1. 业务层需以Service结尾，例如：GoodsService。

2. Mapper需以Mapper结尾，例如：GoodsMapper。

3. 模型命名规范

   i. 在lekshop-mbg 目录下的类，即为数据库模型，默认无后缀命名，例如Goods

   ii. 在domain.result 目录下的类，即为响应数据模型，默认后缀Result命名，例如GoodsResult，通常情况用于服务端给前端的响应

   iii. 在domain.form 目录下的类，即为传递数据模型，默认后缀Form命名，例如GoodsForm，通常情况用于前后端，或者服务于服务之间交互的数据模型

