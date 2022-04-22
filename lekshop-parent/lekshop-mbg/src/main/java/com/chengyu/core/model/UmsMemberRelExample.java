package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsMemberRelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsMemberRelExample() {
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

        public Criteria andUpMemberIdIsNull() {
            addCriterion("up_member_id is null");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdIsNotNull() {
            addCriterion("up_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdEqualTo(Integer value) {
            addCriterion("up_member_id =", value, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdNotEqualTo(Integer value) {
            addCriterion("up_member_id <>", value, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdGreaterThan(Integer value) {
            addCriterion("up_member_id >", value, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("up_member_id >=", value, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdLessThan(Integer value) {
            addCriterion("up_member_id <", value, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("up_member_id <=", value, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdIn(List<Integer> values) {
            addCriterion("up_member_id in", values, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdNotIn(List<Integer> values) {
            addCriterion("up_member_id not in", values, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("up_member_id between", value1, value2, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andUpMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("up_member_id not between", value1, value2, "upMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdIsNull() {
            addCriterion("down_member_id is null");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdIsNotNull() {
            addCriterion("down_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdEqualTo(Integer value) {
            addCriterion("down_member_id =", value, "downMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdNotEqualTo(Integer value) {
            addCriterion("down_member_id <>", value, "downMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdGreaterThan(Integer value) {
            addCriterion("down_member_id >", value, "downMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("down_member_id >=", value, "downMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdLessThan(Integer value) {
            addCriterion("down_member_id <", value, "downMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("down_member_id <=", value, "downMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdIn(List<Integer> values) {
            addCriterion("down_member_id in", values, "downMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdNotIn(List<Integer> values) {
            addCriterion("down_member_id not in", values, "downMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("down_member_id between", value1, value2, "downMemberId");
            return (Criteria) this;
        }

        public Criteria andDownMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("down_member_id not between", value1, value2, "downMemberId");
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