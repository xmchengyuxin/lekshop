package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PmsGoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PmsGoodsExample() {
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

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Integer value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Integer value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Integer value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Integer value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Integer> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Integer> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andCatePidIsNull() {
            addCriterion("cate_pid is null");
            return (Criteria) this;
        }

        public Criteria andCatePidIsNotNull() {
            addCriterion("cate_pid is not null");
            return (Criteria) this;
        }

        public Criteria andCatePidEqualTo(Integer value) {
            addCriterion("cate_pid =", value, "catePid");
            return (Criteria) this;
        }

        public Criteria andCatePidNotEqualTo(Integer value) {
            addCriterion("cate_pid <>", value, "catePid");
            return (Criteria) this;
        }

        public Criteria andCatePidGreaterThan(Integer value) {
            addCriterion("cate_pid >", value, "catePid");
            return (Criteria) this;
        }

        public Criteria andCatePidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cate_pid >=", value, "catePid");
            return (Criteria) this;
        }

        public Criteria andCatePidLessThan(Integer value) {
            addCriterion("cate_pid <", value, "catePid");
            return (Criteria) this;
        }

        public Criteria andCatePidLessThanOrEqualTo(Integer value) {
            addCriterion("cate_pid <=", value, "catePid");
            return (Criteria) this;
        }

        public Criteria andCatePidIn(List<Integer> values) {
            addCriterion("cate_pid in", values, "catePid");
            return (Criteria) this;
        }

        public Criteria andCatePidNotIn(List<Integer> values) {
            addCriterion("cate_pid not in", values, "catePid");
            return (Criteria) this;
        }

        public Criteria andCatePidBetween(Integer value1, Integer value2) {
            addCriterion("cate_pid between", value1, value2, "catePid");
            return (Criteria) this;
        }

        public Criteria andCatePidNotBetween(Integer value1, Integer value2) {
            addCriterion("cate_pid not between", value1, value2, "catePid");
            return (Criteria) this;
        }

        public Criteria andCateTidIsNull() {
            addCriterion("cate_tid is null");
            return (Criteria) this;
        }

        public Criteria andCateTidIsNotNull() {
            addCriterion("cate_tid is not null");
            return (Criteria) this;
        }

        public Criteria andCateTidEqualTo(Integer value) {
            addCriterion("cate_tid =", value, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateTidNotEqualTo(Integer value) {
            addCriterion("cate_tid <>", value, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateTidGreaterThan(Integer value) {
            addCriterion("cate_tid >", value, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateTidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cate_tid >=", value, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateTidLessThan(Integer value) {
            addCriterion("cate_tid <", value, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateTidLessThanOrEqualTo(Integer value) {
            addCriterion("cate_tid <=", value, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateTidIn(List<Integer> values) {
            addCriterion("cate_tid in", values, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateTidNotIn(List<Integer> values) {
            addCriterion("cate_tid not in", values, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateTidBetween(Integer value1, Integer value2) {
            addCriterion("cate_tid between", value1, value2, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateTidNotBetween(Integer value1, Integer value2) {
            addCriterion("cate_tid not between", value1, value2, "cateTid");
            return (Criteria) this;
        }

        public Criteria andCateIdIsNull() {
            addCriterion("cate_id is null");
            return (Criteria) this;
        }

        public Criteria andCateIdIsNotNull() {
            addCriterion("cate_id is not null");
            return (Criteria) this;
        }

        public Criteria andCateIdEqualTo(Integer value) {
            addCriterion("cate_id =", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdNotEqualTo(Integer value) {
            addCriterion("cate_id <>", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdGreaterThan(Integer value) {
            addCriterion("cate_id >", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cate_id >=", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdLessThan(Integer value) {
            addCriterion("cate_id <", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdLessThanOrEqualTo(Integer value) {
            addCriterion("cate_id <=", value, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdIn(List<Integer> values) {
            addCriterion("cate_id in", values, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdNotIn(List<Integer> values) {
            addCriterion("cate_id not in", values, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdBetween(Integer value1, Integer value2) {
            addCriterion("cate_id between", value1, value2, "cateId");
            return (Criteria) this;
        }

        public Criteria andCateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cate_id not between", value1, value2, "cateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdIsNull() {
            addCriterion("shop_cate_id is null");
            return (Criteria) this;
        }

        public Criteria andShopCateIdIsNotNull() {
            addCriterion("shop_cate_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopCateIdEqualTo(Integer value) {
            addCriterion("shop_cate_id =", value, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdNotEqualTo(Integer value) {
            addCriterion("shop_cate_id <>", value, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdGreaterThan(Integer value) {
            addCriterion("shop_cate_id >", value, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_cate_id >=", value, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdLessThan(Integer value) {
            addCriterion("shop_cate_id <", value, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_cate_id <=", value, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdIn(List<Integer> values) {
            addCriterion("shop_cate_id in", values, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdNotIn(List<Integer> values) {
            addCriterion("shop_cate_id not in", values, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_cate_id between", value1, value2, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andShopCateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_cate_id not between", value1, value2, "shopCateId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andMainImgIsNull() {
            addCriterion("main_img is null");
            return (Criteria) this;
        }

        public Criteria andMainImgIsNotNull() {
            addCriterion("main_img is not null");
            return (Criteria) this;
        }

        public Criteria andMainImgEqualTo(String value) {
            addCriterion("main_img =", value, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgNotEqualTo(String value) {
            addCriterion("main_img <>", value, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgGreaterThan(String value) {
            addCriterion("main_img >", value, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgGreaterThanOrEqualTo(String value) {
            addCriterion("main_img >=", value, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgLessThan(String value) {
            addCriterion("main_img <", value, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgLessThanOrEqualTo(String value) {
            addCriterion("main_img <=", value, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgLike(String value) {
            addCriterion("main_img like", value, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgNotLike(String value) {
            addCriterion("main_img not like", value, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgIn(List<String> values) {
            addCriterion("main_img in", values, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgNotIn(List<String> values) {
            addCriterion("main_img not in", values, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgBetween(String value1, String value2) {
            addCriterion("main_img between", value1, value2, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainImgNotBetween(String value1, String value2) {
            addCriterion("main_img not between", value1, value2, "mainImg");
            return (Criteria) this;
        }

        public Criteria andMainVideoIsNull() {
            addCriterion("main_video is null");
            return (Criteria) this;
        }

        public Criteria andMainVideoIsNotNull() {
            addCriterion("main_video is not null");
            return (Criteria) this;
        }

        public Criteria andMainVideoEqualTo(String value) {
            addCriterion("main_video =", value, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoNotEqualTo(String value) {
            addCriterion("main_video <>", value, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoGreaterThan(String value) {
            addCriterion("main_video >", value, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoGreaterThanOrEqualTo(String value) {
            addCriterion("main_video >=", value, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoLessThan(String value) {
            addCriterion("main_video <", value, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoLessThanOrEqualTo(String value) {
            addCriterion("main_video <=", value, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoLike(String value) {
            addCriterion("main_video like", value, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoNotLike(String value) {
            addCriterion("main_video not like", value, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoIn(List<String> values) {
            addCriterion("main_video in", values, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoNotIn(List<String> values) {
            addCriterion("main_video not in", values, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoBetween(String value1, String value2) {
            addCriterion("main_video between", value1, value2, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andMainVideoNotBetween(String value1, String value2) {
            addCriterion("main_video not between", value1, value2, "mainVideo");
            return (Criteria) this;
        }

        public Criteria andGoodsImgIsNull() {
            addCriterion("goods_img is null");
            return (Criteria) this;
        }

        public Criteria andGoodsImgIsNotNull() {
            addCriterion("goods_img is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsImgEqualTo(String value) {
            addCriterion("goods_img =", value, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgNotEqualTo(String value) {
            addCriterion("goods_img <>", value, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgGreaterThan(String value) {
            addCriterion("goods_img >", value, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgGreaterThanOrEqualTo(String value) {
            addCriterion("goods_img >=", value, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgLessThan(String value) {
            addCriterion("goods_img <", value, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgLessThanOrEqualTo(String value) {
            addCriterion("goods_img <=", value, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgLike(String value) {
            addCriterion("goods_img like", value, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgNotLike(String value) {
            addCriterion("goods_img not like", value, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgIn(List<String> values) {
            addCriterion("goods_img in", values, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgNotIn(List<String> values) {
            addCriterion("goods_img not in", values, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgBetween(String value1, String value2) {
            addCriterion("goods_img between", value1, value2, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andGoodsImgNotBetween(String value1, String value2) {
            addCriterion("goods_img not between", value1, value2, "goodsImg");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStockTypeIsNull() {
            addCriterion("stock_type is null");
            return (Criteria) this;
        }

        public Criteria andStockTypeIsNotNull() {
            addCriterion("stock_type is not null");
            return (Criteria) this;
        }

        public Criteria andStockTypeEqualTo(Integer value) {
            addCriterion("stock_type =", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotEqualTo(Integer value) {
            addCriterion("stock_type <>", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeGreaterThan(Integer value) {
            addCriterion("stock_type >", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_type >=", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeLessThan(Integer value) {
            addCriterion("stock_type <", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeLessThanOrEqualTo(Integer value) {
            addCriterion("stock_type <=", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeIn(List<Integer> values) {
            addCriterion("stock_type in", values, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotIn(List<Integer> values) {
            addCriterion("stock_type not in", values, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeBetween(Integer value1, Integer value2) {
            addCriterion("stock_type between", value1, value2, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_type not between", value1, value2, "stockType");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdIsNull() {
            addCriterion("freight_template_id is null");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdIsNotNull() {
            addCriterion("freight_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdEqualTo(Integer value) {
            addCriterion("freight_template_id =", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdNotEqualTo(Integer value) {
            addCriterion("freight_template_id <>", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdGreaterThan(Integer value) {
            addCriterion("freight_template_id >", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("freight_template_id >=", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdLessThan(Integer value) {
            addCriterion("freight_template_id <", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("freight_template_id <=", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdIn(List<Integer> values) {
            addCriterion("freight_template_id in", values, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdNotIn(List<Integer> values) {
            addCriterion("freight_template_id not in", values, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("freight_template_id between", value1, value2, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("freight_template_id not between", value1, value2, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andPointNumIsNull() {
            addCriterion("point_num is null");
            return (Criteria) this;
        }

        public Criteria andPointNumIsNotNull() {
            addCriterion("point_num is not null");
            return (Criteria) this;
        }

        public Criteria andPointNumEqualTo(Integer value) {
            addCriterion("point_num =", value, "pointNum");
            return (Criteria) this;
        }

        public Criteria andPointNumNotEqualTo(Integer value) {
            addCriterion("point_num <>", value, "pointNum");
            return (Criteria) this;
        }

        public Criteria andPointNumGreaterThan(Integer value) {
            addCriterion("point_num >", value, "pointNum");
            return (Criteria) this;
        }

        public Criteria andPointNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("point_num >=", value, "pointNum");
            return (Criteria) this;
        }

        public Criteria andPointNumLessThan(Integer value) {
            addCriterion("point_num <", value, "pointNum");
            return (Criteria) this;
        }

        public Criteria andPointNumLessThanOrEqualTo(Integer value) {
            addCriterion("point_num <=", value, "pointNum");
            return (Criteria) this;
        }

        public Criteria andPointNumIn(List<Integer> values) {
            addCriterion("point_num in", values, "pointNum");
            return (Criteria) this;
        }

        public Criteria andPointNumNotIn(List<Integer> values) {
            addCriterion("point_num not in", values, "pointNum");
            return (Criteria) this;
        }

        public Criteria andPointNumBetween(Integer value1, Integer value2) {
            addCriterion("point_num between", value1, value2, "pointNum");
            return (Criteria) this;
        }

        public Criteria andPointNumNotBetween(Integer value1, Integer value2) {
            addCriterion("point_num not between", value1, value2, "pointNum");
            return (Criteria) this;
        }

        public Criteria andSellNumIsNull() {
            addCriterion("sell_num is null");
            return (Criteria) this;
        }

        public Criteria andSellNumIsNotNull() {
            addCriterion("sell_num is not null");
            return (Criteria) this;
        }

        public Criteria andSellNumEqualTo(Integer value) {
            addCriterion("sell_num =", value, "sellNum");
            return (Criteria) this;
        }

        public Criteria andSellNumNotEqualTo(Integer value) {
            addCriterion("sell_num <>", value, "sellNum");
            return (Criteria) this;
        }

        public Criteria andSellNumGreaterThan(Integer value) {
            addCriterion("sell_num >", value, "sellNum");
            return (Criteria) this;
        }

        public Criteria andSellNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sell_num >=", value, "sellNum");
            return (Criteria) this;
        }

        public Criteria andSellNumLessThan(Integer value) {
            addCriterion("sell_num <", value, "sellNum");
            return (Criteria) this;
        }

        public Criteria andSellNumLessThanOrEqualTo(Integer value) {
            addCriterion("sell_num <=", value, "sellNum");
            return (Criteria) this;
        }

        public Criteria andSellNumIn(List<Integer> values) {
            addCriterion("sell_num in", values, "sellNum");
            return (Criteria) this;
        }

        public Criteria andSellNumNotIn(List<Integer> values) {
            addCriterion("sell_num not in", values, "sellNum");
            return (Criteria) this;
        }

        public Criteria andSellNumBetween(Integer value1, Integer value2) {
            addCriterion("sell_num between", value1, value2, "sellNum");
            return (Criteria) this;
        }

        public Criteria andSellNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sell_num not between", value1, value2, "sellNum");
            return (Criteria) this;
        }

        public Criteria andPayNumIsNull() {
            addCriterion("pay_num is null");
            return (Criteria) this;
        }

        public Criteria andPayNumIsNotNull() {
            addCriterion("pay_num is not null");
            return (Criteria) this;
        }

        public Criteria andPayNumEqualTo(Integer value) {
            addCriterion("pay_num =", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumNotEqualTo(Integer value) {
            addCriterion("pay_num <>", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumGreaterThan(Integer value) {
            addCriterion("pay_num >", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_num >=", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumLessThan(Integer value) {
            addCriterion("pay_num <", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumLessThanOrEqualTo(Integer value) {
            addCriterion("pay_num <=", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumIn(List<Integer> values) {
            addCriterion("pay_num in", values, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumNotIn(List<Integer> values) {
            addCriterion("pay_num not in", values, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumBetween(Integer value1, Integer value2) {
            addCriterion("pay_num between", value1, value2, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_num not between", value1, value2, "payNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumIsNull() {
            addCriterion("comment_num is null");
            return (Criteria) this;
        }

        public Criteria andCommentNumIsNotNull() {
            addCriterion("comment_num is not null");
            return (Criteria) this;
        }

        public Criteria andCommentNumEqualTo(Integer value) {
            addCriterion("comment_num =", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotEqualTo(Integer value) {
            addCriterion("comment_num <>", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumGreaterThan(Integer value) {
            addCriterion("comment_num >", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_num >=", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumLessThan(Integer value) {
            addCriterion("comment_num <", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumLessThanOrEqualTo(Integer value) {
            addCriterion("comment_num <=", value, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumIn(List<Integer> values) {
            addCriterion("comment_num in", values, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotIn(List<Integer> values) {
            addCriterion("comment_num not in", values, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumBetween(Integer value1, Integer value2) {
            addCriterion("comment_num between", value1, value2, "commentNum");
            return (Criteria) this;
        }

        public Criteria andCommentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_num not between", value1, value2, "commentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumIsNull() {
            addCriterion("good_comment_num is null");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumIsNotNull() {
            addCriterion("good_comment_num is not null");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumEqualTo(Integer value) {
            addCriterion("good_comment_num =", value, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumNotEqualTo(Integer value) {
            addCriterion("good_comment_num <>", value, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumGreaterThan(Integer value) {
            addCriterion("good_comment_num >", value, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("good_comment_num >=", value, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumLessThan(Integer value) {
            addCriterion("good_comment_num <", value, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumLessThanOrEqualTo(Integer value) {
            addCriterion("good_comment_num <=", value, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumIn(List<Integer> values) {
            addCriterion("good_comment_num in", values, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumNotIn(List<Integer> values) {
            addCriterion("good_comment_num not in", values, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumBetween(Integer value1, Integer value2) {
            addCriterion("good_comment_num between", value1, value2, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andGoodCommentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("good_comment_num not between", value1, value2, "goodCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumIsNull() {
            addCriterion("bad_comment_num is null");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumIsNotNull() {
            addCriterion("bad_comment_num is not null");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumEqualTo(Integer value) {
            addCriterion("bad_comment_num =", value, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumNotEqualTo(Integer value) {
            addCriterion("bad_comment_num <>", value, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumGreaterThan(Integer value) {
            addCriterion("bad_comment_num >", value, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bad_comment_num >=", value, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumLessThan(Integer value) {
            addCriterion("bad_comment_num <", value, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumLessThanOrEqualTo(Integer value) {
            addCriterion("bad_comment_num <=", value, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumIn(List<Integer> values) {
            addCriterion("bad_comment_num in", values, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumNotIn(List<Integer> values) {
            addCriterion("bad_comment_num not in", values, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumBetween(Integer value1, Integer value2) {
            addCriterion("bad_comment_num between", value1, value2, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andBadCommentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("bad_comment_num not between", value1, value2, "badCommentNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIsNull() {
            addCriterion("collection_num is null");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIsNotNull() {
            addCriterion("collection_num is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionNumEqualTo(Integer value) {
            addCriterion("collection_num =", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotEqualTo(Integer value) {
            addCriterion("collection_num <>", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumGreaterThan(Integer value) {
            addCriterion("collection_num >", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_num >=", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumLessThan(Integer value) {
            addCriterion("collection_num <", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumLessThanOrEqualTo(Integer value) {
            addCriterion("collection_num <=", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIn(List<Integer> values) {
            addCriterion("collection_num in", values, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotIn(List<Integer> values) {
            addCriterion("collection_num not in", values, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumBetween(Integer value1, Integer value2) {
            addCriterion("collection_num between", value1, value2, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_num not between", value1, value2, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNull() {
            addCriterion("group_type is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNotNull() {
            addCriterion("group_type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeEqualTo(Integer value) {
            addCriterion("group_type =", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotEqualTo(Integer value) {
            addCriterion("group_type <>", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThan(Integer value) {
            addCriterion("group_type >", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_type >=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThan(Integer value) {
            addCriterion("group_type <", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThanOrEqualTo(Integer value) {
            addCriterion("group_type <=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIn(List<Integer> values) {
            addCriterion("group_type in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotIn(List<Integer> values) {
            addCriterion("group_type not in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeBetween(Integer value1, Integer value2) {
            addCriterion("group_type between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("group_type not between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupNumIsNull() {
            addCriterion("group_num is null");
            return (Criteria) this;
        }

        public Criteria andGroupNumIsNotNull() {
            addCriterion("group_num is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNumEqualTo(Integer value) {
            addCriterion("group_num =", value, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumNotEqualTo(Integer value) {
            addCriterion("group_num <>", value, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumGreaterThan(Integer value) {
            addCriterion("group_num >", value, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_num >=", value, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumLessThan(Integer value) {
            addCriterion("group_num <", value, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumLessThanOrEqualTo(Integer value) {
            addCriterion("group_num <=", value, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumIn(List<Integer> values) {
            addCriterion("group_num in", values, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumNotIn(List<Integer> values) {
            addCriterion("group_num not in", values, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumBetween(Integer value1, Integer value2) {
            addCriterion("group_num between", value1, value2, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupNumNotBetween(Integer value1, Integer value2) {
            addCriterion("group_num not between", value1, value2, "groupNum");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursIsNull() {
            addCriterion("group_limit_hours is null");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursIsNotNull() {
            addCriterion("group_limit_hours is not null");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursEqualTo(Integer value) {
            addCriterion("group_limit_hours =", value, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursNotEqualTo(Integer value) {
            addCriterion("group_limit_hours <>", value, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursGreaterThan(Integer value) {
            addCriterion("group_limit_hours >", value, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_limit_hours >=", value, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursLessThan(Integer value) {
            addCriterion("group_limit_hours <", value, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursLessThanOrEqualTo(Integer value) {
            addCriterion("group_limit_hours <=", value, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursIn(List<Integer> values) {
            addCriterion("group_limit_hours in", values, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursNotIn(List<Integer> values) {
            addCriterion("group_limit_hours not in", values, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursBetween(Integer value1, Integer value2) {
            addCriterion("group_limit_hours between", value1, value2, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitHoursNotBetween(Integer value1, Integer value2) {
            addCriterion("group_limit_hours not between", value1, value2, "groupLimitHours");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyIsNull() {
            addCriterion("group_limit_buy is null");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyIsNotNull() {
            addCriterion("group_limit_buy is not null");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyEqualTo(Integer value) {
            addCriterion("group_limit_buy =", value, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyNotEqualTo(Integer value) {
            addCriterion("group_limit_buy <>", value, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyGreaterThan(Integer value) {
            addCriterion("group_limit_buy >", value, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_limit_buy >=", value, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyLessThan(Integer value) {
            addCriterion("group_limit_buy <", value, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyLessThanOrEqualTo(Integer value) {
            addCriterion("group_limit_buy <=", value, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyIn(List<Integer> values) {
            addCriterion("group_limit_buy in", values, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyNotIn(List<Integer> values) {
            addCriterion("group_limit_buy not in", values, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyBetween(Integer value1, Integer value2) {
            addCriterion("group_limit_buy between", value1, value2, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupLimitBuyNotBetween(Integer value1, Integer value2) {
            addCriterion("group_limit_buy not between", value1, value2, "groupLimitBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyIsNull() {
            addCriterion("group_single_buy is null");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyIsNotNull() {
            addCriterion("group_single_buy is not null");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyEqualTo(Integer value) {
            addCriterion("group_single_buy =", value, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyNotEqualTo(Integer value) {
            addCriterion("group_single_buy <>", value, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyGreaterThan(Integer value) {
            addCriterion("group_single_buy >", value, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_single_buy >=", value, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyLessThan(Integer value) {
            addCriterion("group_single_buy <", value, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyLessThanOrEqualTo(Integer value) {
            addCriterion("group_single_buy <=", value, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyIn(List<Integer> values) {
            addCriterion("group_single_buy in", values, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyNotIn(List<Integer> values) {
            addCriterion("group_single_buy not in", values, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyBetween(Integer value1, Integer value2) {
            addCriterion("group_single_buy between", value1, value2, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andGroupSingleBuyNotBetween(Integer value1, Integer value2) {
            addCriterion("group_single_buy not between", value1, value2, "groupSingleBuy");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeIsNull() {
            addCriterion("seckill_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeIsNotNull() {
            addCriterion("seckill_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeEqualTo(Date value) {
            addCriterion("seckill_begin_time =", value, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeNotEqualTo(Date value) {
            addCriterion("seckill_begin_time <>", value, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeGreaterThan(Date value) {
            addCriterion("seckill_begin_time >", value, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("seckill_begin_time >=", value, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeLessThan(Date value) {
            addCriterion("seckill_begin_time <", value, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("seckill_begin_time <=", value, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeIn(List<Date> values) {
            addCriterion("seckill_begin_time in", values, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeNotIn(List<Date> values) {
            addCriterion("seckill_begin_time not in", values, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeBetween(Date value1, Date value2) {
            addCriterion("seckill_begin_time between", value1, value2, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("seckill_begin_time not between", value1, value2, "seckillBeginTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeIsNull() {
            addCriterion("seckill_end_time is null");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeIsNotNull() {
            addCriterion("seckill_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeEqualTo(Date value) {
            addCriterion("seckill_end_time =", value, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeNotEqualTo(Date value) {
            addCriterion("seckill_end_time <>", value, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeGreaterThan(Date value) {
            addCriterion("seckill_end_time >", value, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("seckill_end_time >=", value, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeLessThan(Date value) {
            addCriterion("seckill_end_time <", value, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("seckill_end_time <=", value, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeIn(List<Date> values) {
            addCriterion("seckill_end_time in", values, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeNotIn(List<Date> values) {
            addCriterion("seckill_end_time not in", values, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeBetween(Date value1, Date value2) {
            addCriterion("seckill_end_time between", value1, value2, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("seckill_end_time not between", value1, value2, "seckillEndTime");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumIsNull() {
            addCriterion("seckill_limit_num is null");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumIsNotNull() {
            addCriterion("seckill_limit_num is not null");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumEqualTo(Integer value) {
            addCriterion("seckill_limit_num =", value, "seckillLimitNum");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumNotEqualTo(Integer value) {
            addCriterion("seckill_limit_num <>", value, "seckillLimitNum");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumGreaterThan(Integer value) {
            addCriterion("seckill_limit_num >", value, "seckillLimitNum");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("seckill_limit_num >=", value, "seckillLimitNum");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumLessThan(Integer value) {
            addCriterion("seckill_limit_num <", value, "seckillLimitNum");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumLessThanOrEqualTo(Integer value) {
            addCriterion("seckill_limit_num <=", value, "seckillLimitNum");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumIn(List<Integer> values) {
            addCriterion("seckill_limit_num in", values, "seckillLimitNum");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumNotIn(List<Integer> values) {
            addCriterion("seckill_limit_num not in", values, "seckillLimitNum");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumBetween(Integer value1, Integer value2) {
            addCriterion("seckill_limit_num between", value1, value2, "seckillLimitNum");
            return (Criteria) this;
        }

        public Criteria andSeckillLimitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("seckill_limit_num not between", value1, value2, "seckillLimitNum");
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