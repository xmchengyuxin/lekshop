package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RepShopGoodsDayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RepShopGoodsDayExample() {
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

        public Criteria andDayIsNull() {
            addCriterion("day is null");
            return (Criteria) this;
        }

        public Criteria andDayIsNotNull() {
            addCriterion("day is not null");
            return (Criteria) this;
        }

        public Criteria andDayEqualTo(Date value) {
            addCriterionForJDBCDate("day =", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("day <>", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThan(Date value) {
            addCriterionForJDBCDate("day >", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("day >=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThan(Date value) {
            addCriterionForJDBCDate("day <", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("day <=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayIn(List<Date> values) {
            addCriterionForJDBCDate("day in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("day not in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("day between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("day not between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Integer value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Integer value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Integer value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Integer value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Integer> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Integer> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgIsNull() {
            addCriterion("goods_main_img is null");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgIsNotNull() {
            addCriterion("goods_main_img is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgEqualTo(String value) {
            addCriterion("goods_main_img =", value, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgNotEqualTo(String value) {
            addCriterion("goods_main_img <>", value, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgGreaterThan(String value) {
            addCriterion("goods_main_img >", value, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgGreaterThanOrEqualTo(String value) {
            addCriterion("goods_main_img >=", value, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgLessThan(String value) {
            addCriterion("goods_main_img <", value, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgLessThanOrEqualTo(String value) {
            addCriterion("goods_main_img <=", value, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgLike(String value) {
            addCriterion("goods_main_img like", value, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgNotLike(String value) {
            addCriterion("goods_main_img not like", value, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgIn(List<String> values) {
            addCriterion("goods_main_img in", values, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgNotIn(List<String> values) {
            addCriterion("goods_main_img not in", values, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgBetween(String value1, String value2) {
            addCriterion("goods_main_img between", value1, value2, "goodsMainImg");
            return (Criteria) this;
        }

        public Criteria andGoodsMainImgNotBetween(String value1, String value2) {
            addCriterion("goods_main_img not between", value1, value2, "goodsMainImg");
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

        public Criteria andSellAmountIsNull() {
            addCriterion("sell_amount is null");
            return (Criteria) this;
        }

        public Criteria andSellAmountIsNotNull() {
            addCriterion("sell_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSellAmountEqualTo(BigDecimal value) {
            addCriterion("sell_amount =", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountNotEqualTo(BigDecimal value) {
            addCriterion("sell_amount <>", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountGreaterThan(BigDecimal value) {
            addCriterion("sell_amount >", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sell_amount >=", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountLessThan(BigDecimal value) {
            addCriterion("sell_amount <", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sell_amount <=", value, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountIn(List<BigDecimal> values) {
            addCriterion("sell_amount in", values, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountNotIn(List<BigDecimal> values) {
            addCriterion("sell_amount not in", values, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sell_amount between", value1, value2, "sellAmount");
            return (Criteria) this;
        }

        public Criteria andSellAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sell_amount not between", value1, value2, "sellAmount");
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

        public Criteria andMediumCommentNumIsNull() {
            addCriterion("medium_comment_num is null");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumIsNotNull() {
            addCriterion("medium_comment_num is not null");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumEqualTo(Integer value) {
            addCriterion("medium_comment_num =", value, "mediumCommentNum");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumNotEqualTo(Integer value) {
            addCriterion("medium_comment_num <>", value, "mediumCommentNum");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumGreaterThan(Integer value) {
            addCriterion("medium_comment_num >", value, "mediumCommentNum");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("medium_comment_num >=", value, "mediumCommentNum");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumLessThan(Integer value) {
            addCriterion("medium_comment_num <", value, "mediumCommentNum");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumLessThanOrEqualTo(Integer value) {
            addCriterion("medium_comment_num <=", value, "mediumCommentNum");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumIn(List<Integer> values) {
            addCriterion("medium_comment_num in", values, "mediumCommentNum");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumNotIn(List<Integer> values) {
            addCriterion("medium_comment_num not in", values, "mediumCommentNum");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumBetween(Integer value1, Integer value2) {
            addCriterion("medium_comment_num between", value1, value2, "mediumCommentNum");
            return (Criteria) this;
        }

        public Criteria andMediumCommentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("medium_comment_num not between", value1, value2, "mediumCommentNum");
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

        public Criteria andRebuyRateIsNull() {
            addCriterion("rebuy_rate is null");
            return (Criteria) this;
        }

        public Criteria andRebuyRateIsNotNull() {
            addCriterion("rebuy_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRebuyRateEqualTo(BigDecimal value) {
            addCriterion("rebuy_rate =", value, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andRebuyRateNotEqualTo(BigDecimal value) {
            addCriterion("rebuy_rate <>", value, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andRebuyRateGreaterThan(BigDecimal value) {
            addCriterion("rebuy_rate >", value, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andRebuyRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rebuy_rate >=", value, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andRebuyRateLessThan(BigDecimal value) {
            addCriterion("rebuy_rate <", value, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andRebuyRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rebuy_rate <=", value, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andRebuyRateIn(List<BigDecimal> values) {
            addCriterion("rebuy_rate in", values, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andRebuyRateNotIn(List<BigDecimal> values) {
            addCriterion("rebuy_rate not in", values, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andRebuyRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebuy_rate between", value1, value2, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andRebuyRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rebuy_rate not between", value1, value2, "rebuyRate");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumIsNull() {
            addCriterion("buy_two_mannum is null");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumIsNotNull() {
            addCriterion("buy_two_mannum is not null");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumEqualTo(Integer value) {
            addCriterion("buy_two_mannum =", value, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumNotEqualTo(Integer value) {
            addCriterion("buy_two_mannum <>", value, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumGreaterThan(Integer value) {
            addCriterion("buy_two_mannum >", value, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_two_mannum >=", value, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumLessThan(Integer value) {
            addCriterion("buy_two_mannum <", value, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumLessThanOrEqualTo(Integer value) {
            addCriterion("buy_two_mannum <=", value, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumIn(List<Integer> values) {
            addCriterion("buy_two_mannum in", values, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumNotIn(List<Integer> values) {
            addCriterion("buy_two_mannum not in", values, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumBetween(Integer value1, Integer value2) {
            addCriterion("buy_two_mannum between", value1, value2, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyTwoMannumNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_two_mannum not between", value1, value2, "buyTwoMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumIsNull() {
            addCriterion("buy_mannum is null");
            return (Criteria) this;
        }

        public Criteria andBuyMannumIsNotNull() {
            addCriterion("buy_mannum is not null");
            return (Criteria) this;
        }

        public Criteria andBuyMannumEqualTo(Integer value) {
            addCriterion("buy_mannum =", value, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumNotEqualTo(Integer value) {
            addCriterion("buy_mannum <>", value, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumGreaterThan(Integer value) {
            addCriterion("buy_mannum >", value, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_mannum >=", value, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumLessThan(Integer value) {
            addCriterion("buy_mannum <", value, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumLessThanOrEqualTo(Integer value) {
            addCriterion("buy_mannum <=", value, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumIn(List<Integer> values) {
            addCriterion("buy_mannum in", values, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumNotIn(List<Integer> values) {
            addCriterion("buy_mannum not in", values, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumBetween(Integer value1, Integer value2) {
            addCriterion("buy_mannum between", value1, value2, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andBuyMannumNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_mannum not between", value1, value2, "buyMannum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumIsNull() {
            addCriterion("rebuy_num is null");
            return (Criteria) this;
        }

        public Criteria andRebuyNumIsNotNull() {
            addCriterion("rebuy_num is not null");
            return (Criteria) this;
        }

        public Criteria andRebuyNumEqualTo(Integer value) {
            addCriterion("rebuy_num =", value, "rebuyNum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumNotEqualTo(Integer value) {
            addCriterion("rebuy_num <>", value, "rebuyNum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumGreaterThan(Integer value) {
            addCriterion("rebuy_num >", value, "rebuyNum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rebuy_num >=", value, "rebuyNum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumLessThan(Integer value) {
            addCriterion("rebuy_num <", value, "rebuyNum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumLessThanOrEqualTo(Integer value) {
            addCriterion("rebuy_num <=", value, "rebuyNum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumIn(List<Integer> values) {
            addCriterion("rebuy_num in", values, "rebuyNum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumNotIn(List<Integer> values) {
            addCriterion("rebuy_num not in", values, "rebuyNum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumBetween(Integer value1, Integer value2) {
            addCriterion("rebuy_num between", value1, value2, "rebuyNum");
            return (Criteria) this;
        }

        public Criteria andRebuyNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rebuy_num not between", value1, value2, "rebuyNum");
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