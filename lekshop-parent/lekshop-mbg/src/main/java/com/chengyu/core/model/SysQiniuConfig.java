package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SysQiniuConfig implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "AK")
    private String accessKey;

    @ApiModelProperty(value = "SK")
    private String secretKey;

    @ApiModelProperty(value = "七牛存储区域")
    private String imgZone;

    @ApiModelProperty(value = "空间名称")
    private String imgBucket;

    @ApiModelProperty(value = "下载路径")
    private String imgViewUrl;

    @ApiModelProperty(value = "视频存储区域")
    private String videoZone;

    @ApiModelProperty(value = "空间名称")
    private String videoBucket;

    @ApiModelProperty(value = "下载路径")
    private String videoViewUrl;

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

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getImgZone() {
        return imgZone;
    }

    public void setImgZone(String imgZone) {
        this.imgZone = imgZone;
    }

    public String getImgBucket() {
        return imgBucket;
    }

    public void setImgBucket(String imgBucket) {
        this.imgBucket = imgBucket;
    }

    public String getImgViewUrl() {
        return imgViewUrl;
    }

    public void setImgViewUrl(String imgViewUrl) {
        this.imgViewUrl = imgViewUrl;
    }

    public String getVideoZone() {
        return videoZone;
    }

    public void setVideoZone(String videoZone) {
        this.videoZone = videoZone;
    }

    public String getVideoBucket() {
        return videoBucket;
    }

    public void setVideoBucket(String videoBucket) {
        this.videoBucket = videoBucket;
    }

    public String getVideoViewUrl() {
        return videoViewUrl;
    }

    public void setVideoViewUrl(String videoViewUrl) {
        this.videoViewUrl = videoViewUrl;
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
        sb.append(", accessKey=").append(accessKey);
        sb.append(", secretKey=").append(secretKey);
        sb.append(", imgZone=").append(imgZone);
        sb.append(", imgBucket=").append(imgBucket);
        sb.append(", imgViewUrl=").append(imgViewUrl);
        sb.append(", videoZone=").append(videoZone);
        sb.append(", videoBucket=").append(videoBucket);
        sb.append(", videoViewUrl=").append(videoViewUrl);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}