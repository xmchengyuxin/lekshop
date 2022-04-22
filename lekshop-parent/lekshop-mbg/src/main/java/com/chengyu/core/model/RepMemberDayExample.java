package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RepMemberDayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RepMemberDayExample() {
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

        public Criteria andActiviteNumIsNull() {
            addCriterion("activite_num is null");
            return (Criteria) this;
        }

        public Criteria andActiviteNumIsNotNull() {
            addCriterion("activite_num is not null");
            return (Criteria) this;
        }

        public Criteria andActiviteNumEqualTo(Integer value) {
            addCriterion("activite_num =", value, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andActiviteNumNotEqualTo(Integer value) {
            addCriterion("activite_num <>", value, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andActiviteNumGreaterThan(Integer value) {
            addCriterion("activite_num >", value, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andActiviteNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("activite_num >=", value, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andActiviteNumLessThan(Integer value) {
            addCriterion("activite_num <", value, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andActiviteNumLessThanOrEqualTo(Integer value) {
            addCriterion("activite_num <=", value, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andActiviteNumIn(List<Integer> values) {
            addCriterion("activite_num in", values, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andActiviteNumNotIn(List<Integer> values) {
            addCriterion("activite_num not in", values, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andActiviteNumBetween(Integer value1, Integer value2) {
            addCriterion("activite_num between", value1, value2, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andActiviteNumNotBetween(Integer value1, Integer value2) {
            addCriterion("activite_num not between", value1, value2, "activiteNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumIsNull() {
            addCriterion("register_num is null");
            return (Criteria) this;
        }

        public Criteria andRegisterNumIsNotNull() {
            addCriterion("register_num is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterNumEqualTo(Integer value) {
            addCriterion("register_num =", value, "registerNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumNotEqualTo(Integer value) {
            addCriterion("register_num <>", value, "registerNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumGreaterThan(Integer value) {
            addCriterion("register_num >", value, "registerNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("register_num >=", value, "registerNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumLessThan(Integer value) {
            addCriterion("register_num <", value, "registerNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumLessThanOrEqualTo(Integer value) {
            addCriterion("register_num <=", value, "registerNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumIn(List<Integer> values) {
            addCriterion("register_num in", values, "registerNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumNotIn(List<Integer> values) {
            addCriterion("register_num not in", values, "registerNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumBetween(Integer value1, Integer value2) {
            addCriterion("register_num between", value1, value2, "registerNum");
            return (Criteria) this;
        }

        public Criteria andRegisterNumNotBetween(Integer value1, Integer value2) {
            addCriterion("register_num not between", value1, value2, "registerNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("order_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("order_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(Integer value) {
            addCriterion("order_num =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(Integer value) {
            addCriterion("order_num <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(Integer value) {
            addCriterion("order_num >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_num >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(Integer value) {
            addCriterion("order_num <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("order_num <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<Integer> values) {
            addCriterion("order_num in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<Integer> values) {
            addCriterion("order_num not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("order_num between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("order_num not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumIsNull() {
            addCriterion("xcx_num is null");
            return (Criteria) this;
        }

        public Criteria andXcxNumIsNotNull() {
            addCriterion("xcx_num is not null");
            return (Criteria) this;
        }

        public Criteria andXcxNumEqualTo(Integer value) {
            addCriterion("xcx_num =", value, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumNotEqualTo(Integer value) {
            addCriterion("xcx_num <>", value, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumGreaterThan(Integer value) {
            addCriterion("xcx_num >", value, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("xcx_num >=", value, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumLessThan(Integer value) {
            addCriterion("xcx_num <", value, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumLessThanOrEqualTo(Integer value) {
            addCriterion("xcx_num <=", value, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumIn(List<Integer> values) {
            addCriterion("xcx_num in", values, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumNotIn(List<Integer> values) {
            addCriterion("xcx_num not in", values, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumBetween(Integer value1, Integer value2) {
            addCriterion("xcx_num between", value1, value2, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andXcxNumNotBetween(Integer value1, Integer value2) {
            addCriterion("xcx_num not between", value1, value2, "xcxNum");
            return (Criteria) this;
        }

        public Criteria andAppNumIsNull() {
            addCriterion("app_num is null");
            return (Criteria) this;
        }

        public Criteria andAppNumIsNotNull() {
            addCriterion("app_num is not null");
            return (Criteria) this;
        }

        public Criteria andAppNumEqualTo(Integer value) {
            addCriterion("app_num =", value, "appNum");
            return (Criteria) this;
        }

        public Criteria andAppNumNotEqualTo(Integer value) {
            addCriterion("app_num <>", value, "appNum");
            return (Criteria) this;
        }

        public Criteria andAppNumGreaterThan(Integer value) {
            addCriterion("app_num >", value, "appNum");
            return (Criteria) this;
        }

        public Criteria andAppNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_num >=", value, "appNum");
            return (Criteria) this;
        }

        public Criteria andAppNumLessThan(Integer value) {
            addCriterion("app_num <", value, "appNum");
            return (Criteria) this;
        }

        public Criteria andAppNumLessThanOrEqualTo(Integer value) {
            addCriterion("app_num <=", value, "appNum");
            return (Criteria) this;
        }

        public Criteria andAppNumIn(List<Integer> values) {
            addCriterion("app_num in", values, "appNum");
            return (Criteria) this;
        }

        public Criteria andAppNumNotIn(List<Integer> values) {
            addCriterion("app_num not in", values, "appNum");
            return (Criteria) this;
        }

        public Criteria andAppNumBetween(Integer value1, Integer value2) {
            addCriterion("app_num between", value1, value2, "appNum");
            return (Criteria) this;
        }

        public Criteria andAppNumNotBetween(Integer value1, Integer value2) {
            addCriterion("app_num not between", value1, value2, "appNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumIsNull() {
            addCriterion("link_num is null");
            return (Criteria) this;
        }

        public Criteria andLinkNumIsNotNull() {
            addCriterion("link_num is not null");
            return (Criteria) this;
        }

        public Criteria andLinkNumEqualTo(Integer value) {
            addCriterion("link_num =", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumNotEqualTo(Integer value) {
            addCriterion("link_num <>", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumGreaterThan(Integer value) {
            addCriterion("link_num >", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_num >=", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumLessThan(Integer value) {
            addCriterion("link_num <", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumLessThanOrEqualTo(Integer value) {
            addCriterion("link_num <=", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumIn(List<Integer> values) {
            addCriterion("link_num in", values, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumNotIn(List<Integer> values) {
            addCriterion("link_num not in", values, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumBetween(Integer value1, Integer value2) {
            addCriterion("link_num between", value1, value2, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumNotBetween(Integer value1, Integer value2) {
            addCriterion("link_num not between", value1, value2, "linkNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumIsNull() {
            addCriterion("xcx_order_num is null");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumIsNotNull() {
            addCriterion("xcx_order_num is not null");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumEqualTo(Integer value) {
            addCriterion("xcx_order_num =", value, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumNotEqualTo(Integer value) {
            addCriterion("xcx_order_num <>", value, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumGreaterThan(Integer value) {
            addCriterion("xcx_order_num >", value, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("xcx_order_num >=", value, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumLessThan(Integer value) {
            addCriterion("xcx_order_num <", value, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("xcx_order_num <=", value, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumIn(List<Integer> values) {
            addCriterion("xcx_order_num in", values, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumNotIn(List<Integer> values) {
            addCriterion("xcx_order_num not in", values, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("xcx_order_num between", value1, value2, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andXcxOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("xcx_order_num not between", value1, value2, "xcxOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumIsNull() {
            addCriterion("app_order_num is null");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumIsNotNull() {
            addCriterion("app_order_num is not null");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumEqualTo(Integer value) {
            addCriterion("app_order_num =", value, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumNotEqualTo(Integer value) {
            addCriterion("app_order_num <>", value, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumGreaterThan(Integer value) {
            addCriterion("app_order_num >", value, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_order_num >=", value, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumLessThan(Integer value) {
            addCriterion("app_order_num <", value, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("app_order_num <=", value, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumIn(List<Integer> values) {
            addCriterion("app_order_num in", values, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumNotIn(List<Integer> values) {
            addCriterion("app_order_num not in", values, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("app_order_num between", value1, value2, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andAppOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("app_order_num not between", value1, value2, "appOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumIsNull() {
            addCriterion("link_order_num is null");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumIsNotNull() {
            addCriterion("link_order_num is not null");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumEqualTo(Integer value) {
            addCriterion("link_order_num =", value, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumNotEqualTo(Integer value) {
            addCriterion("link_order_num <>", value, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumGreaterThan(Integer value) {
            addCriterion("link_order_num >", value, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_order_num >=", value, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumLessThan(Integer value) {
            addCriterion("link_order_num <", value, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("link_order_num <=", value, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumIn(List<Integer> values) {
            addCriterion("link_order_num in", values, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumNotIn(List<Integer> values) {
            addCriterion("link_order_num not in", values, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("link_order_num between", value1, value2, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andLinkOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("link_order_num not between", value1, value2, "linkOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumIsNull() {
            addCriterion("new_order_num is null");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumIsNotNull() {
            addCriterion("new_order_num is not null");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumEqualTo(Integer value) {
            addCriterion("new_order_num =", value, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumNotEqualTo(Integer value) {
            addCriterion("new_order_num <>", value, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumGreaterThan(Integer value) {
            addCriterion("new_order_num >", value, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("new_order_num >=", value, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumLessThan(Integer value) {
            addCriterion("new_order_num <", value, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("new_order_num <=", value, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumIn(List<Integer> values) {
            addCriterion("new_order_num in", values, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumNotIn(List<Integer> values) {
            addCriterion("new_order_num not in", values, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("new_order_num between", value1, value2, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andNewOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("new_order_num not between", value1, value2, "newOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumIsNull() {
            addCriterion("old_order_num is null");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumIsNotNull() {
            addCriterion("old_order_num is not null");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumEqualTo(Integer value) {
            addCriterion("old_order_num =", value, "oldOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumNotEqualTo(Integer value) {
            addCriterion("old_order_num <>", value, "oldOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumGreaterThan(Integer value) {
            addCriterion("old_order_num >", value, "oldOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("old_order_num >=", value, "oldOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumLessThan(Integer value) {
            addCriterion("old_order_num <", value, "oldOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("old_order_num <=", value, "oldOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumIn(List<Integer> values) {
            addCriterion("old_order_num in", values, "oldOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumNotIn(List<Integer> values) {
            addCriterion("old_order_num not in", values, "oldOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("old_order_num between", value1, value2, "oldOrderNum");
            return (Criteria) this;
        }

        public Criteria andOldOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("old_order_num not between", value1, value2, "oldOrderNum");
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