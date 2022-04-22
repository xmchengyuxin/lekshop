package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UmsShopCouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsShopCouponExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andUseTypeIsNull() {
            addCriterion("use_type is null");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNotNull() {
            addCriterion("use_type is not null");
            return (Criteria) this;
        }

        public Criteria andUseTypeEqualTo(Integer value) {
            addCriterion("use_type =", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotEqualTo(Integer value) {
            addCriterion("use_type <>", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThan(Integer value) {
            addCriterion("use_type >", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_type >=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThan(Integer value) {
            addCriterion("use_type <", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("use_type <=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeIn(List<Integer> values) {
            addCriterion("use_type in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotIn(List<Integer> values) {
            addCriterion("use_type not in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeBetween(Integer value1, Integer value2) {
            addCriterion("use_type between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("use_type not between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsIsNull() {
            addCriterion("use_goods_ids is null");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsIsNotNull() {
            addCriterion("use_goods_ids is not null");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsEqualTo(String value) {
            addCriterion("use_goods_ids =", value, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsNotEqualTo(String value) {
            addCriterion("use_goods_ids <>", value, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsGreaterThan(String value) {
            addCriterion("use_goods_ids >", value, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsGreaterThanOrEqualTo(String value) {
            addCriterion("use_goods_ids >=", value, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsLessThan(String value) {
            addCriterion("use_goods_ids <", value, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsLessThanOrEqualTo(String value) {
            addCriterion("use_goods_ids <=", value, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsLike(String value) {
            addCriterion("use_goods_ids like", value, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsNotLike(String value) {
            addCriterion("use_goods_ids not like", value, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsIn(List<String> values) {
            addCriterion("use_goods_ids in", values, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsNotIn(List<String> values) {
            addCriterion("use_goods_ids not in", values, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsBetween(String value1, String value2) {
            addCriterion("use_goods_ids between", value1, value2, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsIdsNotBetween(String value1, String value2) {
            addCriterion("use_goods_ids not between", value1, value2, "useGoodsIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsIsNull() {
            addCriterion("use_goods_cate_ids is null");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsIsNotNull() {
            addCriterion("use_goods_cate_ids is not null");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsEqualTo(String value) {
            addCriterion("use_goods_cate_ids =", value, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsNotEqualTo(String value) {
            addCriterion("use_goods_cate_ids <>", value, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsGreaterThan(String value) {
            addCriterion("use_goods_cate_ids >", value, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsGreaterThanOrEqualTo(String value) {
            addCriterion("use_goods_cate_ids >=", value, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsLessThan(String value) {
            addCriterion("use_goods_cate_ids <", value, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsLessThanOrEqualTo(String value) {
            addCriterion("use_goods_cate_ids <=", value, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsLike(String value) {
            addCriterion("use_goods_cate_ids like", value, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsNotLike(String value) {
            addCriterion("use_goods_cate_ids not like", value, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsIn(List<String> values) {
            addCriterion("use_goods_cate_ids in", values, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsNotIn(List<String> values) {
            addCriterion("use_goods_cate_ids not in", values, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsBetween(String value1, String value2) {
            addCriterion("use_goods_cate_ids between", value1, value2, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andUseGoodsCateIdsNotBetween(String value1, String value2) {
            addCriterion("use_goods_cate_ids not between", value1, value2, "useGoodsCateIds");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andFullAmountIsNull() {
            addCriterion("full_amount is null");
            return (Criteria) this;
        }

        public Criteria andFullAmountIsNotNull() {
            addCriterion("full_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFullAmountEqualTo(BigDecimal value) {
            addCriterion("full_amount =", value, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andFullAmountNotEqualTo(BigDecimal value) {
            addCriterion("full_amount <>", value, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andFullAmountGreaterThan(BigDecimal value) {
            addCriterion("full_amount >", value, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andFullAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("full_amount >=", value, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andFullAmountLessThan(BigDecimal value) {
            addCriterion("full_amount <", value, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andFullAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("full_amount <=", value, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andFullAmountIn(List<BigDecimal> values) {
            addCriterion("full_amount in", values, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andFullAmountNotIn(List<BigDecimal> values) {
            addCriterion("full_amount not in", values, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andFullAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("full_amount between", value1, value2, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andFullAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("full_amount not between", value1, value2, "fullAmount");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNull() {
            addCriterion("total_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNotNull() {
            addCriterion("total_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumEqualTo(Integer value) {
            addCriterion("total_num =", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotEqualTo(Integer value) {
            addCriterion("total_num <>", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThan(Integer value) {
            addCriterion("total_num >", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_num >=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThan(Integer value) {
            addCriterion("total_num <", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThanOrEqualTo(Integer value) {
            addCriterion("total_num <=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumIn(List<Integer> values) {
            addCriterion("total_num in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotIn(List<Integer> values) {
            addCriterion("total_num not in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumBetween(Integer value1, Integer value2) {
            addCriterion("total_num between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotBetween(Integer value1, Integer value2) {
            addCriterion("total_num not between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumIsNull() {
            addCriterion("draw_num is null");
            return (Criteria) this;
        }

        public Criteria andDrawNumIsNotNull() {
            addCriterion("draw_num is not null");
            return (Criteria) this;
        }

        public Criteria andDrawNumEqualTo(Integer value) {
            addCriterion("draw_num =", value, "drawNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumNotEqualTo(Integer value) {
            addCriterion("draw_num <>", value, "drawNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumGreaterThan(Integer value) {
            addCriterion("draw_num >", value, "drawNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("draw_num >=", value, "drawNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumLessThan(Integer value) {
            addCriterion("draw_num <", value, "drawNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumLessThanOrEqualTo(Integer value) {
            addCriterion("draw_num <=", value, "drawNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumIn(List<Integer> values) {
            addCriterion("draw_num in", values, "drawNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumNotIn(List<Integer> values) {
            addCriterion("draw_num not in", values, "drawNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumBetween(Integer value1, Integer value2) {
            addCriterion("draw_num between", value1, value2, "drawNum");
            return (Criteria) this;
        }

        public Criteria andDrawNumNotBetween(Integer value1, Integer value2) {
            addCriterion("draw_num not between", value1, value2, "drawNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNull() {
            addCriterion("limit_num is null");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNotNull() {
            addCriterion("limit_num is not null");
            return (Criteria) this;
        }

        public Criteria andLimitNumEqualTo(Integer value) {
            addCriterion("limit_num =", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotEqualTo(Integer value) {
            addCriterion("limit_num <>", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThan(Integer value) {
            addCriterion("limit_num >", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_num >=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThan(Integer value) {
            addCriterion("limit_num <", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThanOrEqualTo(Integer value) {
            addCriterion("limit_num <=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumIn(List<Integer> values) {
            addCriterion("limit_num in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotIn(List<Integer> values) {
            addCriterion("limit_num not in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumBetween(Integer value1, Integer value2) {
            addCriterion("limit_num between", value1, value2, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_num not between", value1, value2, "limitNum");
            return (Criteria) this;
        }

        public Criteria andValidityTypeIsNull() {
            addCriterion("validity_type is null");
            return (Criteria) this;
        }

        public Criteria andValidityTypeIsNotNull() {
            addCriterion("validity_type is not null");
            return (Criteria) this;
        }

        public Criteria andValidityTypeEqualTo(Integer value) {
            addCriterion("validity_type =", value, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityTypeNotEqualTo(Integer value) {
            addCriterion("validity_type <>", value, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityTypeGreaterThan(Integer value) {
            addCriterion("validity_type >", value, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity_type >=", value, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityTypeLessThan(Integer value) {
            addCriterion("validity_type <", value, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityTypeLessThanOrEqualTo(Integer value) {
            addCriterion("validity_type <=", value, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityTypeIn(List<Integer> values) {
            addCriterion("validity_type in", values, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityTypeNotIn(List<Integer> values) {
            addCriterion("validity_type not in", values, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityTypeBetween(Integer value1, Integer value2) {
            addCriterion("validity_type between", value1, value2, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("validity_type not between", value1, value2, "validityType");
            return (Criteria) this;
        }

        public Criteria andValidityDaysIsNull() {
            addCriterion("validity_days is null");
            return (Criteria) this;
        }

        public Criteria andValidityDaysIsNotNull() {
            addCriterion("validity_days is not null");
            return (Criteria) this;
        }

        public Criteria andValidityDaysEqualTo(Integer value) {
            addCriterion("validity_days =", value, "validityDays");
            return (Criteria) this;
        }

        public Criteria andValidityDaysNotEqualTo(Integer value) {
            addCriterion("validity_days <>", value, "validityDays");
            return (Criteria) this;
        }

        public Criteria andValidityDaysGreaterThan(Integer value) {
            addCriterion("validity_days >", value, "validityDays");
            return (Criteria) this;
        }

        public Criteria andValidityDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity_days >=", value, "validityDays");
            return (Criteria) this;
        }

        public Criteria andValidityDaysLessThan(Integer value) {
            addCriterion("validity_days <", value, "validityDays");
            return (Criteria) this;
        }

        public Criteria andValidityDaysLessThanOrEqualTo(Integer value) {
            addCriterion("validity_days <=", value, "validityDays");
            return (Criteria) this;
        }

        public Criteria andValidityDaysIn(List<Integer> values) {
            addCriterion("validity_days in", values, "validityDays");
            return (Criteria) this;
        }

        public Criteria andValidityDaysNotIn(List<Integer> values) {
            addCriterion("validity_days not in", values, "validityDays");
            return (Criteria) this;
        }

        public Criteria andValidityDaysBetween(Integer value1, Integer value2) {
            addCriterion("validity_days between", value1, value2, "validityDays");
            return (Criteria) this;
        }

        public Criteria andValidityDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("validity_days not between", value1, value2, "validityDays");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateIsNull() {
            addCriterion("fixed_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateIsNotNull() {
            addCriterion("fixed_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateEqualTo(Date value) {
            addCriterionForJDBCDate("fixed_begin_date =", value, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("fixed_begin_date <>", value, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateGreaterThan(Date value) {
            addCriterionForJDBCDate("fixed_begin_date >", value, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fixed_begin_date >=", value, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateLessThan(Date value) {
            addCriterionForJDBCDate("fixed_begin_date <", value, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fixed_begin_date <=", value, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateIn(List<Date> values) {
            addCriterionForJDBCDate("fixed_begin_date in", values, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("fixed_begin_date not in", values, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fixed_begin_date between", value1, value2, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedBeginDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fixed_begin_date not between", value1, value2, "fixedBeginDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateIsNull() {
            addCriterion("fixed_end_date is null");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateIsNotNull() {
            addCriterion("fixed_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("fixed_end_date =", value, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("fixed_end_date <>", value, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("fixed_end_date >", value, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fixed_end_date >=", value, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateLessThan(Date value) {
            addCriterionForJDBCDate("fixed_end_date <", value, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("fixed_end_date <=", value, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("fixed_end_date in", values, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("fixed_end_date not in", values, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fixed_end_date between", value1, value2, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andFixedEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("fixed_end_date not between", value1, value2, "fixedEndDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("begin_date is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(Date value) {
            addCriterionForJDBCDate("begin_date =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("begin_date <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(Date value) {
            addCriterionForJDBCDate("begin_date >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_date >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(Date value) {
            addCriterionForJDBCDate("begin_date <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_date <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<Date> values) {
            addCriterionForJDBCDate("begin_date in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("begin_date not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_date between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_date not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andRulesIsNull() {
            addCriterion("rules is null");
            return (Criteria) this;
        }

        public Criteria andRulesIsNotNull() {
            addCriterion("rules is not null");
            return (Criteria) this;
        }

        public Criteria andRulesEqualTo(String value) {
            addCriterion("rules =", value, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesNotEqualTo(String value) {
            addCriterion("rules <>", value, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesGreaterThan(String value) {
            addCriterion("rules >", value, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesGreaterThanOrEqualTo(String value) {
            addCriterion("rules >=", value, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesLessThan(String value) {
            addCriterion("rules <", value, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesLessThanOrEqualTo(String value) {
            addCriterion("rules <=", value, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesLike(String value) {
            addCriterion("rules like", value, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesNotLike(String value) {
            addCriterion("rules not like", value, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesIn(List<String> values) {
            addCriterion("rules in", values, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesNotIn(List<String> values) {
            addCriterion("rules not in", values, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesBetween(String value1, String value2) {
            addCriterion("rules between", value1, value2, "rules");
            return (Criteria) this;
        }

        public Criteria andRulesNotBetween(String value1, String value2) {
            addCriterion("rules not between", value1, value2, "rules");
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