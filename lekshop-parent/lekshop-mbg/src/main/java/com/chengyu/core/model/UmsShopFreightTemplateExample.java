package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UmsShopFreightTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsShopFreightTemplateExample() {
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

        public Criteria andValuteTypeIsNull() {
            addCriterion("valute_type is null");
            return (Criteria) this;
        }

        public Criteria andValuteTypeIsNotNull() {
            addCriterion("valute_type is not null");
            return (Criteria) this;
        }

        public Criteria andValuteTypeEqualTo(Integer value) {
            addCriterion("valute_type =", value, "valuteType");
            return (Criteria) this;
        }

        public Criteria andValuteTypeNotEqualTo(Integer value) {
            addCriterion("valute_type <>", value, "valuteType");
            return (Criteria) this;
        }

        public Criteria andValuteTypeGreaterThan(Integer value) {
            addCriterion("valute_type >", value, "valuteType");
            return (Criteria) this;
        }

        public Criteria andValuteTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("valute_type >=", value, "valuteType");
            return (Criteria) this;
        }

        public Criteria andValuteTypeLessThan(Integer value) {
            addCriterion("valute_type <", value, "valuteType");
            return (Criteria) this;
        }

        public Criteria andValuteTypeLessThanOrEqualTo(Integer value) {
            addCriterion("valute_type <=", value, "valuteType");
            return (Criteria) this;
        }

        public Criteria andValuteTypeIn(List<Integer> values) {
            addCriterion("valute_type in", values, "valuteType");
            return (Criteria) this;
        }

        public Criteria andValuteTypeNotIn(List<Integer> values) {
            addCriterion("valute_type not in", values, "valuteType");
            return (Criteria) this;
        }

        public Criteria andValuteTypeBetween(Integer value1, Integer value2) {
            addCriterion("valute_type between", value1, value2, "valuteType");
            return (Criteria) this;
        }

        public Criteria andValuteTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("valute_type not between", value1, value2, "valuteType");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumIsNull() {
            addCriterion("default_first_num is null");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumIsNotNull() {
            addCriterion("default_first_num is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumEqualTo(BigDecimal value) {
            addCriterion("default_first_num =", value, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumNotEqualTo(BigDecimal value) {
            addCriterion("default_first_num <>", value, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumGreaterThan(BigDecimal value) {
            addCriterion("default_first_num >", value, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("default_first_num >=", value, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumLessThan(BigDecimal value) {
            addCriterion("default_first_num <", value, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("default_first_num <=", value, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumIn(List<BigDecimal> values) {
            addCriterion("default_first_num in", values, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumNotIn(List<BigDecimal> values) {
            addCriterion("default_first_num not in", values, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_first_num between", value1, value2, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_first_num not between", value1, value2, "defaultFirstNum");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceIsNull() {
            addCriterion("default_first_price is null");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceIsNotNull() {
            addCriterion("default_first_price is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceEqualTo(BigDecimal value) {
            addCriterion("default_first_price =", value, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceNotEqualTo(BigDecimal value) {
            addCriterion("default_first_price <>", value, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceGreaterThan(BigDecimal value) {
            addCriterion("default_first_price >", value, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("default_first_price >=", value, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceLessThan(BigDecimal value) {
            addCriterion("default_first_price <", value, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("default_first_price <=", value, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceIn(List<BigDecimal> values) {
            addCriterion("default_first_price in", values, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceNotIn(List<BigDecimal> values) {
            addCriterion("default_first_price not in", values, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_first_price between", value1, value2, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultFirstPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_first_price not between", value1, value2, "defaultFirstPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumIsNull() {
            addCriterion("default_second_num is null");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumIsNotNull() {
            addCriterion("default_second_num is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumEqualTo(BigDecimal value) {
            addCriterion("default_second_num =", value, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumNotEqualTo(BigDecimal value) {
            addCriterion("default_second_num <>", value, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumGreaterThan(BigDecimal value) {
            addCriterion("default_second_num >", value, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("default_second_num >=", value, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumLessThan(BigDecimal value) {
            addCriterion("default_second_num <", value, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("default_second_num <=", value, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumIn(List<BigDecimal> values) {
            addCriterion("default_second_num in", values, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumNotIn(List<BigDecimal> values) {
            addCriterion("default_second_num not in", values, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_second_num between", value1, value2, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_second_num not between", value1, value2, "defaultSecondNum");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceIsNull() {
            addCriterion("default_second_price is null");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceIsNotNull() {
            addCriterion("default_second_price is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceEqualTo(BigDecimal value) {
            addCriterion("default_second_price =", value, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceNotEqualTo(BigDecimal value) {
            addCriterion("default_second_price <>", value, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceGreaterThan(BigDecimal value) {
            addCriterion("default_second_price >", value, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("default_second_price >=", value, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceLessThan(BigDecimal value) {
            addCriterion("default_second_price <", value, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("default_second_price <=", value, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceIn(List<BigDecimal> values) {
            addCriterion("default_second_price in", values, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceNotIn(List<BigDecimal> values) {
            addCriterion("default_second_price not in", values, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_second_price between", value1, value2, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDefaultSecondPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("default_second_price not between", value1, value2, "defaultSecondPrice");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(String value) {
            addCriterion("delivery_time =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(String value) {
            addCriterion("delivery_time <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(String value) {
            addCriterion("delivery_time >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_time >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(String value) {
            addCriterion("delivery_time <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(String value) {
            addCriterion("delivery_time <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLike(String value) {
            addCriterion("delivery_time like", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotLike(String value) {
            addCriterion("delivery_time not like", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<String> values) {
            addCriterion("delivery_time in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<String> values) {
            addCriterion("delivery_time not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(String value1, String value2) {
            addCriterion("delivery_time between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(String value1, String value2) {
            addCriterion("delivery_time not between", value1, value2, "deliveryTime");
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