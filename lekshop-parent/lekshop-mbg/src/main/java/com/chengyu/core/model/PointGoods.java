package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PointGoods implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "商品编号")
    private String goodsCode;

    @ApiModelProperty(value = "一级分类ID")
    private Integer catePid;

    @ApiModelProperty(value = "二级分类ID")
    private Integer cateTid;

    @ApiModelProperty(value = "分类ID")
    private Integer cateId;

    private String cateIds;

    @ApiModelProperty(value = "类目名称")
    private String cateName;

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

    @ApiModelProperty(value = "兑换方式>>1积分>>2积分+现金")
    private Integer exchangeMethod;

    @ApiModelProperty(value = "原价")
    private BigDecimal oriPrice;

    @ApiModelProperty(value = "兑换积分")
    private BigDecimal point;

    @ApiModelProperty(value = "兑换现金")
    private BigDecimal price;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "1上架>>2仓库>>3删除")
    private Integer status;

    @ApiModelProperty(value = "库存计算方式>>1下单减库存>>2付款减库存")
    private Integer stockType;

    @ApiModelProperty(value = "限购")
    private Integer limitExchange;

    @ApiModelProperty(value = "库存")
    private Integer stock;

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

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
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

    public String getCateIds() {
        return cateIds;
    }

    public void setCateIds(String cateIds) {
        this.cateIds = cateIds;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
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

    public Integer getExchangeMethod() {
        return exchangeMethod;
    }

    public void setExchangeMethod(Integer exchangeMethod) {
        this.exchangeMethod = exchangeMethod;
    }

    public BigDecimal getOriPrice() {
        return oriPrice;
    }

    public void setOriPrice(BigDecimal oriPrice) {
        this.oriPrice = oriPrice;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
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

    public Integer getLimitExchange() {
        return limitExchange;
    }

    public void setLimitExchange(Integer limitExchange) {
        this.limitExchange = limitExchange;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", catePid=").append(catePid);
        sb.append(", cateTid=").append(cateTid);
        sb.append(", cateId=").append(cateId);
        sb.append(", cateIds=").append(cateIds);
        sb.append(", cateName=").append(cateName);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", mainImg=").append(mainImg);
        sb.append(", mainVideo=").append(mainVideo);
        sb.append(", goodsImg=").append(goodsImg);
        sb.append(", exchangeMethod=").append(exchangeMethod);
        sb.append(", oriPrice=").append(oriPrice);
        sb.append(", point=").append(point);
        sb.append(", price=").append(price);
        sb.append(", sort=").append(sort);
        sb.append(", weight=").append(weight);
        sb.append(", status=").append(status);
        sb.append(", stockType=").append(stockType);
        sb.append(", limitExchange=").append(limitExchange);
        sb.append(", stock=").append(stock);
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