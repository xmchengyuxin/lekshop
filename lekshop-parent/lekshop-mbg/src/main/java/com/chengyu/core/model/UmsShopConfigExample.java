package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsShopConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsShopConfigExample() {
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

        public Criteria andCateStyleIsNull() {
            addCriterion("cate_style is null");
            return (Criteria) this;
        }

        public Criteria andCateStyleIsNotNull() {
            addCriterion("cate_style is not null");
            return (Criteria) this;
        }

        public Criteria andCateStyleEqualTo(Integer value) {
            addCriterion("cate_style =", value, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andCateStyleNotEqualTo(Integer value) {
            addCriterion("cate_style <>", value, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andCateStyleGreaterThan(Integer value) {
            addCriterion("cate_style >", value, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andCateStyleGreaterThanOrEqualTo(Integer value) {
            addCriterion("cate_style >=", value, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andCateStyleLessThan(Integer value) {
            addCriterion("cate_style <", value, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andCateStyleLessThanOrEqualTo(Integer value) {
            addCriterion("cate_style <=", value, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andCateStyleIn(List<Integer> values) {
            addCriterion("cate_style in", values, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andCateStyleNotIn(List<Integer> values) {
            addCriterion("cate_style not in", values, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andCateStyleBetween(Integer value1, Integer value2) {
            addCriterion("cate_style between", value1, value2, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andCateStyleNotBetween(Integer value1, Integer value2) {
            addCriterion("cate_style not between", value1, value2, "cateStyle");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeIsNull() {
            addCriterion("delivery_fee_type is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeIsNotNull() {
            addCriterion("delivery_fee_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeEqualTo(Integer value) {
            addCriterion("delivery_fee_type =", value, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeNotEqualTo(Integer value) {
            addCriterion("delivery_fee_type <>", value, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeGreaterThan(Integer value) {
            addCriterion("delivery_fee_type >", value, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_fee_type >=", value, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeLessThan(Integer value) {
            addCriterion("delivery_fee_type <", value, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_fee_type <=", value, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeIn(List<Integer> values) {
            addCriterion("delivery_fee_type in", values, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeNotIn(List<Integer> values) {
            addCriterion("delivery_fee_type not in", values, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeBetween(Integer value1, Integer value2) {
            addCriterion("delivery_fee_type between", value1, value2, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andDeliveryFeeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_fee_type not between", value1, value2, "deliveryFeeType");
            return (Criteria) this;
        }

        public Criteria andWelcomeIsNull() {
            addCriterion("welcome is null");
            return (Criteria) this;
        }

        public Criteria andWelcomeIsNotNull() {
            addCriterion("welcome is not null");
            return (Criteria) this;
        }

        public Criteria andWelcomeEqualTo(String value) {
            addCriterion("welcome =", value, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeNotEqualTo(String value) {
            addCriterion("welcome <>", value, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeGreaterThan(String value) {
            addCriterion("welcome >", value, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeGreaterThanOrEqualTo(String value) {
            addCriterion("welcome >=", value, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeLessThan(String value) {
            addCriterion("welcome <", value, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeLessThanOrEqualTo(String value) {
            addCriterion("welcome <=", value, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeLike(String value) {
            addCriterion("welcome like", value, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeNotLike(String value) {
            addCriterion("welcome not like", value, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeIn(List<String> values) {
            addCriterion("welcome in", values, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeNotIn(List<String> values) {
            addCriterion("welcome not in", values, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeBetween(String value1, String value2) {
            addCriterion("welcome between", value1, value2, "welcome");
            return (Criteria) this;
        }

        public Criteria andWelcomeNotBetween(String value1, String value2) {
            addCriterion("welcome not between", value1, value2, "welcome");
            return (Criteria) this;
        }

        public Criteria andKdPrintIsNull() {
            addCriterion("kd_print is null");
            return (Criteria) this;
        }

        public Criteria andKdPrintIsNotNull() {
            addCriterion("kd_print is not null");
            return (Criteria) this;
        }

        public Criteria andKdPrintEqualTo(Integer value) {
            addCriterion("kd_print =", value, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdPrintNotEqualTo(Integer value) {
            addCriterion("kd_print <>", value, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdPrintGreaterThan(Integer value) {
            addCriterion("kd_print >", value, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdPrintGreaterThanOrEqualTo(Integer value) {
            addCriterion("kd_print >=", value, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdPrintLessThan(Integer value) {
            addCriterion("kd_print <", value, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdPrintLessThanOrEqualTo(Integer value) {
            addCriterion("kd_print <=", value, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdPrintIn(List<Integer> values) {
            addCriterion("kd_print in", values, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdPrintNotIn(List<Integer> values) {
            addCriterion("kd_print not in", values, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdPrintBetween(Integer value1, Integer value2) {
            addCriterion("kd_print between", value1, value2, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdPrintNotBetween(Integer value1, Integer value2) {
            addCriterion("kd_print not between", value1, value2, "kdPrint");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryIsNull() {
            addCriterion("kd_delivery is null");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryIsNotNull() {
            addCriterion("kd_delivery is not null");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryEqualTo(Integer value) {
            addCriterion("kd_delivery =", value, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryNotEqualTo(Integer value) {
            addCriterion("kd_delivery <>", value, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryGreaterThan(Integer value) {
            addCriterion("kd_delivery >", value, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryGreaterThanOrEqualTo(Integer value) {
            addCriterion("kd_delivery >=", value, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryLessThan(Integer value) {
            addCriterion("kd_delivery <", value, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryLessThanOrEqualTo(Integer value) {
            addCriterion("kd_delivery <=", value, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryIn(List<Integer> values) {
            addCriterion("kd_delivery in", values, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryNotIn(List<Integer> values) {
            addCriterion("kd_delivery not in", values, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryBetween(Integer value1, Integer value2) {
            addCriterion("kd_delivery between", value1, value2, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdDeliveryNotBetween(Integer value1, Integer value2) {
            addCriterion("kd_delivery not between", value1, value2, "kdDelivery");
            return (Criteria) this;
        }

        public Criteria andKdSecretIsNull() {
            addCriterion("kd_secret is null");
            return (Criteria) this;
        }

        public Criteria andKdSecretIsNotNull() {
            addCriterion("kd_secret is not null");
            return (Criteria) this;
        }

        public Criteria andKdSecretEqualTo(String value) {
            addCriterion("kd_secret =", value, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretNotEqualTo(String value) {
            addCriterion("kd_secret <>", value, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretGreaterThan(String value) {
            addCriterion("kd_secret >", value, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretGreaterThanOrEqualTo(String value) {
            addCriterion("kd_secret >=", value, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretLessThan(String value) {
            addCriterion("kd_secret <", value, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretLessThanOrEqualTo(String value) {
            addCriterion("kd_secret <=", value, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretLike(String value) {
            addCriterion("kd_secret like", value, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretNotLike(String value) {
            addCriterion("kd_secret not like", value, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretIn(List<String> values) {
            addCriterion("kd_secret in", values, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretNotIn(List<String> values) {
            addCriterion("kd_secret not in", values, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretBetween(String value1, String value2) {
            addCriterion("kd_secret between", value1, value2, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdSecretNotBetween(String value1, String value2) {
            addCriterion("kd_secret not between", value1, value2, "kdSecret");
            return (Criteria) this;
        }

        public Criteria andKdKeyIsNull() {
            addCriterion("kd_key is null");
            return (Criteria) this;
        }

        public Criteria andKdKeyIsNotNull() {
            addCriterion("kd_key is not null");
            return (Criteria) this;
        }

        public Criteria andKdKeyEqualTo(String value) {
            addCriterion("kd_key =", value, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyNotEqualTo(String value) {
            addCriterion("kd_key <>", value, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyGreaterThan(String value) {
            addCriterion("kd_key >", value, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyGreaterThanOrEqualTo(String value) {
            addCriterion("kd_key >=", value, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyLessThan(String value) {
            addCriterion("kd_key <", value, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyLessThanOrEqualTo(String value) {
            addCriterion("kd_key <=", value, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyLike(String value) {
            addCriterion("kd_key like", value, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyNotLike(String value) {
            addCriterion("kd_key not like", value, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyIn(List<String> values) {
            addCriterion("kd_key in", values, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyNotIn(List<String> values) {
            addCriterion("kd_key not in", values, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyBetween(String value1, String value2) {
            addCriterion("kd_key between", value1, value2, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdKeyNotBetween(String value1, String value2) {
            addCriterion("kd_key not between", value1, value2, "kdKey");
            return (Criteria) this;
        }

        public Criteria andKdAccountIsNull() {
            addCriterion("kd_account is null");
            return (Criteria) this;
        }

        public Criteria andKdAccountIsNotNull() {
            addCriterion("kd_account is not null");
            return (Criteria) this;
        }

        public Criteria andKdAccountEqualTo(String value) {
            addCriterion("kd_account =", value, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountNotEqualTo(String value) {
            addCriterion("kd_account <>", value, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountGreaterThan(String value) {
            addCriterion("kd_account >", value, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountGreaterThanOrEqualTo(String value) {
            addCriterion("kd_account >=", value, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountLessThan(String value) {
            addCriterion("kd_account <", value, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountLessThanOrEqualTo(String value) {
            addCriterion("kd_account <=", value, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountLike(String value) {
            addCriterion("kd_account like", value, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountNotLike(String value) {
            addCriterion("kd_account not like", value, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountIn(List<String> values) {
            addCriterion("kd_account in", values, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountNotIn(List<String> values) {
            addCriterion("kd_account not in", values, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountBetween(String value1, String value2) {
            addCriterion("kd_account between", value1, value2, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdAccountNotBetween(String value1, String value2) {
            addCriterion("kd_account not between", value1, value2, "kdAccount");
            return (Criteria) this;
        }

        public Criteria andKdPasswordIsNull() {
            addCriterion("kd_password is null");
            return (Criteria) this;
        }

        public Criteria andKdPasswordIsNotNull() {
            addCriterion("kd_password is not null");
            return (Criteria) this;
        }

        public Criteria andKdPasswordEqualTo(String value) {
            addCriterion("kd_password =", value, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordNotEqualTo(String value) {
            addCriterion("kd_password <>", value, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordGreaterThan(String value) {
            addCriterion("kd_password >", value, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("kd_password >=", value, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordLessThan(String value) {
            addCriterion("kd_password <", value, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordLessThanOrEqualTo(String value) {
            addCriterion("kd_password <=", value, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordLike(String value) {
            addCriterion("kd_password like", value, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordNotLike(String value) {
            addCriterion("kd_password not like", value, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordIn(List<String> values) {
            addCriterion("kd_password in", values, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordNotIn(List<String> values) {
            addCriterion("kd_password not in", values, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordBetween(String value1, String value2) {
            addCriterion("kd_password between", value1, value2, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdPasswordNotBetween(String value1, String value2) {
            addCriterion("kd_password not between", value1, value2, "kdPassword");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdIsNull() {
            addCriterion("kd_equipment_id is null");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdIsNotNull() {
            addCriterion("kd_equipment_id is not null");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdEqualTo(String value) {
            addCriterion("kd_equipment_id =", value, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdNotEqualTo(String value) {
            addCriterion("kd_equipment_id <>", value, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdGreaterThan(String value) {
            addCriterion("kd_equipment_id >", value, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("kd_equipment_id >=", value, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdLessThan(String value) {
            addCriterion("kd_equipment_id <", value, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdLessThanOrEqualTo(String value) {
            addCriterion("kd_equipment_id <=", value, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdLike(String value) {
            addCriterion("kd_equipment_id like", value, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdNotLike(String value) {
            addCriterion("kd_equipment_id not like", value, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdIn(List<String> values) {
            addCriterion("kd_equipment_id in", values, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdNotIn(List<String> values) {
            addCriterion("kd_equipment_id not in", values, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdBetween(String value1, String value2) {
            addCriterion("kd_equipment_id between", value1, value2, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdEquipmentIdNotBetween(String value1, String value2) {
            addCriterion("kd_equipment_id not between", value1, value2, "kdEquipmentId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdIsNull() {
            addCriterion("kd_template_id is null");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdIsNotNull() {
            addCriterion("kd_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdEqualTo(String value) {
            addCriterion("kd_template_id =", value, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdNotEqualTo(String value) {
            addCriterion("kd_template_id <>", value, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdGreaterThan(String value) {
            addCriterion("kd_template_id >", value, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("kd_template_id >=", value, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdLessThan(String value) {
            addCriterion("kd_template_id <", value, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("kd_template_id <=", value, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdLike(String value) {
            addCriterion("kd_template_id like", value, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdNotLike(String value) {
            addCriterion("kd_template_id not like", value, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdIn(List<String> values) {
            addCriterion("kd_template_id in", values, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdNotIn(List<String> values) {
            addCriterion("kd_template_id not in", values, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdBetween(String value1, String value2) {
            addCriterion("kd_template_id between", value1, value2, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andKdTemplateIdNotBetween(String value1, String value2) {
            addCriterion("kd_template_id not between", value1, value2, "kdTemplateId");
            return (Criteria) this;
        }

        public Criteria andSendNameIsNull() {
            addCriterion("send_name is null");
            return (Criteria) this;
        }

        public Criteria andSendNameIsNotNull() {
            addCriterion("send_name is not null");
            return (Criteria) this;
        }

        public Criteria andSendNameEqualTo(String value) {
            addCriterion("send_name =", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotEqualTo(String value) {
            addCriterion("send_name <>", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameGreaterThan(String value) {
            addCriterion("send_name >", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameGreaterThanOrEqualTo(String value) {
            addCriterion("send_name >=", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLessThan(String value) {
            addCriterion("send_name <", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLessThanOrEqualTo(String value) {
            addCriterion("send_name <=", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLike(String value) {
            addCriterion("send_name like", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotLike(String value) {
            addCriterion("send_name not like", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameIn(List<String> values) {
            addCriterion("send_name in", values, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotIn(List<String> values) {
            addCriterion("send_name not in", values, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameBetween(String value1, String value2) {
            addCriterion("send_name between", value1, value2, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotBetween(String value1, String value2) {
            addCriterion("send_name not between", value1, value2, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendPhoneIsNull() {
            addCriterion("send_phone is null");
            return (Criteria) this;
        }

        public Criteria andSendPhoneIsNotNull() {
            addCriterion("send_phone is not null");
            return (Criteria) this;
        }

        public Criteria andSendPhoneEqualTo(String value) {
            addCriterion("send_phone =", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneNotEqualTo(String value) {
            addCriterion("send_phone <>", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneGreaterThan(String value) {
            addCriterion("send_phone >", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("send_phone >=", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneLessThan(String value) {
            addCriterion("send_phone <", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneLessThanOrEqualTo(String value) {
            addCriterion("send_phone <=", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneLike(String value) {
            addCriterion("send_phone like", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneNotLike(String value) {
            addCriterion("send_phone not like", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneIn(List<String> values) {
            addCriterion("send_phone in", values, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneNotIn(List<String> values) {
            addCriterion("send_phone not in", values, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneBetween(String value1, String value2) {
            addCriterion("send_phone between", value1, value2, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneNotBetween(String value1, String value2) {
            addCriterion("send_phone not between", value1, value2, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendAddressIsNull() {
            addCriterion("send_address is null");
            return (Criteria) this;
        }

        public Criteria andSendAddressIsNotNull() {
            addCriterion("send_address is not null");
            return (Criteria) this;
        }

        public Criteria andSendAddressEqualTo(String value) {
            addCriterion("send_address =", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressNotEqualTo(String value) {
            addCriterion("send_address <>", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressGreaterThan(String value) {
            addCriterion("send_address >", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressGreaterThanOrEqualTo(String value) {
            addCriterion("send_address >=", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressLessThan(String value) {
            addCriterion("send_address <", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressLessThanOrEqualTo(String value) {
            addCriterion("send_address <=", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressLike(String value) {
            addCriterion("send_address like", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressNotLike(String value) {
            addCriterion("send_address not like", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressIn(List<String> values) {
            addCriterion("send_address in", values, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressNotIn(List<String> values) {
            addCriterion("send_address not in", values, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressBetween(String value1, String value2) {
            addCriterion("send_address between", value1, value2, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressNotBetween(String value1, String value2) {
            addCriterion("send_address not between", value1, value2, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andReturnNameIsNull() {
            addCriterion("return_name is null");
            return (Criteria) this;
        }

        public Criteria andReturnNameIsNotNull() {
            addCriterion("return_name is not null");
            return (Criteria) this;
        }

        public Criteria andReturnNameEqualTo(String value) {
            addCriterion("return_name =", value, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameNotEqualTo(String value) {
            addCriterion("return_name <>", value, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameGreaterThan(String value) {
            addCriterion("return_name >", value, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameGreaterThanOrEqualTo(String value) {
            addCriterion("return_name >=", value, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameLessThan(String value) {
            addCriterion("return_name <", value, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameLessThanOrEqualTo(String value) {
            addCriterion("return_name <=", value, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameLike(String value) {
            addCriterion("return_name like", value, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameNotLike(String value) {
            addCriterion("return_name not like", value, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameIn(List<String> values) {
            addCriterion("return_name in", values, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameNotIn(List<String> values) {
            addCriterion("return_name not in", values, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameBetween(String value1, String value2) {
            addCriterion("return_name between", value1, value2, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnNameNotBetween(String value1, String value2) {
            addCriterion("return_name not between", value1, value2, "returnName");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneIsNull() {
            addCriterion("return_phone is null");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneIsNotNull() {
            addCriterion("return_phone is not null");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneEqualTo(String value) {
            addCriterion("return_phone =", value, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneNotEqualTo(String value) {
            addCriterion("return_phone <>", value, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneGreaterThan(String value) {
            addCriterion("return_phone >", value, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("return_phone >=", value, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneLessThan(String value) {
            addCriterion("return_phone <", value, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneLessThanOrEqualTo(String value) {
            addCriterion("return_phone <=", value, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneLike(String value) {
            addCriterion("return_phone like", value, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneNotLike(String value) {
            addCriterion("return_phone not like", value, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneIn(List<String> values) {
            addCriterion("return_phone in", values, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneNotIn(List<String> values) {
            addCriterion("return_phone not in", values, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneBetween(String value1, String value2) {
            addCriterion("return_phone between", value1, value2, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnPhoneNotBetween(String value1, String value2) {
            addCriterion("return_phone not between", value1, value2, "returnPhone");
            return (Criteria) this;
        }

        public Criteria andReturnAddressIsNull() {
            addCriterion("return_address is null");
            return (Criteria) this;
        }

        public Criteria andReturnAddressIsNotNull() {
            addCriterion("return_address is not null");
            return (Criteria) this;
        }

        public Criteria andReturnAddressEqualTo(String value) {
            addCriterion("return_address =", value, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressNotEqualTo(String value) {
            addCriterion("return_address <>", value, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressGreaterThan(String value) {
            addCriterion("return_address >", value, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressGreaterThanOrEqualTo(String value) {
            addCriterion("return_address >=", value, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressLessThan(String value) {
            addCriterion("return_address <", value, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressLessThanOrEqualTo(String value) {
            addCriterion("return_address <=", value, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressLike(String value) {
            addCriterion("return_address like", value, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressNotLike(String value) {
            addCriterion("return_address not like", value, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressIn(List<String> values) {
            addCriterion("return_address in", values, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressNotIn(List<String> values) {
            addCriterion("return_address not in", values, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressBetween(String value1, String value2) {
            addCriterion("return_address between", value1, value2, "returnAddress");
            return (Criteria) this;
        }

        public Criteria andReturnAddressNotBetween(String value1, String value2) {
            addCriterion("return_address not between", value1, value2, "returnAddress");
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