package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WalkTrendsCommentLikeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WalkTrendsCommentLikeExample() {
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

        public Criteria andViewMemberIdIsNull() {
            addCriterion("view_member_id is null");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdIsNotNull() {
            addCriterion("view_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdEqualTo(Integer value) {
            addCriterion("view_member_id =", value, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdNotEqualTo(Integer value) {
            addCriterion("view_member_id <>", value, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdGreaterThan(Integer value) {
            addCriterion("view_member_id >", value, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("view_member_id >=", value, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdLessThan(Integer value) {
            addCriterion("view_member_id <", value, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("view_member_id <=", value, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdIn(List<Integer> values) {
            addCriterion("view_member_id in", values, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdNotIn(List<Integer> values) {
            addCriterion("view_member_id not in", values, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("view_member_id between", value1, value2, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("view_member_id not between", value1, value2, "viewMemberId");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameIsNull() {
            addCriterion("view_member_name is null");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameIsNotNull() {
            addCriterion("view_member_name is not null");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameEqualTo(String value) {
            addCriterion("view_member_name =", value, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameNotEqualTo(String value) {
            addCriterion("view_member_name <>", value, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameGreaterThan(String value) {
            addCriterion("view_member_name >", value, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("view_member_name >=", value, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameLessThan(String value) {
            addCriterion("view_member_name <", value, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameLessThanOrEqualTo(String value) {
            addCriterion("view_member_name <=", value, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameLike(String value) {
            addCriterion("view_member_name like", value, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameNotLike(String value) {
            addCriterion("view_member_name not like", value, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameIn(List<String> values) {
            addCriterion("view_member_name in", values, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameNotIn(List<String> values) {
            addCriterion("view_member_name not in", values, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameBetween(String value1, String value2) {
            addCriterion("view_member_name between", value1, value2, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberNameNotBetween(String value1, String value2) {
            addCriterion("view_member_name not between", value1, value2, "viewMemberName");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgIsNull() {
            addCriterion("view_member_head_img is null");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgIsNotNull() {
            addCriterion("view_member_head_img is not null");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgEqualTo(String value) {
            addCriterion("view_member_head_img =", value, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgNotEqualTo(String value) {
            addCriterion("view_member_head_img <>", value, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgGreaterThan(String value) {
            addCriterion("view_member_head_img >", value, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("view_member_head_img >=", value, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgLessThan(String value) {
            addCriterion("view_member_head_img <", value, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgLessThanOrEqualTo(String value) {
            addCriterion("view_member_head_img <=", value, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgLike(String value) {
            addCriterion("view_member_head_img like", value, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgNotLike(String value) {
            addCriterion("view_member_head_img not like", value, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgIn(List<String> values) {
            addCriterion("view_member_head_img in", values, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgNotIn(List<String> values) {
            addCriterion("view_member_head_img not in", values, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgBetween(String value1, String value2) {
            addCriterion("view_member_head_img between", value1, value2, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andViewMemberHeadImgNotBetween(String value1, String value2) {
            addCriterion("view_member_head_img not between", value1, value2, "viewMemberHeadImg");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNull() {
            addCriterion("comment_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNotNull() {
            addCriterion("comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentIdEqualTo(Integer value) {
            addCriterion("comment_id =", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotEqualTo(Integer value) {
            addCriterion("comment_id <>", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThan(Integer value) {
            addCriterion("comment_id >", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_id >=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThan(Integer value) {
            addCriterion("comment_id <", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("comment_id <=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIn(List<Integer> values) {
            addCriterion("comment_id in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotIn(List<Integer> values) {
            addCriterion("comment_id not in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("comment_id between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_id not between", value1, value2, "commentId");
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