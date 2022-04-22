package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RepConversionDayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RepConversionDayExample() {
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

        public Criteria andXcxOpenNumIsNull() {
            addCriterion("xcx_open_num is null");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumIsNotNull() {
            addCriterion("xcx_open_num is not null");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumEqualTo(Integer value) {
            addCriterion("xcx_open_num =", value, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumNotEqualTo(Integer value) {
            addCriterion("xcx_open_num <>", value, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumGreaterThan(Integer value) {
            addCriterion("xcx_open_num >", value, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("xcx_open_num >=", value, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumLessThan(Integer value) {
            addCriterion("xcx_open_num <", value, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumLessThanOrEqualTo(Integer value) {
            addCriterion("xcx_open_num <=", value, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumIn(List<Integer> values) {
            addCriterion("xcx_open_num in", values, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumNotIn(List<Integer> values) {
            addCriterion("xcx_open_num not in", values, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumBetween(Integer value1, Integer value2) {
            addCriterion("xcx_open_num between", value1, value2, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andXcxOpenNumNotBetween(Integer value1, Integer value2) {
            addCriterion("xcx_open_num not between", value1, value2, "xcxOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumIsNull() {
            addCriterion("app_open_num is null");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumIsNotNull() {
            addCriterion("app_open_num is not null");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumEqualTo(Integer value) {
            addCriterion("app_open_num =", value, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumNotEqualTo(Integer value) {
            addCriterion("app_open_num <>", value, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumGreaterThan(Integer value) {
            addCriterion("app_open_num >", value, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_open_num >=", value, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumLessThan(Integer value) {
            addCriterion("app_open_num <", value, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumLessThanOrEqualTo(Integer value) {
            addCriterion("app_open_num <=", value, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumIn(List<Integer> values) {
            addCriterion("app_open_num in", values, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumNotIn(List<Integer> values) {
            addCriterion("app_open_num not in", values, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumBetween(Integer value1, Integer value2) {
            addCriterion("app_open_num between", value1, value2, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andAppOpenNumNotBetween(Integer value1, Integer value2) {
            addCriterion("app_open_num not between", value1, value2, "appOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumIsNull() {
            addCriterion("link_open_num is null");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumIsNotNull() {
            addCriterion("link_open_num is not null");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumEqualTo(Integer value) {
            addCriterion("link_open_num =", value, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumNotEqualTo(Integer value) {
            addCriterion("link_open_num <>", value, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumGreaterThan(Integer value) {
            addCriterion("link_open_num >", value, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_open_num >=", value, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumLessThan(Integer value) {
            addCriterion("link_open_num <", value, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumLessThanOrEqualTo(Integer value) {
            addCriterion("link_open_num <=", value, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumIn(List<Integer> values) {
            addCriterion("link_open_num in", values, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumNotIn(List<Integer> values) {
            addCriterion("link_open_num not in", values, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumBetween(Integer value1, Integer value2) {
            addCriterion("link_open_num between", value1, value2, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andLinkOpenNumNotBetween(Integer value1, Integer value2) {
            addCriterion("link_open_num not between", value1, value2, "linkOpenNum");
            return (Criteria) this;
        }

        public Criteria andTransRateIsNull() {
            addCriterion("trans_rate is null");
            return (Criteria) this;
        }

        public Criteria andTransRateIsNotNull() {
            addCriterion("trans_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTransRateEqualTo(BigDecimal value) {
            addCriterion("trans_rate =", value, "transRate");
            return (Criteria) this;
        }

        public Criteria andTransRateNotEqualTo(BigDecimal value) {
            addCriterion("trans_rate <>", value, "transRate");
            return (Criteria) this;
        }

        public Criteria andTransRateGreaterThan(BigDecimal value) {
            addCriterion("trans_rate >", value, "transRate");
            return (Criteria) this;
        }

        public Criteria andTransRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trans_rate >=", value, "transRate");
            return (Criteria) this;
        }

        public Criteria andTransRateLessThan(BigDecimal value) {
            addCriterion("trans_rate <", value, "transRate");
            return (Criteria) this;
        }

        public Criteria andTransRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trans_rate <=", value, "transRate");
            return (Criteria) this;
        }

        public Criteria andTransRateIn(List<BigDecimal> values) {
            addCriterion("trans_rate in", values, "transRate");
            return (Criteria) this;
        }

        public Criteria andTransRateNotIn(List<BigDecimal> values) {
            addCriterion("trans_rate not in", values, "transRate");
            return (Criteria) this;
        }

        public Criteria andTransRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trans_rate between", value1, value2, "transRate");
            return (Criteria) this;
        }

        public Criteria andTransRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trans_rate not between", value1, value2, "transRate");
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