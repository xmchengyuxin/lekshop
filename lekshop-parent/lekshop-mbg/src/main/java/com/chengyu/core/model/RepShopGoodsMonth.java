package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepShopGoodsMonth implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "商家ID")
    private Integer shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "统计月份")
    private String month;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    private String goodsName;

    @ApiModelProperty(value = "主图")
    private String goodsMainImg;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "分类ID")
    private Integer cateId;

    @ApiModelProperty(value = "分类名称")
    private String cateName;

    @ApiModelProperty(value = "销售数量")
    private Integer sellNum;

    @ApiModelProperty(value = "销售金额")
    private BigDecimal sellAmount;

    @ApiModelProperty(value = "好评数量")
    private Integer goodCommentNum;

    @ApiModelProperty(value = "中评数量")
    private Integer mediumCommentNum;

    @ApiModelProperty(value = "差评数量")
    private Integer badCommentNum;

    @ApiModelProperty(value = "复购率(购买两次以上人数/购买总人数)")
    private BigDecimal rebuyRate;

    @ApiModelProperty(value = "购买2次以上人数")
    private Integer buyTwoMannum;

    @ApiModelProperty(value = "购买总人数")
    private Integer buyMannum;

    @ApiModelProperty(value = "复购销量")
    private Integer rebuyNum;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsMainImg() {
        return goodsMainImg;
    }

    public void setGoodsMainImg(String goodsMainImg) {
        this.goodsMainImg = goodsMainImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Integer getSellNum() {
        return sellNum;
    }

    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    public BigDecimal getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(BigDecimal sellAmount) {
        this.sellAmount = sellAmount;
    }

    public Integer getGoodCommentNum() {
        return goodCommentNum;
    }

    public void setGoodCommentNum(Integer goodCommentNum) {
        this.goodCommentNum = goodCommentNum;
    }

    public Integer getMediumCommentNum() {
        return mediumCommentNum;
    }

    public void setMediumCommentNum(Integer mediumCommentNum) {
        this.mediumCommentNum = mediumCommentNum;
    }

    public Integer getBadCommentNum() {
        return badCommentNum;
    }

    public void setBadCommentNum(Integer badCommentNum) {
        this.badCommentNum = badCommentNum;
    }

    public BigDecimal getRebuyRate() {
        return rebuyRate;
    }

    public void setRebuyRate(BigDecimal rebuyRate) {
        this.rebuyRate = rebuyRate;
    }

    public Integer getBuyTwoMannum() {
        return buyTwoMannum;
    }

    public void setBuyTwoMannum(Integer buyTwoMannum) {
        this.buyTwoMannum = buyTwoMannum;
    }

    public Integer getBuyMannum() {
        return buyMannum;
    }

    public void setBuyMannum(Integer buyMannum) {
        this.buyMannum = buyMannum;
    }

    public Integer getRebuyNum() {
        return rebuyNum;
    }

    public void setRebuyNum(Integer rebuyNum) {
        this.rebuyNum = rebuyNum;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", month=").append(month);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsMainImg=").append(goodsMainImg);
        sb.append(", price=").append(price);
        sb.append(", cateId=").append(cateId);
        sb.append(", cateName=").append(cateName);
        sb.append(", sellNum=").append(sellNum);
        sb.append(", sellAmount=").append(sellAmount);
        sb.append(", goodCommentNum=").append(goodCommentNum);
        sb.append(", mediumCommentNum=").append(mediumCommentNum);
        sb.append(", badCommentNum=").append(badCommentNum);
        sb.append(", rebuyRate=").append(rebuyRate);
        sb.append(", buyTwoMannum=").append(buyTwoMannum);
        sb.append(", buyMannum=").append(buyMannum);
        sb.append(", rebuyNum=").append(rebuyNum);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}