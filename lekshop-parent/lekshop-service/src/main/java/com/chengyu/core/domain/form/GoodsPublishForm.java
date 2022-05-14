package com.chengyu.core.domain.form;

import com.chengyu.core.model.PmsGoodsGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GoodsPublishForm {

	@ApiModelProperty(value = "主键ID")
	private Integer id;

	@ApiModelProperty(value = "一级分类ID")
	private Integer catePid;

	@ApiModelProperty(value = "二级分类ID")
	private Integer cateTid;

	@ApiModelProperty(value = "分类ID")
	private Integer cateId;

	@ApiModelProperty(value = "店铺自建分类ID")
	private Integer shopCateId;

	@ApiModelProperty(value = "商品ID集合")
	private String goodsCateIds;

	@ApiModelProperty(value = "店铺自建分类ID集合")
	private String shopCateIds;

	@ApiModelProperty(value = "1普通商品>>2秒杀商品>>3拼团商品")
	private Integer type;

	@ApiModelProperty(value = "商品标题")
	private String title;

	@ApiModelProperty(value = "产品描述")
	private String description;

	@ApiModelProperty(value = "主图")
	private String mainImg;

	@ApiModelProperty(value = "视频")
	private String mainVideo;

	@ApiModelProperty(value = "商品图片")
	private String goodsImg;

	@ApiModelProperty(value = "库存计算方式>>1下单减库存>>2付款减库存")
	private Integer stockType;

	@ApiModelProperty(value = "运费模板ID")
	private Integer freightTemplateId;

	@ApiModelProperty(value = "1普通拼团>>2阶梯拼团")
	private Integer groupType;

	@ApiModelProperty(value = "成团人数")
	private Integer groupNum;

	@ApiModelProperty(value = "成团限时")
	private Integer groupLimitHours;

	@ApiModelProperty(value = "个人限购次数")
	private Integer groupLimitBuy;

	@ApiModelProperty(value = "0不支持单买>>1支持单买")
	private Integer groupSingleBuy;

	@ApiModelProperty(value = "阶梯拼团设置")
	private List<PmsGoodsGroup> groupList;

	@ApiModelProperty(value = "秒杀开始时间")
	private Date seckillBeginTime;

	@ApiModelProperty(value = "秒杀结束时间")
	private Date seckillEndTime;

	@ApiModelProperty(value = "秒杀限购数量")
	private Integer seckillLimitNum;

	@ApiModelProperty(value = "详情")
	private String detail;

	@ApiModelProperty(value = "1上架>>2仓库")
	private Integer status;

	@ApiModelProperty(value = "SKU参数")
	private String attrKeyList;

	@ApiModelProperty(value = "SKU值")
	private String skuList;
}