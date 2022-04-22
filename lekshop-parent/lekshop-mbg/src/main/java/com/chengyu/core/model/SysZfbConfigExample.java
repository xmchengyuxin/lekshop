package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysZfbConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysZfbConfigExample() {
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

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyIsNull() {
            addCriterion("zfb_public_key is null");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyIsNotNull() {
            addCriterion("zfb_public_key is not null");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyEqualTo(String value) {
            addCriterion("zfb_public_key =", value, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyNotEqualTo(String value) {
            addCriterion("zfb_public_key <>", value, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyGreaterThan(String value) {
            addCriterion("zfb_public_key >", value, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyGreaterThanOrEqualTo(String value) {
            addCriterion("zfb_public_key >=", value, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyLessThan(String value) {
            addCriterion("zfb_public_key <", value, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyLessThanOrEqualTo(String value) {
            addCriterion("zfb_public_key <=", value, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyLike(String value) {
            addCriterion("zfb_public_key like", value, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyNotLike(String value) {
            addCriterion("zfb_public_key not like", value, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyIn(List<String> values) {
            addCriterion("zfb_public_key in", values, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyNotIn(List<String> values) {
            addCriterion("zfb_public_key not in", values, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyBetween(String value1, String value2) {
            addCriterion("zfb_public_key between", value1, value2, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andZfbPublicKeyNotBetween(String value1, String value2) {
            addCriterion("zfb_public_key not between", value1, value2, "zfbPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyIsNull() {
            addCriterion("app_public_key is null");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyIsNotNull() {
            addCriterion("app_public_key is not null");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyEqualTo(String value) {
            addCriterion("app_public_key =", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyNotEqualTo(String value) {
            addCriterion("app_public_key <>", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyGreaterThan(String value) {
            addCriterion("app_public_key >", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyGreaterThanOrEqualTo(String value) {
            addCriterion("app_public_key >=", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyLessThan(String value) {
            addCriterion("app_public_key <", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyLessThanOrEqualTo(String value) {
            addCriterion("app_public_key <=", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyLike(String value) {
            addCriterion("app_public_key like", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyNotLike(String value) {
            addCriterion("app_public_key not like", value, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyIn(List<String> values) {
            addCriterion("app_public_key in", values, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyNotIn(List<String> values) {
            addCriterion("app_public_key not in", values, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyBetween(String value1, String value2) {
            addCriterion("app_public_key between", value1, value2, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andAppPublicKeyNotBetween(String value1, String value2) {
            addCriterion("app_public_key not between", value1, value2, "appPublicKey");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlIsNull() {
            addCriterion("pay_notify_url is null");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlIsNotNull() {
            addCriterion("pay_notify_url is not null");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlEqualTo(String value) {
            addCriterion("pay_notify_url =", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlNotEqualTo(String value) {
            addCriterion("pay_notify_url <>", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlGreaterThan(String value) {
            addCriterion("pay_notify_url >", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pay_notify_url >=", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlLessThan(String value) {
            addCriterion("pay_notify_url <", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlLessThanOrEqualTo(String value) {
            addCriterion("pay_notify_url <=", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlLike(String value) {
            addCriterion("pay_notify_url like", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlNotLike(String value) {
            addCriterion("pay_notify_url not like", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlIn(List<String> values) {
            addCriterion("pay_notify_url in", values, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlNotIn(List<String> values) {
            addCriterion("pay_notify_url not in", values, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlBetween(String value1, String value2) {
            addCriterion("pay_notify_url between", value1, value2, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlNotBetween(String value1, String value2) {
            addCriterion("pay_notify_url not between", value1, value2, "payNotifyUrl");
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