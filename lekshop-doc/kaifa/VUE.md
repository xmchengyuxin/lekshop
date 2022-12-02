<!-- wp:heading {"level":1} -->
<h1 id="前端-js-项目开发规范">前端 JS 项目开发规范<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#%E5%89%8D%E7%AB%AF-js-%E9%A1%B9%E7%9B%AE%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83"></a></h1>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>规范的目的是为了编写高质量的代码，让你的团队成员每天得心情都是愉悦的，大家在一起是快乐的。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>引自《阿里规约》的开头片段：</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p><em>----现代软件架构的复杂性需要协同开发完成，如何高效地协同呢？无规矩不成方圆，无规范难以协同，比如，制订交通法规表面上是要限制行车权，实际上是保障公众的人身安全，试想如果没有限速，没有红绿灯，谁还敢上路行驶。对软件来说，适当的规范和标准绝不是消灭代码内容的创造性、优雅性，而是限制过度个性化，以一种普遍认可的统一方式一起做事，提升协作效率，降低沟通成本。代码的字里行间流淌的是软件系统的血液，质量的提升是尽可能少踩坑，杜绝踩重复的坑，切实提升系统稳定性，码出质量。</em></p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":1} -->
<h1 id="一、编程规约">一、编程规约<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#%E4%B8%80%E3%80%81%E7%BC%96%E7%A8%8B%E8%A7%84%E7%BA%A6"></a></h1>
<!-- /wp:heading -->

<!-- wp:heading {"level":3} -->
<h3 id="一命名规范">(一)命名规范<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#%E4%B8%80%E5%91%BD%E5%90%8D%E8%A7%84%E8%8C%83"></a></h3>
<!-- /wp:heading -->

<!-- wp:heading {"level":4} -->
<h4 id="111-项目命名">1.1.1 项目命名<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#111-%E9%A1%B9%E7%9B%AE%E5%91%BD%E5%90%8D"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>全部采用小写方式， 以中划线分隔。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：<code>mall-management-system</code></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>反例：<code>mall_management-system / mallManagementSystem</code></p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="112-目录命名">1.1.2 目录命名<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#112-%E7%9B%AE%E5%BD%95%E5%91%BD%E5%90%8D"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>全部采用小写方式， 以中划线分隔，有复数结构时，要采用复数命名法， 缩写不用复数</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：&nbsp;<code>scripts / styles / components / images / utils / layouts / demo-styles / demo-scripts / img / doc</code></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>反例：&nbsp;<code>script / style / demo_scripts / demoStyles / imgs / docs</code></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>【特殊】VUE 的项目中的 components 中的组件目录，使用 kebab-case 命名</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：&nbsp;<code>head-search / page-loading / authorized / notice-icon</code></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>反例：&nbsp;<code>HeadSearch / PageLoading</code></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>【特殊】VUE 的项目中的除 components 组件目录外的所有目录也使用 kebab-case 命名 正例：&nbsp;<code>page-one / shopping-car / user-management</code></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>反例：&nbsp;<code>ShoppingCar / UserManagement</code></p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="113-js、css、scss、html、png-文件命名">1.1.3 JS、CSS、SCSS、HTML、PNG 文件命名<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#113-js%E3%80%81css%E3%80%81scss%E3%80%81html%E3%80%81png-%E6%96%87%E4%BB%B6%E5%91%BD%E5%90%8D"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>全部采用小写方式， 以中划线分隔</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：&nbsp;<code>render-dom.js / signup.css / index.html / company-logo.png</code></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>反例：&nbsp;<code>renderDom.js / UserManagement.html</code></p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="114-命名严谨性">1.1.4 命名严谨性<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#114-%E5%91%BD%E5%90%8D%E4%B8%A5%E8%B0%A8%E6%80%A7"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>代码中的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式。 说明：正确的英文拼写和语法可以让阅读者易于理解，避免歧义。注意，即使纯拼音命名方式也要避免采用</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：<code>henan / luoyang / rmb 等国际通用的名称，可视同英文。</code></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>反例：<code>DaZhePromotion [打折] / getPingfenByName() [评分] / int 某变量 = 3</code></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p><strong>杜绝完全不规范的缩写，避免望文不知义：</strong></p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>反例：AbstractClass“缩写”命名成 AbsClass；condition“缩写”命名成 condi，此类随意缩写严重降低了代码的可阅读性。</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":3} -->
<h3 id="二html-规范-（vue-template-同样适用）">(二)HTML 规范 （Vue Template 同样适用）<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#%E4%BA%8Chtml-%E8%A7%84%E8%8C%83-%EF%BC%88vue-template-%E5%90%8C%E6%A0%B7%E9%80%82%E7%94%A8%EF%BC%89"></a></h3>
<!-- /wp:heading -->

<!-- wp:heading {"level":4} -->
<h4 id="121-html-类型">1.2.1 HTML 类型<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#121-html-%E7%B1%BB%E5%9E%8B"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>推荐使用 HTML5 的文档类型申明： . （建议使用 text/html 格式的 HTML。避免使用 XHTML。XHTML 以及它的属性，比如 application/xhtml+xml 在浏览器中的应用支持与优化空间都十分有限）。</p>
<!-- /wp:paragraph -->

