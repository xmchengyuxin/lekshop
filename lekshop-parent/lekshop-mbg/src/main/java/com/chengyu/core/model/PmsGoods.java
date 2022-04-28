package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PmsGoods implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "商家ID")
    private Integer shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "一级分类ID")
    private Integer catePid;

    @ApiModelProperty(value = "二级分类ID")
    private Integer cateTid;

    @ApiModelProperty(value = "分类ID")
    private Integer cateId;

    @ApiModelProperty(value = "店铺自建分类ID")
    private Integer shopCateId;

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

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "0待审核>>1上架>>2仓库>>3删除>>4驳回")
    private Integer status;

    @ApiModelProperty(value = "库存计算方式>>1下单减库存>>2付款减库存")
    private Integer stockType;

    @ApiModelProperty(value = "运费模板ID")
    private Integer freightTemplateId;

    @ApiModelProperty(value = "点击数量")
    private Integer pointNum;

    @ApiModelProperty(value = "销量")
    private Integer sellNum;

    @ApiModelProperty(value = "付款数量")
    private Integer payNum;

    @ApiModelProperty(value = "评论数量")
    private Integer commentNum;

    @ApiModelProperty(value = "好评数量")
    private Integer goodCommentNum;

    @ApiModelProperty(value = "差评数量")
    private Integer badCommentNum;

    @ApiModelProperty(value = "收藏数量")
    private Integer collectionNum;

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

    @ApiModelProperty(value = "秒杀开始时间")
    private Date seckillBeginTime;

    @ApiModelProperty(value = "秒杀结束时间")
    private Date seckillEndTime;

    @ApiModelProperty(value = "秒杀限购数量")
    private Integer seckillLimitNum;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    @ApiModelProperty(value = "详情")
    private String detail;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getCatePid() {
        return catePid;
    }

    public void setCatePid(Integer catePid) {
        this.catePid = catePid;
    }

    public Integer getCateTid() {
        return cateTid;
    }

    public void setCateTid(Integer cateTid) {
        this.cateTid = cateTid;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getShopCateId() {
        return shopCateId;
    }

    public void setShopCateId(Integer shopCateId) {
        this.shopCateId = shopCateId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getMainVideo() {
        return mainVideo;
    }

    public void setMainVideo(String mainVideo) {
        this.mainVideo = mainVideo;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public Integer getFreightTemplateId() {
        return freightTemplateId;
    }

    public void setFreightTemplateId(Integer freightTemplateId) {
        this.freightTemplateId = freightTemplateId;
    }

    public Integer getPointNum() {
        return pointNum;
    }

    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
    }

    public Integer getSellNum() {
        return sellNum;
    }

    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    public Integer getPayNum() {
        return payNum;
    }

    public void setPayNum(Integer payNum) {
        this.payNum = payNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getGoodCommentNum() {
        return goodCommentNum;
    }

    public void setGoodCommentNum(Integer goodCommentNum) {
        this.goodCommentNum = goodCommentNum;
    }

    public Integer getBadCommentNum() {
        return badCommentNum;
    }

    public void setBadCommentNum(Integer badCommentNum) {
        this.badCommentNum = badCommentNum;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public Integer getGroupLimitHours() {
        return groupLimitHours;
    }

    public void setGroupLimitHours(Integer groupLimitHours) {
        this.groupLimitHours = groupLimitHours;
    }

    public Integer getGroupLimitBuy() {
        return groupLimitBuy;
    }

    public void setGroupLimitBuy(Integer groupLimitBuy) {
        this.groupLimitBuy = groupLimitBuy;
    }

    public Integer getGroupSingleBuy() {
        return groupSingleBuy;
    }

    public void setGroupSingleBuy(Integer groupSingleBuy) {
        this.groupSingleBuy = groupSingleBuy;
    }

    public Date getSeckillBeginTime() {
        return seckillBeginTime;
    }

    public void setSeckillBeginTime(Date seckillBeginTime) {
        this.seckillBeginTime = seckillBeginTime;
    }

    public Date getSeckillEndTime() {
        return seckillEndTime;
    }

    public void setSeckillEndTime(Date seckillEndTime) {
        this.seckillEndTime = seckillEndTime;
    }

    public Integer getSeckillLimitNum() {
        return seckillLimitNum;
    }

    public void setSeckillLimitNum(Integer seckillLimitNum) {
        this.seckillLimitNum = seckillLimitNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getUpdBy() {
        return updBy;
    }

    public void setUpdBy(String updBy) {
        this.updBy = updBy;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", catePid=").append(catePid);
        sb.append(", cateTid=").append(cateTid);
        sb.append(", cateId=").append(cateId);
        sb.append(", shopCateId=").append(shopCateId);
        sb.append(", type=").append(type);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", mainImg=").append(mainImg);
        sb.append(", mainVideo=").append(mainVideo);
        sb.append(", goodsImg=").append(goodsImg);
        sb.append(", price=").append(price);
        sb.append(", sort=").append(sort);
        sb.append(", weight=").append(weight);
        sb.append(", status=").append(status);
        sb.append(", stockType=").append(stockType);
        sb.append(", freightTemplateId=").append(freightTemplateId);
        sb.append(", pointNum=").append(pointNum);
        sb.append(", sellNum=").append(sellNum);
        sb.append(", payNum=").append(payNum);
        sb.append(", commentNum=").append(commentNum);
        sb.append(", goodCommentNum=").append(goodCommentNum);
        sb.append(", badCommentNum=").append(badCommentNum);
        sb.append(", collectionNum=").append(collectionNum);
        sb.append(", groupType=").append(groupType);
        sb.append(", groupNum=").append(groupNum);
        sb.append(", groupLimitHours=").append(groupLimitHours);
        sb.append(", groupLimitBuy=").append(groupLimitBuy);
        sb.append(", groupSingleBuy=").append(groupSingleBuy);
        sb.append(", seckillBeginTime=").append(seckillBeginTime);
        sb.append(", seckillEndTime=").append(seckillEndTime);
        sb.append(", seckillLimitNum=").append(seckillLimitNum);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", detail=").append(detail);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}