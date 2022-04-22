package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysQiniuConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysQiniuConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAccessKeyIsNull() {
            addCriterion("access_key is null");
            return (Criteria) this;
        }

        public Criteria andAccessKeyIsNotNull() {
            addCriterion("access_key is not null");
            return (Criteria) this;
        }

        public Criteria andAccessKeyEqualTo(String value) {
            addCriterion("access_key =", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyNotEqualTo(String value) {
            addCriterion("access_key <>", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyGreaterThan(String value) {
            addCriterion("access_key >", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyGreaterThanOrEqualTo(String value) {
            addCriterion("access_key >=", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyLessThan(String value) {
            addCriterion("access_key <", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyLessThanOrEqualTo(String value) {
            addCriterion("access_key <=", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyLike(String value) {
            addCriterion("access_key like", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyNotLike(String value) {
            addCriterion("access_key not like", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyIn(List<String> values) {
            addCriterion("access_key in", values, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyNotIn(List<String> values) {
            addCriterion("access_key not in", values, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyBetween(String value1, String value2) {
            addCriterion("access_key between", value1, value2, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyNotBetween(String value1, String value2) {
            addCriterion("access_key not between", value1, value2, "accessKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyIsNull() {
            addCriterion("secret_key is null");
            return (Criteria) this;
        }

        public Criteria andSecretKeyIsNotNull() {
            addCriterion("secret_key is not null");
            return (Criteria) this;
        }

        public Criteria andSecretKeyEqualTo(String value) {
            addCriterion("secret_key =", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotEqualTo(String value) {
            addCriterion("secret_key <>", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyGreaterThan(String value) {
            addCriterion("secret_key >", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyGreaterThanOrEqualTo(String value) {
            addCriterion("secret_key >=", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyLessThan(String value) {
            addCriterion("secret_key <", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyLessThanOrEqualTo(String value) {
            addCriterion("secret_key <=", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyLike(String value) {
            addCriterion("secret_key like", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotLike(String value) {
            addCriterion("secret_key not like", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyIn(List<String> values) {
            addCriterion("secret_key in", values, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotIn(List<String> values) {
            addCriterion("secret_key not in", values, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyBetween(String value1, String value2) {
            addCriterion("secret_key between", value1, value2, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotBetween(String value1, String value2) {
            addCriterion("secret_key not between", value1, value2, "secretKey");
            return (Criteria) this;
        }

        public Criteria andImgZoneIsNull() {
            addCriterion("img_zone is null");
            return (Criteria) this;
        }

        public Criteria andImgZoneIsNotNull() {
            addCriterion("img_zone is not null");
            return (Criteria) this;
        }

        public Criteria andImgZoneEqualTo(String value) {
            addCriterion("img_zone =", value, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneNotEqualTo(String value) {
            addCriterion("img_zone <>", value, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneGreaterThan(String value) {
            addCriterion("img_zone >", value, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneGreaterThanOrEqualTo(String value) {
            addCriterion("img_zone >=", value, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneLessThan(String value) {
            addCriterion("img_zone <", value, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneLessThanOrEqualTo(String value) {
            addCriterion("img_zone <=", value, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneLike(String value) {
            addCriterion("img_zone like", value, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneNotLike(String value) {
            addCriterion("img_zone not like", value, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneIn(List<String> values) {
            addCriterion("img_zone in", values, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneNotIn(List<String> values) {
            addCriterion("img_zone not in", values, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneBetween(String value1, String value2) {
            addCriterion("img_zone between", value1, value2, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgZoneNotBetween(String value1, String value2) {
            addCriterion("img_zone not between", value1, value2, "imgZone");
            return (Criteria) this;
        }

        public Criteria andImgBucketIsNull() {
            addCriterion("img_bucket is null");
            return (Criteria) this;
        }

        public Criteria andImgBucketIsNotNull() {
            addCriterion("img_bucket is not null");
            return (Criteria) this;
        }

        public Criteria andImgBucketEqualTo(String value) {
            addCriterion("img_bucket =", value, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketNotEqualTo(String value) {
            addCriterion("img_bucket <>", value, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketGreaterThan(String value) {
            addCriterion("img_bucket >", value, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketGreaterThanOrEqualTo(String value) {
            addCriterion("img_bucket >=", value, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketLessThan(String value) {
            addCriterion("img_bucket <", value, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketLessThanOrEqualTo(String value) {
            addCriterion("img_bucket <=", value, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketLike(String value) {
            addCriterion("img_bucket like", value, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketNotLike(String value) {
            addCriterion("img_bucket not like", value, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketIn(List<String> values) {
            addCriterion("img_bucket in", values, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketNotIn(List<String> values) {
            addCriterion("img_bucket not in", values, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketBetween(String value1, String value2) {
            addCriterion("img_bucket between", value1, value2, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgBucketNotBetween(String value1, String value2) {
            addCriterion("img_bucket not between", value1, value2, "imgBucket");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlIsNull() {
            addCriterion("img_view_url is null");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlIsNotNull() {
            addCriterion("img_view_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlEqualTo(String value) {
            addCriterion("img_view_url =", value, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlNotEqualTo(String value) {
            addCriterion("img_view_url <>", value, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlGreaterThan(String value) {
            addCriterion("img_view_url >", value, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_view_url >=", value, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlLessThan(String value) {
            addCriterion("img_view_url <", value, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlLessThanOrEqualTo(String value) {
            addCriterion("img_view_url <=", value, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlLike(String value) {
            addCriterion("img_view_url like", value, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlNotLike(String value) {
            addCriterion("img_view_url not like", value, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlIn(List<String> values) {
            addCriterion("img_view_url in", values, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlNotIn(List<String> values) {
            addCriterion("img_view_url not in", values, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlBetween(String value1, String value2) {
            addCriterion("img_view_url between", value1, value2, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andImgViewUrlNotBetween(String value1, String value2) {
            addCriterion("img_view_url not between", value1, value2, "imgViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoZoneIsNull() {
            addCriterion("video_zone is null");
            return (Criteria) this;
        }

        public Criteria andVideoZoneIsNotNull() {
            addCriterion("video_zone is not null");
            return (Criteria) this;
        }

        public Criteria andVideoZoneEqualTo(String value) {
            addCriterion("video_zone =", value, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneNotEqualTo(String value) {
            addCriterion("video_zone <>", value, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneGreaterThan(String value) {
            addCriterion("video_zone >", value, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneGreaterThanOrEqualTo(String value) {
            addCriterion("video_zone >=", value, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneLessThan(String value) {
            addCriterion("video_zone <", value, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneLessThanOrEqualTo(String value) {
            addCriterion("video_zone <=", value, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneLike(String value) {
            addCriterion("video_zone like", value, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneNotLike(String value) {
            addCriterion("video_zone not like", value, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneIn(List<String> values) {
            addCriterion("video_zone in", values, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneNotIn(List<String> values) {
            addCriterion("video_zone not in", values, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneBetween(String value1, String value2) {
            addCriterion("video_zone between", value1, value2, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoZoneNotBetween(String value1, String value2) {
            addCriterion("video_zone not between", value1, value2, "videoZone");
            return (Criteria) this;
        }

        public Criteria andVideoBucketIsNull() {
            addCriterion("video_bucket is null");
            return (Criteria) this;
        }

        public Criteria andVideoBucketIsNotNull() {
            addCriterion("video_bucket is not null");
            return (Criteria) this;
        }

        public Criteria andVideoBucketEqualTo(String value) {
            addCriterion("video_bucket =", value, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketNotEqualTo(String value) {
            addCriterion("video_bucket <>", value, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketGreaterThan(String value) {
            addCriterion("video_bucket >", value, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketGreaterThanOrEqualTo(String value) {
            addCriterion("video_bucket >=", value, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketLessThan(String value) {
            addCriterion("video_bucket <", value, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketLessThanOrEqualTo(String value) {
            addCriterion("video_bucket <=", value, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketLike(String value) {
            addCriterion("video_bucket like", value, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketNotLike(String value) {
            addCriterion("video_bucket not like", value, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketIn(List<String> values) {
            addCriterion("video_bucket in", values, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketNotIn(List<String> values) {
            addCriterion("video_bucket not in", values, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketBetween(String value1, String value2) {
            addCriterion("video_bucket between", value1, value2, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoBucketNotBetween(String value1, String value2) {
            addCriterion("video_bucket not between", value1, value2, "videoBucket");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlIsNull() {
            addCriterion("video_view_url is null");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlIsNotNull() {
            addCriterion("video_view_url is not null");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlEqualTo(String value) {
            addCriterion("video_view_url =", value, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlNotEqualTo(String value) {
            addCriterion("video_view_url <>", value, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlGreaterThan(String value) {
            addCriterion("video_view_url >", value, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlGreaterThanOrEqualTo(String value) {
            addCriterion("video_view_url >=", value, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlLessThan(String value) {
            addCriterion("video_view_url <", value, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlLessThanOrEqualTo(String value) {
            addCriterion("video_view_url <=", value, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlLike(String value) {
            addCriterion("video_view_url like", value, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlNotLike(String value) {
            addCriterion("video_view_url not like", value, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlIn(List<String> values) {
            addCriterion("video_view_url in", values, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlNotIn(List<String> values) {
            addCriterion("video_view_url not in", values, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlBetween(String value1, String value2) {
            addCriterion("video_view_url between", value1, value2, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andVideoViewUrlNotBetween(String value1, String value2) {
            addCriterion("video_view_url not between", value1, value2, "videoViewUrl");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddByIsNull() {
            addCriterion("add_by is null");
            return (Criteria) this;
        }

        public Criteria andAddByIsNotNull() {
            addCriterion("add_by is not null");
            return (Criteria) this;
        }

        public Criteria andAddByEqualTo(String value) {
            addCriterion("add_by =", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotEqualTo(String value) {
            addCriterion("add_by <>", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByGreaterThan(String value) {
            addCriterion("add_by >", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByGreaterThanOrEqualTo(String value) {
            addCriterion("add_by >=", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByLessThan(String value) {
            addCriterion("add_by <", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByLessThanOrEqualTo(String value) {
            addCriterion("add_by <=", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByLike(String value) {
            addCriterion("add_by like", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotLike(String value) {
            addCriterion("add_by not like", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByIn(List<String> values) {
            addCriterion("add_by in", values, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotIn(List<String> values) {
            addCriterion("add_by not in", values, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByBetween(String value1, String value2) {
            addCriterion("add_by between", value1, value2, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotBetween(String value1, String value2) {
            addCriterion("add_by not between", value1, value2, "addBy");
            return (Criteria) this;
        }

        public Criteria andUpdTimeIsNull() {
            addCriterion("upd_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdTimeIsNotNull() {
            addCriterion("upd_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdTimeEqualTo(Date value) {
            addCriterion("upd_time =", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeNotEqualTo(Date value) {
            addCriterion("upd_time <>", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeGreaterThan(Date value) {
            addCriterion("upd_time >", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upd_time >=", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeLessThan(Date value) {
            addCriterion("upd_time <", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeLessThanOrEqualTo(Date value) {
            addCriterion("upd_time <=", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeIn(List<Date> values) {
            addCriterion("upd_time in", values, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeNotIn(List<Date> values) {
            addCriterion("upd_time not in", values, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeBetween(Date value1, Date value2) {
            addCriterion("upd_time between", value1, value2, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeNotBetween(Date value1, Date value2) {
            addCriterion("upd_time not between", value1, value2, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdByIsNull() {
            addCriterion("upd_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdByIsNotNull() {
            addCriterion("upd_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdByEqualTo(String value) {
            addCriterion("upd_by =", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByNotEqualTo(String value) {
            addCriterion("upd_by <>", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByGreaterThan(String value) {
            addCriterion("upd_by >", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByGreaterThanOrEqualTo(String value) {
            addCriterion("upd_by >=", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByLessThan(String value) {
            addCriterion("upd_by <", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByLessThanOrEqualTo(String value) {
            addCriterion("upd_by <=", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByLike(String value) {
            addCriterion("upd_by like", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByNotLike(String value) {
            addCriterion("upd_by not like", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByIn(List<String> values) {
            addCriterion("upd_by in", values, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByNotIn(List<String> values) {
            addCriterion("upd_by not in", values, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByBetween(String value1, String value2) {
            addCriterion("upd_by between", value1, value2, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByNotBetween(String value1, String value2) {
            addCriterion("upd_by not between", value1, value2, "updBy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}