<!-- wp:list -->
<ul><!-- wp:list-item -->
<li>规定字符编码</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>IE 兼容模式</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>规定字符编码</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>doctype 大写</li>
<!-- /wp:list-item --></ul>
<!-- /wp:list -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;!DOCTYPE html&gt;
&lt;html&gt;
  &lt;head&gt;
    &lt;meta http-equiv="X-UA-Compatible" content="IE=Edge" /&gt;
    &lt;meta charset="UTF-8" /&gt;
    &lt;title&gt;Page title&lt;/title&gt;
  &lt;/head&gt;
  &lt;body&gt;
    &lt;img src="images/company-logo.png" alt="Company" /&gt;
  &lt;/body&gt;
&lt;/html&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":4} -->
<h4 id="122-缩进">1.2.2 缩进<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#122-%E7%BC%A9%E8%BF%9B"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>缩进使用 4 个空格（一个 tab）</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>嵌套的节点应该缩进。</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="123-分块注释">1.2.3 分块注释<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#123-%E5%88%86%E5%9D%97%E6%B3%A8%E9%87%8A"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>在每一个块状元素，列表元素和表格元素后，加上一对 HTML 注释。注释格式</p>
<!-- /wp:paragraph -->

<!-- wp:image {"linkDestination":"custom"} -->
<figure class="wp-block-image"><a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#"><img src="https://docs.pickmall.cn/development/images/header.jpg" alt=""/></a></figure>
<!-- /wp:image -->

<!-- wp:paragraph -->
<p>&lt;/body&gt;</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
### 1.2.4 语义化标签

HTML5 中新增很多语义化标签，所以优先使用语义化标签，避免一个页面都是 div 或者 p 标签

正例
</code></pre>
<!-- /wp:code -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
反例
</code></pre>
<!-- /wp:code -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
#### 1.2.5 引号

