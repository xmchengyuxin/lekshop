package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PointGoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PointGoodsExample() {
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

        public Criteria andGoodsCodeIsNull() {
            addCriterion("goods_code is null");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeIsNotNull() {
            addCriterion("goods_code is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeEqualTo(String value) {
            addCriterion("goods_code =", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotEqualTo(String value) {
            addCriterion("goods_code <>", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeGreaterThan(String value) {
            addCriterion("goods_code >", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("goods_code >=", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeLessThan(String value) {
            addCriterion("goods_code <", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeLessThanOrEqualTo(String value) {
            addCriterion("goods_code <=", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeLike(String value) {
            addCriterion("goods_code like", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotLike(String value) {
            addCriterion("goods_code not like", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeIn(List<String> values) {
            addCriterion("goods_code in", values, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotIn(List<String> values) {
            addCriterion("goods_code not in", values, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeBetween(String value1, String value2) {
            addCriterion("goods_code between", value1, value2, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotBetween(String value1, String value2) {
            addCriterion("goods_code not between", value1, value2, "goodsCode");
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

        public Criteria andCateIdsIsNull() {
            addCriterion("cate_ids is null");
            return (Criteria) this;
        }

        public Criteria andCateIdsIsNotNull() {
            addCriterion("cate_ids is not null");
            return (Criteria) this;
        }

        public Criteria andCateIdsEqualTo(String value) {
            addCriterion("cate_ids =", value, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsNotEqualTo(String value) {
            addCriterion("cate_ids <>", value, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsGreaterThan(String value) {
            addCriterion("cate_ids >", value, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsGreaterThanOrEqualTo(String value) {
            addCriterion("cate_ids >=", value, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsLessThan(String value) {
            addCriterion("cate_ids <", value, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsLessThanOrEqualTo(String value) {
            addCriterion("cate_ids <=", value, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsLike(String value) {
            addCriterion("cate_ids like", value, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsNotLike(String value) {
            addCriterion("cate_ids not like", value, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsIn(List<String> values) {
            addCriterion("cate_ids in", values, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsNotIn(List<String> values) {
            addCriterion("cate_ids not in", values, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsBetween(String value1, String value2) {
            addCriterion("cate_ids between", value1, value2, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateIdsNotBetween(String value1, String value2) {
            addCriterion("cate_ids not between", value1, value2, "cateIds");
            return (Criteria) this;
        }

        public Criteria andCateNameIsNull() {
            addCriterion("cate_name is null");
            return (Criteria) this;
        }

        public Criteria andCateNameIsNotNull() {
            addCriterion("cate_name is not null");
            return (Criteria) this;
        }

        public Criteria andCateNameEqualTo(String value) {
            addCriterion("cate_name =", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameNotEqualTo(String value) {
            addCriterion("cate_name <>", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameGreaterThan(String value) {
            addCriterion("cate_name >", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameGreaterThanOrEqualTo(String value) {
            addCriterion("cate_name >=", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameLessThan(String value) {
            addCriterion("cate_name <", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameLessThanOrEqualTo(String value) {
            addCriterion("cate_name <=", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameLike(String value) {
            addCriterion("cate_name like", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameNotLike(String value) {
            addCriterion("cate_name not like", value, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameIn(List<String> values) {
            addCriterion("cate_name in", values, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameNotIn(List<String> values) {
            addCriterion("cate_name not in", values, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameBetween(String value1, String value2) {
            addCriterion("cate_name between", value1, value2, "cateName");
            return (Criteria) this;
        }

        public Criteria andCateNameNotBetween(String value1, String value2) {
            addCriterion("cate_name not between", value1, value2, "cateName");
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

        public Criteria andExchangeMethodIsNull() {
            addCriterion("exchange_method is null");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodIsNotNull() {
            addCriterion("exchange_method is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodEqualTo(Integer value) {
            addCriterion("exchange_method =", value, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodNotEqualTo(Integer value) {
            addCriterion("exchange_method <>", value, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodGreaterThan(Integer value) {
            addCriterion("exchange_method >", value, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("exchange_method >=", value, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodLessThan(Integer value) {
            addCriterion("exchange_method <", value, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodLessThanOrEqualTo(Integer value) {
            addCriterion("exchange_method <=", value, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodIn(List<Integer> values) {
            addCriterion("exchange_method in", values, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodNotIn(List<Integer> values) {
            addCriterion("exchange_method not in", values, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodBetween(Integer value1, Integer value2) {
            addCriterion("exchange_method between", value1, value2, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andExchangeMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("exchange_method not between", value1, value2, "exchangeMethod");
            return (Criteria) this;
        }

        public Criteria andOriPriceIsNull() {
            addCriterion("ori_price is null");
            return (Criteria) this;
        }

        public Criteria andOriPriceIsNotNull() {
            addCriterion("ori_price is not null");
            return (Criteria) this;
        }

        public Criteria andOriPriceEqualTo(BigDecimal value) {
            addCriterion("ori_price =", value, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andOriPriceNotEqualTo(BigDecimal value) {
            addCriterion("ori_price <>", value, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andOriPriceGreaterThan(BigDecimal value) {
            addCriterion("ori_price >", value, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andOriPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ori_price >=", value, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andOriPriceLessThan(BigDecimal value) {
            addCriterion("ori_price <", value, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andOriPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ori_price <=", value, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andOriPriceIn(List<BigDecimal> values) {
            addCriterion("ori_price in", values, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andOriPriceNotIn(List<BigDecimal> values) {
            addCriterion("ori_price not in", values, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andOriPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ori_price between", value1, value2, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andOriPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ori_price not between", value1, value2, "oriPrice");
            return (Criteria) this;
        }

        public Criteria andPointIsNull() {
            addCriterion("point is null");
            return (Criteria) this;
        }

        public Criteria andPointIsNotNull() {
            addCriterion("point is not null");
            return (Criteria) this;
        }

        public Criteria andPointEqualTo(BigDecimal value) {
            addCriterion("point =", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotEqualTo(BigDecimal value) {
            addCriterion("point <>", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThan(BigDecimal value) {
            addCriterion("point >", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("point >=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThan(BigDecimal value) {
            addCriterion("point <", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThanOrEqualTo(BigDecimal value) {
            addCriterion("point <=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointIn(List<BigDecimal> values) {
            addCriterion("point in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotIn(List<BigDecimal> values) {
            addCriterion("point not in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("point between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("point not between", value1, value2, "point");
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

        public Criteria andLimitExchangeIsNull() {
            addCriterion("limit_exchange is null");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeIsNotNull() {
            addCriterion("limit_exchange is not null");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeEqualTo(Integer value) {
            addCriterion("limit_exchange =", value, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeNotEqualTo(Integer value) {
            addCriterion("limit_exchange <>", value, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeGreaterThan(Integer value) {
            addCriterion("limit_exchange >", value, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_exchange >=", value, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeLessThan(Integer value) {
            addCriterion("limit_exchange <", value, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeLessThanOrEqualTo(Integer value) {
            addCriterion("limit_exchange <=", value, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeIn(List<Integer> values) {
            addCriterion("limit_exchange in", values, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeNotIn(List<Integer> values) {
            addCriterion("limit_exchange not in", values, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeBetween(Integer value1, Integer value2) {
            addCriterion("limit_exchange between", value1, value2, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andLimitExchangeNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_exchange not between", value1, value2, "limitExchange");
            return (Criteria) this;
        }

        public Criteria andStockIsNull() {
            addCriterion("stock is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("stock is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Integer value) {
            addCriterion("stock =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Integer value) {
            addCriterion("stock <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Integer value) {
            addCriterion("stock >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Integer value) {
            addCriterion("stock <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Integer value) {
            addCriterion("stock <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Integer> values) {
            addCriterion("stock in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Integer> values) {
            addCriterion("stock not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Integer value1, Integer value2) {
            addCriterion("stock between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Integer value1, Integer value2) {
            addCriterion("stock not between", value1, value2, "stock");
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