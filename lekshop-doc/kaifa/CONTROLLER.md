# 控制器开发规范
1. 控制器注解引入
    - @RestController：标注类为Restful规范的控制器。
    - @RequestMapping：请求路径，需遵守Restful规范。
2. 设定请求方式
​    - 查询方法：@GetMapping
​    - 添加：@PostMapping
​    - 删除：@DeleteMapping
​ 
3. 入参设计
    - 入参需以lowerCamelCase风格命名。
4. 设定请求参数方式
    - 表单提交：直接使用VO类或具体参数名接收。
    - @PathVariable：路径变量。
    - @RequestParam：参数请求。
5. 校验请求参数
    - 非空：@NotNull(message = "XXX")。
    - 使用注解@Validated，使得参数自动校验生效，它是spring-contex中的注解。
    - 对象可在变量上进行定义。
6. 方法返回
    - 查询、修改、添加返回：操作后的对象。
    - 其他业务操作返回：操作状态。
    - 返回对象：CommonResult.success(返回对象);
    - 操作成功： CommonResult.success("返回内容");
    - 操作失败：CommonResult.fail("返回内容");
7. 自动生成接口文档
    - 使用SwaggerAPI。
    - @Api()：添加在类名之上，标注控制器实现内容。
    - @ApiOperation(value = "XX")：添加方法名上，标注方法实现内容。
    - @ApiImplicitParams、@ApiImplicitParam：添加方法名上，标注参数内容。
    - Controller示例代码：
    ```
    @ApiOperation(value = "商品拼团成员列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsId", value = "商品ID"),
	})
	@ResponseBody
	@GetMapping(value="/goods/getGroupList")
	public CommonResult<CommonPage<OmsOrderGroup>> getGroupList(Integer goodsId, Integer page, Integer pageSize) {
		OrderGroupSearchForm form = new OrderGroupSearchForm();
		form.setGoodsId(goodsId);
		form.setStatus(1);
		List<OmsOrderGroup> groupList = orderGroupService.getOrderGroupList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(groupList));
	}
    ```
    