使用双引号(" ") 而不是单引号(’ ') 。

正例： `"header"`

反例： `'header'`

### (三) CSS 规范

#### 1.3.1 命名

- 类名使用小写字母，以中划线分隔
- id 采用驼峰式命名
- scss 中的变量、函数、混合、placeholder 采用驼峰式命名

ID 和 class 的名称总是使用可以反应元素目的和用途的名称，或其他通用的名称，代替表象和晦涩难懂的名称

不推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>.fw-800 { font-weight: 800; }</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>.red { color: red; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
推荐:
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>.heavy { font-weight: 800; }</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>.important { color: red; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
#### 1.3.2 选择器

1)css 选择器中避免使用标签名
从结构、表现、行为分离的原则来看，应该尽量避免 css 中出现 HTML 标签，并且在 css 选择器中出现标签名会存在潜在的问题。

2)很多前端开发人员写选择器链的时候不使用 直接子选择器（注：直接子选择器和后代选择器的区别）。有时，这可能会导致疼痛的设计问题并且有时候可能会很耗性能。然而，在任何情况下，这是一个非常不好的做法。如果你不写很通用的，需要匹配到 DOM 末端的选择器， 你应该总是考虑直接子选择器。

不推荐:
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>.content .title { font-size: 2rem; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
推荐:
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>.content &gt; .title { font-size: 2rem; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
#### 1.3.3 尽量使用缩写属性

不推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>border-top-style: none; font-family: palatino, georgia, serif; font-size: 100%; line-height: 1.6; padding-bottom: 2em; padding-left: 1em; padding-right: 1em; padding-top: 0;</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>border-top: 0; font: 100%/1.6 palatino, georgia, serif; padding: 0 1em 2em;</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
#### 1.3.4 每个选择器及属性独占一行

不推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>button{ width:100px;height:50px;color:#fff;background:#00a0e9; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>button{ width:100px; height:50px; color:#fff; background:#00a0e9; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
#### 1.3.5 省略0后面的单位

不推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>div{ padding-bottom: 0px; margin: 0em; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>div{ padding-bottom: 0; margin: 0; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
#### 1.3.6 避免使用ID选择器及全局标签选择器防止污染全局样式

不推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":1} -->
<h1 id="header">header{<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#header"></a></h1>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>padding-bottom: 0px; margin: 0em; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>.header{ padding-bottom: 0px; margin: 0em; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
### (四) LESS 规范

#### 1.4.1 代码组织

##### 1)将公共less文件放置在style/less/common文件夹

例:// color.less,common.less

##### 2)按以下顺序组织

1、@import;
2、变量声明;
3、样式声明;
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>@import "mixins/size.less";</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>@default-text-color: #333;</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>.page { width: 960px; margin: 0 auto; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
#### 1.4.2 避免嵌套层级过多

将嵌套深度限制在3级。对于超过4级的嵌套，给予重新评估。这可以避免出现过于详实的CSS选择器。
避免大量的嵌套规则。当可读性受到影响时，将之打断。推荐避免出现多于20行的嵌套规则出现

不推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>.main{ .title{ .name{ color:#fff } } }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
推荐：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>.main-title{ .name{ color:#fff } }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
### (五) Javascript 规范

#### 1.5.1 命名

##### 1) 采用小写驼峰命名 lowerCamelCase，代码中的命名均不能以下划线，也不能以下划线或美元符号结束

反例： `_name / name_ / name$`

##### 2) 方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格，必须遵从驼峰形式。

正例： `localValue / getHttpMessage() / inputUserId`

****其中 method 方法命名必须是 动词 或者 动词+名词 形式\****

正例：`saveShopCarData /openShopCarInfoDialog`

反例：`save / open / show / go`

***特此说明，增删查改，详情统一使用如下 5 个单词，不得使用其他（目的是为了统一各个端）***
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>add / update / delete / detail / get</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
**附： 函数方法常用的动词:**
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>get 获取/set 设置, add 增加/remove 删除 create 创建/destory 移除 start 启动/stop 停止 open 打开/close 关闭, read 读取/write 写入 load 载入/save 保存, create 创建/destroy 销毁 begin 开始/end 结束, backup 备份/restore 恢复 import 导入/export 导出, split 分割/merge 合并 inject 注入/extract 提取, attach 附着/detach 脱离 bind 绑定/separate 分离, view 查看/browse 浏览 edit 编辑/modify 修改, select 选取/mark 标记 copy 复制/paste 粘贴, undo 撤销/redo 重做 insert 插入/delete 移除, add 加入/append 添加 clean 清理/clear 清除, index 索引/sort 排序 find 查找/search 搜索, increase 增加/decrease 减少 play 播放/pause 暂停, launch 启动/run 运行 compile 编译/execute 执行, debug 调试/trace 跟踪 observe 观察/listen 监听, build 构建/publish 发布 input 输入/output 输出, encode 编码/decode 解码 encrypt 加密/decrypt 解密, compress 压缩/decompress 解压缩 pack 打包/unpack 解包, parse 解析/emit 生成 connect 连接/disconnect 断开, send 发送/receive 接收 download 下载/upload 上传, refresh 刷新/synchronize 同步 update 更新/revert 复原, lock 锁定/unlock 解锁 check out 签出/check in 签入, submit 提交/commit 交付 push 推/pull 拉, expand 展开/collapse 折叠 begin 起始/end 结束, start 开始/finish 完成 enter 进入/exit 退出, abort 放弃/quit 离开 obsolete 废弃/depreciate 废旧, collect 收集/aggregate 聚集</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
##### 3) 常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。

正例： `MAX_STOCK_COUNT`

反例： `MAX_COUNT`

#### 1.5.2 代码格式

##### 1) 使用 4 个空格进行缩进

正例：
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>if (x &lt; y) { x += 10; } else { x += 1; }</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
##### 2) 不同逻辑、不同语义、不同业务的代码之间插入一个空行分隔开来以提升可读性。

&gt; 说明：任何情形，没有必要插入多个空行进行隔开。

#### 1.5.3 字符串

统一使用单引号(‘)，不使用双引号(“)。这在创建 HTML 字符串非常有好处：

正例:
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>let str = 'foo'; let testDiv = '</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>';</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
反例:
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>let str = 'foo'; let testDiv = "</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>";</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>
#### 1.5.4 对象声明

##### 1)使用字面值创建对象

正例： `let user = {};`

反例： `let user = new Object();`

##### 2) 使用字面量来代替对象构造器

正例：

```javascript
var user = {
  age: 0,
  name: 1,
  city: 3
};
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>var user = new Object();
user.age = 0;
user.name = 0;
user.city = 0;
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":4} -->
<h4 id="155-使用-es67">1.5.5 使用 ES6,7<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#155-%E4%BD%BF%E7%94%A8-es67"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>必须优先使用 ES6,7 中新增的语法糖和函数。这将简化你的程序，并让你的代码更加灵活和可复用。</p>
<!-- /wp:paragraph -->

<!-- wp:quote -->
<blockquote class="wp-block-quote"><!-- wp:paragraph -->
<p>必须强制使用 ES6, ES7 的新语法，比如箭头函数、await/async ， 解构， let ， for…of 等等</p>
<!-- /wp:paragraph --></blockquote>
<!-- /wp:quote -->

<!-- wp:heading {"level":4} -->
<h4 id="156-括号">1.5.6 括号<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#156-%E6%8B%AC%E5%8F%B7"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>下列关键字后必须有大括号（即使代码块的内容只有一行）：if, else, for, while, do, switch, try, catch, finally, with。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>if (condition) {
  doSomething();
}
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>if (condition) doSomething();
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":4} -->
<h4 id="157-undefined-判断">1.5.7 undefined 判断<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#157-undefined-%E5%88%A4%E6%96%AD"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>永远不要直接使用 undefined 进行变量判断；使用 typeof 和字符串’undefined’对变量进行判断。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>if (typeof person === 'undefined') {
    ...
}
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>if (person === undefined) {
    ...
}
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":4} -->
<h4 id="158-条件判断和循环最多三层">1.5.8 条件判断和循环最多三层<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#158-%E6%9D%A1%E4%BB%B6%E5%88%A4%E6%96%AD%E5%92%8C%E5%BE%AA%E7%8E%AF%E6%9C%80%E5%A4%9A%E4%B8%89%E5%B1%82"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>条件判断能使用三目运算符和逻辑运算符解决的，就不要使用条件判断，但是谨记不要写太长的三目运算符。如果超过 3 层请抽成函数，并写清楚注释。</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="159-this-的转换命名">1.5.9 this 的转换命名<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#159-this-%E7%9A%84%E8%BD%AC%E6%8D%A2%E5%91%BD%E5%90%8D"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>对上下文 this 的引用只能使用’self’来命名</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="1510-慎用-consolelog">1.5.10 慎用 console.log<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#1510-%E6%85%8E%E7%94%A8-consolelog"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>因 console.log 大量使用会有性能问题，所以在非 webpack 项目中谨慎使用 log 功能</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":1} -->
<h1 id="二、vue-项目规范">二、Vue 项目规范<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#%E4%BA%8C%E3%80%81vue-%E9%A1%B9%E7%9B%AE%E8%A7%84%E8%8C%83"></a></h1>
<!-- /wp:heading -->

<!-- wp:heading {"level":3} -->
<h3 id="一-vue-编码基础">(一) Vue 编码基础<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#%E4%B8%80-vue-%E7%BC%96%E7%A0%81%E5%9F%BA%E7%A1%80"></a></h3>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>vue 项目规范以 Vue 官方规范 （<a href="https://cn.vuejs.org/v2/style-guide/%EF%BC%89" target="_blank" rel="noreferrer noopener">https://cn.vuejs.org/v2/style-guide/）</a>&nbsp;中的 A 规范为基础，在其上面进行项目开发，故所有代码均遵守该规范。</p>
<!-- /wp:paragraph -->

<!-- wp:quote -->
<blockquote class="wp-block-quote"><!-- wp:paragraph -->
<p>请仔仔细细阅读 Vue 官方规范，切记，此为第一步。</p>
<!-- /wp:paragraph --></blockquote>
<!-- /wp:quote -->

<!-- wp:heading {"level":4} -->
<h4 id="211-组件规范">2.1.1. 组件规范<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#211-%E7%BB%84%E4%BB%B6%E8%A7%84%E8%8C%83"></a></h4>
<!-- /wp:heading -->

<!-- wp:heading {"level":5} -->
<h5 id="1-组件名为多个单词。">1) 组件名为多个单词。<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#1-%E7%BB%84%E4%BB%B6%E5%90%8D%E4%B8%BA%E5%A4%9A%E4%B8%AA%E5%8D%95%E8%AF%8D%E3%80%82"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>组件名应该始终是多个单词组成（大于等于 2），且命名规范为<code>KebabCase</code>格式。 这样做可以避免跟现有的以及未来的 HTML 元素相冲突，因为所有的 HTML 元素名称都是单个单词的。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>export default {
  name: 'TodoItem'
  // ...
};
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>export default {
  name: 'Todo',
  // ...
}
export default {
  name: 'todo-item',
  // ...
}
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="2-组件文件名为-pascal-case-格式">2) 组件文件名为 pascal-case 格式<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#2-%E7%BB%84%E4%BB%B6%E6%96%87%E4%BB%B6%E5%90%8D%E4%B8%BA-pascal-case-%E6%A0%BC%E5%BC%8F"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>components/
|- my-component.vue
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>components/
|- myComponent.vue
|- MyComponent.vue
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="3-基础组件文件名为-base-开头，使用完整单词而不是缩写。">3) 基础组件文件名为 base 开头，使用完整单词而不是缩写。<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#3-%E5%9F%BA%E7%A1%80%E7%BB%84%E4%BB%B6%E6%96%87%E4%BB%B6%E5%90%8D%E4%B8%BA-base-%E5%BC%80%E5%A4%B4%EF%BC%8C%E4%BD%BF%E7%94%A8%E5%AE%8C%E6%95%B4%E5%8D%95%E8%AF%8D%E8%80%8C%E4%B8%8D%E6%98%AF%E7%BC%A9%E5%86%99%E3%80%82"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>components/
|- base-button.vue
|- base-table.vue
|- base-icon.vue
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>components/
|- MyButton.vue
|- VueTable.vue
|- Icon.vue
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="4-和父组件紧密耦合的子组件应该以父组件名作为前缀命名">4) 和父组件紧密耦合的子组件应该以父组件名作为前缀命名<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#4-%E5%92%8C%E7%88%B6%E7%BB%84%E4%BB%B6%E7%B4%A7%E5%AF%86%E8%80%A6%E5%90%88%E7%9A%84%E5%AD%90%E7%BB%84%E4%BB%B6%E5%BA%94%E8%AF%A5%E4%BB%A5%E7%88%B6%E7%BB%84%E4%BB%B6%E5%90%8D%E4%BD%9C%E4%B8%BA%E5%89%8D%E7%BC%80%E5%91%BD%E5%90%8D"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>components/
|- todo-list.vue
|- todo-list-item.vue
|- todo-list-item-button.vue
|- user-profile-options.vue （完整单词）
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>components/
|- TodoList.vue
|- TodoItem.vue
|- TodoButton.vue
|- UProfOpts.vue （使用了缩写）
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="5-在-template-模版中使用组件，应使用-pascalcase-模式，并且使用自闭合组件。">5) 在 Template 模版中使用组件，应使用 PascalCase 模式，并且使用自闭合组件。<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#5-%E5%9C%A8-template-%E6%A8%A1%E7%89%88%E4%B8%AD%E4%BD%BF%E7%94%A8%E7%BB%84%E4%BB%B6%EF%BC%8C%E5%BA%94%E4%BD%BF%E7%94%A8-pascalcase-%E6%A8%A1%E5%BC%8F%EF%BC%8C%E5%B9%B6%E4%B8%94%E4%BD%BF%E7%94%A8%E8%87%AA%E9%97%AD%E5%90%88%E7%BB%84%E4%BB%B6%E3%80%82"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;!-- 在单文件组件、字符串模板和 JSX 中 --&gt;
&lt;MyComponent /&gt;
&lt;Row&gt;&lt;table :column="data"/&gt;&lt;/Row&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;my-component /&gt; &lt;row&gt;&lt;table :column="data"/&gt;&lt;/row&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="6-组件的-data-必须是一个函数">6) 组件的 data 必须是一个函数<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#6-%E7%BB%84%E4%BB%B6%E7%9A%84-data-%E5%BF%85%E9%A1%BB%E6%98%AF%E4%B8%80%E4%B8%AA%E5%87%BD%E6%95%B0"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>当在组件中使用 data 属性的时候 (除了 new Vue 外的任何地方)，它的值必须是返回一个对象的函数。 因为如果直接是一个对象的话，子组件之间的属性值会互相影响。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>export default {
  data () {
    return {
      name: 'jack'
    }
  }
}
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>export default {
  data: {
    name: 'jack'
  }
}
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="7-prop-定义应该尽量详细">7) Prop 定义应该尽量详细<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#7-prop-%E5%AE%9A%E4%B9%89%E5%BA%94%E8%AF%A5%E5%B0%BD%E9%87%8F%E8%AF%A6%E7%BB%86"></a></h5>
<!-- /wp:heading -->

<!-- wp:list -->
<ul><!-- wp:list-item -->
<li>必须使用 camelCase 驼峰命名</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>必须指定类型</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>必须加上注释，表明其含义</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>必须加上 required 或者 default，两者二选其一</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>如果有业务需要，必须加上 validator 验证</li>
<!-- /wp:list-item --></ul>
<!-- /wp:list -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code> props: {
  // 组件状态，用于控制组件的颜色
   status: {
     type: String,
     required: true,
     validator: function (value) {
       return &#91;
         'succ',
         'info',
         'error'
       ].indexOf(value) !== -1
     }
   },
    // 用户级别，用于显示皇冠个数
   userLevel：{
      type: String,
      required: true
   }
}
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="8-为组件样式设置作用域">8) 为组件样式设置作用域<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#8-%E4%B8%BA%E7%BB%84%E4%BB%B6%E6%A0%B7%E5%BC%8F%E8%AE%BE%E7%BD%AE%E4%BD%9C%E7%94%A8%E5%9F%9F"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;template&gt;
  &lt;button class="btn btn-close"&gt;X&lt;/button&gt;
&lt;/template&gt;

&lt;!-- 使用 `scoped` 特性 --&gt;
&lt;style scoped&gt;
  .btn-close {
    background-color: red;
  }
&lt;/style&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;template&gt;
  &lt;button class="btn btn-close"&gt;X&lt;/button&gt;
&lt;/template&gt;
&lt;!-- 没有使用 `scoped` 特性 --&gt;
&lt;style&gt;
  .btn-close {
    background-color: red;
  }
&lt;/style&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="9-如果特性元素较多，应该主动换行。">9) 如果特性元素较多，应该主动换行。<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#9-%E5%A6%82%E6%9E%9C%E7%89%B9%E6%80%A7%E5%85%83%E7%B4%A0%E8%BE%83%E5%A4%9A%EF%BC%8C%E5%BA%94%E8%AF%A5%E4%B8%BB%E5%8A%A8%E6%8D%A2%E8%A1%8C%E3%80%82"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;MyComponent foo="a" bar="b" baz="c"
    foo="a" bar="b" baz="c"
    foo="a" bar="b" baz="c"
 /&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;MyComponent foo="a" bar="b" baz="c" foo="a" bar="b" baz="c" foo="a" bar="b" baz="c" foo="a" bar="b" baz="c"/&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":4} -->
<h4 id="212-模板中使用简单的表达式">2.1.2. 模板中使用简单的表达式<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#212-%E6%A8%A1%E6%9D%BF%E4%B8%AD%E4%BD%BF%E7%94%A8%E7%AE%80%E5%8D%95%E7%9A%84%E8%A1%A8%E8%BE%BE%E5%BC%8F"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>组件模板应该只包含简单的表达式，复杂的表达式则应该重构为计算属性或方法。复杂表达式会让你的模板变得不那么声明式。我们应该尽量描述应该出现的是什么，而非如何计算那个值。而且计算属性和方法使得代码可以重用。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;template&gt;
  &lt;p&gt;{{ normalizedFullName }}&lt;/p&gt;
&lt;/template&gt;

// 复杂表达式已经移入一个计算属性
computed: {
  normalizedFullName: function () {
    return this.fullName.split(' ').map(function (word) {
      return word&#91;0].toUpperCase() + word.slice(1)
    }).join(' ')
  }
}
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":4} -->
<h4 id="213-指令都使用缩写形式">2.1.3 指令都使用缩写形式<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#213-%E6%8C%87%E4%BB%A4%E9%83%BD%E4%BD%BF%E7%94%A8%E7%BC%A9%E5%86%99%E5%BD%A2%E5%BC%8F"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>指令推荐都使用缩写形式，(用 : 表示 v-bind: 、用 @ 表示 v-on: 和用 # 表示 v-slot:)</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;input
  @input="onInput"
  @focus="onFocus"
&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;input
  v-on:input="onInput"
  @focus="onFocus"
&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":4} -->
<h4 id="214-标签顺序保持一致">2.1.4 标签顺序保持一致<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#214-%E6%A0%87%E7%AD%BE%E9%A1%BA%E5%BA%8F%E4%BF%9D%E6%8C%81%E4%B8%80%E8%87%B4"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>单文件组件应该总是让标签顺序保持为</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;template&gt;...&lt;/template&gt;
&lt;script&gt;...&lt;/script&gt;
&lt;style&gt;...&lt;/style&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>反例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>&lt;template&gt;...&lt;/template&gt;
&lt;style&gt;...&lt;/style&gt;
&lt;script&gt;...&lt;/script&gt;
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":4} -->
<h4 id="215-必须为-v-for-设置键值-key">2.1.5 必须为 v-for 设置键值 key<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#215-%E5%BF%85%E9%A1%BB%E4%B8%BA-v-for-%E8%AE%BE%E7%BD%AE%E9%94%AE%E5%80%BC-key"></a></h4>
<!-- /wp:heading -->

<!-- wp:heading {"level":4} -->
<h4 id="216-v-show-与-v-if-选择">2.1.6 v-show 与 v-if 选择<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#216-v-show-%E4%B8%8E-v-if-%E9%80%89%E6%8B%A9"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>如果运行时，需要非常频繁地切换，使用 v-show ；如果在运行时，条件很少改变，使用 v-if。</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="217-script-标签内部结构顺序">2.1.7 script 标签内部结构顺序<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#217-script-%E6%A0%87%E7%AD%BE%E5%86%85%E9%83%A8%E7%BB%93%E6%9E%84%E9%A1%BA%E5%BA%8F"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>components &gt; props &gt; data &gt; computed &gt; watch &gt; filter &gt; 钩子函数（钩子函数按其执行顺序） &gt; methods</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="218-vue-router-规范">2.1.8 Vue Router 规范<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#218-vue-router-%E8%A7%84%E8%8C%83"></a></h4>
<!-- /wp:heading -->

<!-- wp:heading {"level":5} -->
<h5 id="1-页面跳转数据传递使用路由参数">1) 页面跳转数据传递使用路由参数<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#1-%E9%A1%B5%E9%9D%A2%E8%B7%B3%E8%BD%AC%E6%95%B0%E6%8D%AE%E4%BC%A0%E9%80%92%E4%BD%BF%E7%94%A8%E8%B7%AF%E7%94%B1%E5%8F%82%E6%95%B0"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>页面跳转，例如 A 页面跳转到 B 页面，需要将 A 页面的数据传递到 B 页面，推荐使用 路由参数进行传参，而不是将需要传递的数据保存 vuex，然后在 B 页面取出 vuex 的数据，因为如果在 B 页面刷新会导致 vuex 数据丢失，导致 B 页面无法正常显示数据。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>let id = ' 123';
this.$router.push({ name: 'userCenter', query: { id: id } });
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="2-使用路由懒加载（延迟加载）机制">2) 使用路由懒加载（延迟加载）机制<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#2-%E4%BD%BF%E7%94%A8%E8%B7%AF%E7%94%B1%E6%87%92%E5%8A%A0%E8%BD%BD%EF%BC%88%E5%BB%B6%E8%BF%9F%E5%8A%A0%E8%BD%BD%EF%BC%89%E6%9C%BA%E5%88%B6"></a></h5>
<!-- /wp:heading -->

<!-- wp:code -->
<pre class="wp-block-code"><code>    {
        path: '/uploadAttachment',
        name: 'uploadAttachment',
        meta: {
          title: '上传附件'
        },
        component: () =&gt; import('@/view/components/uploadAttachment/index.vue')
      },
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="3-router-中的命名规范">3) router 中的命名规范<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#3-router-%E4%B8%AD%E7%9A%84%E5%91%BD%E5%90%8D%E8%A7%84%E8%8C%83"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>path、childrenPoints 命名规范采用<code>kebab-case</code>命名规范（尽量vue文件的目录结构保持一致，因为目录、文件名都是<code>kebab-case</code>，这样很方便找到对应的文件）</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>name 命名规范采用<code>KebabCase</code>命名规范且和component组件名保持一致！（因为要保持keep-alive特性，keep-alive按照component的name进行缓存，所以两者必须高度保持一致）</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>// 动态加载
export const reload = &#91;
  {
    path: '/reload',
    name: 'reload',
    component: Main,
    meta: {
      title: '动态加载',
      icon: 'icon iconfont'
    },

    children: &#91;
      {
        path: '/reload/smart-reload-list',
        name: 'SmartReloadList',
        meta: {
          title: 'SmartReload',
          childrenPoints: &#91;
            {
              title: '查询',
              name: 'smart-reload-search'
            },
            {
              title: '执行reload',
              name: 'smart-reload-update'
            },
            {
              title: '查看执行结果',
              name: 'smart-reload-result'
            }
          ]
        },
        component: () =&gt;
          import('@/views/reload/smart-reload/smart-reload-list.vue')
      }
    ]
  }
];
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="4-router-中的-path-命名规范">4) router 中的 path 命名规范<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#4-router-%E4%B8%AD%E7%9A%84-path-%E5%91%BD%E5%90%8D%E8%A7%84%E8%8C%83"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>path除了采用<code>kebab-case</code>命名规范以外，必须以 / 开头，即使是children里的path也要以 / 开头。如下示例</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>目的：</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>经常有这样的场景：某个页面有问题，要立刻找到这个vue文件，如果不用以/开头，path为parent和children组成的，可能经常需要在router文件里搜索多次才能找到，而如果以/开头，则能立刻搜索到对应的组件</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>{
    path: '/file',
    name: 'File',
    component: Main,
    meta: {
      title: '文件服务',
      icon: 'ios-cloud-upload'
    },
    children: &#91;
      {
        path: '/file/file-list',
        name: 'FileList',
        component: () =&gt; import('@/views/file/file-list.vue')
      },
      {
        path: '/file/file-add',
        name: 'FileAdd',
        component: () =&gt; import('@/views/file/file-add.vue')
      },
      {
        path: '/file/file-update',
        name: 'FileUpdate',
        component: () =&gt; import('@/views/file/file-update.vue')
      }
    ]
  }
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":3} -->
<h3 id="二-vue-项目目录规范">(二) Vue 项目目录规范<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#%E4%BA%8C-vue-%E9%A1%B9%E7%9B%AE%E7%9B%AE%E5%BD%95%E8%A7%84%E8%8C%83"></a></h3>
<!-- /wp:heading -->

<!-- wp:heading {"level":4} -->
<h4 id="221-基础">2.2.1 基础<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#221-%E5%9F%BA%E7%A1%80"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>vue 项目中的所有命名一定要与后端命名统一。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>比如权限：后端 privilege, 前端无论 router , store, api 等都必须使用 privielege 单词！</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="222-使用-vue-cli-脚手架">2.2.2 使用 Vue-cli 脚手架<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#222-%E4%BD%BF%E7%94%A8-vue-cli-%E8%84%9A%E6%89%8B%E6%9E%B6"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>使用 vue-cli3 来初始化项目，项目名按照上面的命名规范。</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":4} -->
<h4 id="223-目录说明">2.2.3 目录说明<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#223-%E7%9B%AE%E5%BD%95%E8%AF%B4%E6%98%8E"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>目录名按照上面的命名规范，其中 components 组件用大写驼峰，其余除 components 组件目录外的所有目录均使用 kebab-case 命名。</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>src                               源码目录
|-- api                              所有api接口
|-- assets                           静态资源，images, icons, styles等
|-- components                       公用组件
|-- config                           配置信息
|-- constants                        常量信息，项目所有Enum, 全局常量等
|-- directives                       自定义指令
|-- filters                          过滤器，全局工具
|-- datas                            模拟数据，临时存放
|-- lib                              外部引用的插件存放及修改文件
|-- mock                             模拟接口，临时存放
|-- plugins                          插件，全局使用
|-- router                           路由，统一管理
|-- store                            vuex, 统一管理
|-- themes                           自定义样式主题
|-- views                            视图目录
|   |-- role                             role模块名
|   |-- |-- role-list.vue                    role列表页面
|   |-- |-- role-add.vue                     role新建页面
|   |-- |-- role-update.vue                  role更新页面
|   |-- |-- index.less                      role模块样式
|   |-- |-- components                      role模块通用组件文件夹
|   |-- employee                         employee模块
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="1-api-目录">1) api 目录<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#1-api-%E7%9B%AE%E5%BD%95"></a></h5>
<!-- /wp:heading -->

<!-- wp:list -->
<ul><!-- wp:list-item -->
<li>文件、变量命名要与后端保持一致。</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>此目录对应后端 API 接口，按照后端一个 controller 一个 api js 文件。若项目较大时，可以按照业务划分子目录，并与后端保持一致。</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>api 中的方法名字要与后端 api url 尽量保持语义高度一致性。</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>对于 api 中的每个方法要添加注释，注释与后端 swagger 文档保持一致。</li>
<!-- /wp:list-item --></ul>
<!-- /wp:list -->

<!-- wp:paragraph -->
<p>正例：</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>后端 url： EmployeeController.java</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>/employee/add
/employee/delete/{id}
/employee/update
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>前端： employee.js</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>  // 添加员工
  addEmployee: (data) =&gt; {
    return postAxios('/employee/add', data)
  },
  // 更新员工信息
  updateEmployee: (data) =&gt; {
    return postAxios('/employee/update', data)
  },
    // 删除员工
  deleteEmployee: (employeeId) =&gt; {
    return postAxios('/employee/delete/' + employeeId)
   },
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="2-assets-目录">2) assets 目录<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#2-assets-%E7%9B%AE%E5%BD%95"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>assets 为静态资源，里面存放 images, styles, icons 等静态资源，静态资源命名格式为 kebab-case</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>|assets
|-- icons
|-- images
|   |-- background-color.png
|   |-- upload-header.png
|-- styles
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="3-components-目录">3) components 目录<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#3-components-%E7%9B%AE%E5%BD%95"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>此目录应按照组件进行目录划分，目录命名为 kebab-case，组件命名规则也为 kebab-case</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>|components
|-- error-log
|   |-- index.vue
|   |-- index.less
|-- markdown-editor
|   |-- index.vue
|   |-- index.js
|-- kebab-case
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="4-constants-目录">4) constants 目录<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#4-constants-%E7%9B%AE%E5%BD%95"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>此目录存放项目所有常量，如果常量在 vue 中使用，请使用 vue-enum 插件(<a href="https://www.npmjs.com/package/vue-enum" target="_blank" rel="noreferrer noopener">https://www.npmjs.com/package/vue-enum</a>)</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>目录结构：</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>|constants
|-- index.js
|-- role.js
|-- employee.js
</code></pre>
<!-- /wp:code -->

<!-- wp:paragraph -->
<p>例子： employee.js</p>
<!-- /wp:paragraph -->

<!-- wp:code -->
<pre class="wp-block-code"><code>export const EMPLOYEE_STATUS = {
  NORMAL: {
    value: 1,
    desc: '正常'
  },
  DISABLED: {
    value: 1,
    desc: '禁用'
  },
  DELETED: {
    value: 2,
    desc: '已删除'
  }
};

export const EMPLOYEE_ACCOUNT_TYPE = {
  QQ: {
    value: 1,
    desc: 'QQ登录'
  },
  WECHAT: {
    value: 2,
    desc: '微信登录'
  },
  DINGDING: {
    value: 3,
    desc: '钉钉登录'
  },
  USERNAME: {
    value: 4,
    desc: '用户名密码登录'
  }
};

export default {
  EMPLOYEE_STATUS,
  EMPLOYEE_ACCOUNT_TYPE
};
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":5} -->
<h5 id="5-router-与-store-目录">5) router 与 store 目录<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#5-router-%E4%B8%8E-store-%E7%9B%AE%E5%BD%95"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>这两个目录一定要将业务进行拆分，不能放到一个 js 文件里。</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>router 尽量按照 views 中的结构保持一致</p>
<!-- /wp:paragraph -->

<!-- wp:paragraph -->
<p>store 按照业务进行拆分不同的 js 文件</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":5} -->
<h5 id="6-views-目录">6) views 目录<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#6-views-%E7%9B%AE%E5%BD%95"></a></h5>
<!-- /wp:heading -->

<!-- wp:list -->
<ul><!-- wp:list-item -->
<li>命名要与后端、router、api 等保持一致</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>components 中组件要使用 PascalCase 规则</li>
<!-- /wp:list-item --></ul>
<!-- /wp:list -->

<!-- wp:code -->
<pre class="wp-block-code"><code>|-- views                            视图目录
|   |-- role                             role模块名
|   |   |-- role-list.vue                    role列表页面
|   |   |-- role-add.vue                     role新建页面
|   |   |-- role-update.vue                  role更新页面
|   |   |-- index.less                      role模块样式
|   |   |-- components                      role模块通用组件文件夹
|   |   |   |-- role-header.vue                        role头部组件
|   |   |   |-- role-modal.vue                         role弹出框组件
|   |-- employee                         employee模块
|   |-- behavior-log                      行为日志log模块
|   |-- code-generator                    代码生成器模块
</code></pre>
<!-- /wp:code -->

<!-- wp:heading {"level":4} -->
<h4 id="224-注释说明">2.2.4 注释说明<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#224-%E6%B3%A8%E9%87%8A%E8%AF%B4%E6%98%8E"></a></h4>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>整理必须加注释的地方</p>
<!-- /wp:paragraph -->

<!-- wp:list -->
<ul><!-- wp:list-item -->
<li>公共组件使用说明</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>api 目录的接口 js 文件必须加注释</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>store 中的 state, mutation, action 等必须加注释</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>vue 文件中的 template 必须加注释，若文件较大添加 start end 注释</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>vue 文件的 methods，每个 method 必须添加注释</li>
<!-- /wp:list-item -->

<!-- wp:list-item -->
<li>vue 文件的 data, 非常见单词要加注释</li>
<!-- /wp:list-item --></ul>
<!-- /wp:list -->

<!-- wp:heading {"level":4} -->
<h4 id="225-其他">2.2.5 其他<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#225-%E5%85%B6%E4%BB%96"></a></h4>
<!-- /wp:heading -->

<!-- wp:heading {"level":5} -->
<h5 id="1-尽量不要手动操作-dom">1) 尽量不要手动操作 DOM<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#1-%E5%B0%BD%E9%87%8F%E4%B8%8D%E8%A6%81%E6%89%8B%E5%8A%A8%E6%93%8D%E4%BD%9C-dom"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>因使用 vue 框架，所以在项目开发中尽量使用 vue 的数据驱动更新 DOM，尽量（不到万不得已）不要手动操作 DOM，包括：增删改 dom 元素、以及更改样式、添加事件等。</p>
<!-- /wp:paragraph -->

<!-- wp:heading {"level":5} -->
<h5 id="2-删除无用代码">2) 删除无用代码<a href="https://docs.pickmall.cn/development/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.html#2-%E5%88%A0%E9%99%A4%E6%97%A0%E7%94%A8%E4%BB%A3%E7%A0%81"></a></h5>
<!-- /wp:heading -->

<!-- wp:paragraph -->
<p>因使用了 git/svn 等代码版本工具，对于无用代码必须及时删除，例如：一些调试的 console 语句、无用的弃用功能代码。</p>
<!-- /wp:paragraph -->