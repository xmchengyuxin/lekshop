package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OmsOrderPriceLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OmsOrderPriceLogExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOldPriceIsNull() {
            addCriterion("old_price is null");
            return (Criteria) this;
        }

        public Criteria andOldPriceIsNotNull() {
            addCriterion("old_price is not null");
            return (Criteria) this;
        }

        public Criteria andOldPriceEqualTo(BigDecimal value) {
            addCriterion("old_price =", value, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andOldPriceNotEqualTo(BigDecimal value) {
            addCriterion("old_price <>", value, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andOldPriceGreaterThan(BigDecimal value) {
            addCriterion("old_price >", value, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andOldPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("old_price >=", value, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andOldPriceLessThan(BigDecimal value) {
            addCriterion("old_price <", value, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andOldPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("old_price <=", value, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andOldPriceIn(List<BigDecimal> values) {
            addCriterion("old_price in", values, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andOldPriceNotIn(List<BigDecimal> values) {
            addCriterion("old_price not in", values, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andOldPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("old_price between", value1, value2, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andOldPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("old_price not between", value1, value2, "oldPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceIsNull() {
            addCriterion("now_price is null");
            return (Criteria) this;
        }

        public Criteria andNowPriceIsNotNull() {
            addCriterion("now_price is not null");
            return (Criteria) this;
        }

        public Criteria andNowPriceEqualTo(BigDecimal value) {
            addCriterion("now_price =", value, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceNotEqualTo(BigDecimal value) {
            addCriterion("now_price <>", value, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceGreaterThan(BigDecimal value) {
            addCriterion("now_price >", value, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("now_price >=", value, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceLessThan(BigDecimal value) {
            addCriterion("now_price <", value, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("now_price <=", value, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceIn(List<BigDecimal> values) {
            addCriterion("now_price in", values, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceNotIn(List<BigDecimal> values) {
            addCriterion("now_price not in", values, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("now_price between", value1, value2, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andNowPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("now_price not between", value1, value2, "nowPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceIsNull() {
            addCriterion("edit_price is null");
            return (Criteria) this;
        }

        public Criteria andEditPriceIsNotNull() {
            addCriterion("edit_price is not null");
            return (Criteria) this;
        }

        public Criteria andEditPriceEqualTo(BigDecimal value) {
            addCriterion("edit_price =", value, "editPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceNotEqualTo(BigDecimal value) {
            addCriterion("edit_price <>", value, "editPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceGreaterThan(BigDecimal value) {
            addCriterion("edit_price >", value, "editPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("edit_price >=", value, "editPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceLessThan(BigDecimal value) {
            addCriterion("edit_price <", value, "editPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("edit_price <=", value, "editPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceIn(List<BigDecimal> values) {
            addCriterion("edit_price in", values, "editPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceNotIn(List<BigDecimal> values) {
            addCriterion("edit_price not in", values, "editPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("edit_price between", value1, value2, "editPrice");
            return (Criteria) this;
        }

        public Criteria andEditPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("edit_price not between", value1, value2, "editPrice");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andEditIdIsNull() {
            addCriterion("edit_id is null");
            return (Criteria) this;
        }

        public Criteria andEditIdIsNotNull() {
            addCriterion("edit_id is not null");
            return (Criteria) this;
        }

        public Criteria andEditIdEqualTo(Integer value) {
            addCriterion("edit_id =", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdNotEqualTo(Integer value) {
            addCriterion("edit_id <>", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdGreaterThan(Integer value) {
            addCriterion("edit_id >", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("edit_id >=", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdLessThan(Integer value) {
            addCriterion("edit_id <", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdLessThanOrEqualTo(Integer value) {
            addCriterion("edit_id <=", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdIn(List<Integer> values) {
            addCriterion("edit_id in", values, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdNotIn(List<Integer> values) {
            addCriterion("edit_id not in", values, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdBetween(Integer value1, Integer value2) {
            addCriterion("edit_id between", value1, value2, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdNotBetween(Integer value1, Integer value2) {
            addCriterion("edit_id not between", value1, value2, "editId");
            return (Criteria) this;
        }

        public Criteria andEditNameIsNull() {
            addCriterion("edit_name is null");
            return (Criteria) this;
        }

        public Criteria andEditNameIsNotNull() {
            addCriterion("edit_name is not null");
            return (Criteria) this;
        }

        public Criteria andEditNameEqualTo(String value) {
            addCriterion("edit_name =", value, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameNotEqualTo(String value) {
            addCriterion("edit_name <>", value, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameGreaterThan(String value) {
            addCriterion("edit_name >", value, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameGreaterThanOrEqualTo(String value) {
            addCriterion("edit_name >=", value, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameLessThan(String value) {
            addCriterion("edit_name <", value, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameLessThanOrEqualTo(String value) {
            addCriterion("edit_name <=", value, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameLike(String value) {
            addCriterion("edit_name like", value, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameNotLike(String value) {
            addCriterion("edit_name not like", value, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameIn(List<String> values) {
            addCriterion("edit_name in", values, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameNotIn(List<String> values) {
            addCriterion("edit_name not in", values, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameBetween(String value1, String value2) {
            addCriterion("edit_name between", value1, value2, "editName");
            return (Criteria) this;
        }

        public Criteria andEditNameNotBetween(String value1, String value2) {
            addCriterion("edit_name not between", value1, value2, "editName");
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