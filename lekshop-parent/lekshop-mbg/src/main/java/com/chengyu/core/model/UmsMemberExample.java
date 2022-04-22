package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UmsMemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsMemberExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupIconIsNull() {
            addCriterion("group_icon is null");
            return (Criteria) this;
        }

        public Criteria andGroupIconIsNotNull() {
            addCriterion("group_icon is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIconEqualTo(String value) {
            addCriterion("group_icon =", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconNotEqualTo(String value) {
            addCriterion("group_icon <>", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconGreaterThan(String value) {
            addCriterion("group_icon >", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconGreaterThanOrEqualTo(String value) {
            addCriterion("group_icon >=", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconLessThan(String value) {
            addCriterion("group_icon <", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconLessThanOrEqualTo(String value) {
            addCriterion("group_icon <=", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconLike(String value) {
            addCriterion("group_icon like", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconNotLike(String value) {
            addCriterion("group_icon not like", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconIn(List<String> values) {
            addCriterion("group_icon in", values, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconNotIn(List<String> values) {
            addCriterion("group_icon not in", values, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconBetween(String value1, String value2) {
            addCriterion("group_icon between", value1, value2, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconNotBetween(String value1, String value2) {
            addCriterion("group_icon not between", value1, value2, "groupIcon");
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdIsNull() {
            addCriterion("kf_open_id is null");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdIsNotNull() {
            addCriterion("kf_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdEqualTo(String value) {
            addCriterion("kf_open_id =", value, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdNotEqualTo(String value) {
            addCriterion("kf_open_id <>", value, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdGreaterThan(String value) {
            addCriterion("kf_open_id >", value, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("kf_open_id >=", value, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdLessThan(String value) {
            addCriterion("kf_open_id <", value, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdLessThanOrEqualTo(String value) {
            addCriterion("kf_open_id <=", value, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdLike(String value) {
            addCriterion("kf_open_id like", value, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdNotLike(String value) {
            addCriterion("kf_open_id not like", value, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdIn(List<String> values) {
            addCriterion("kf_open_id in", values, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdNotIn(List<String> values) {
            addCriterion("kf_open_id not in", values, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdBetween(String value1, String value2) {
            addCriterion("kf_open_id between", value1, value2, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andKfOpenIdNotBetween(String value1, String value2) {
            addCriterion("kf_open_id not between", value1, value2, "kfOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdIsNull() {
            addCriterion("xcx_open_id is null");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdIsNotNull() {
            addCriterion("xcx_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdEqualTo(String value) {
            addCriterion("xcx_open_id =", value, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdNotEqualTo(String value) {
            addCriterion("xcx_open_id <>", value, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdGreaterThan(String value) {
            addCriterion("xcx_open_id >", value, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("xcx_open_id >=", value, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdLessThan(String value) {
            addCriterion("xcx_open_id <", value, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdLessThanOrEqualTo(String value) {
            addCriterion("xcx_open_id <=", value, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdLike(String value) {
            addCriterion("xcx_open_id like", value, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdNotLike(String value) {
            addCriterion("xcx_open_id not like", value, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdIn(List<String> values) {
            addCriterion("xcx_open_id in", values, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdNotIn(List<String> values) {
            addCriterion("xcx_open_id not in", values, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdBetween(String value1, String value2) {
            addCriterion("xcx_open_id between", value1, value2, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andXcxOpenIdNotBetween(String value1, String value2) {
            addCriterion("xcx_open_id not between", value1, value2, "xcxOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdIsNull() {
            addCriterion("gzh_open_id is null");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdIsNotNull() {
            addCriterion("gzh_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdEqualTo(String value) {
            addCriterion("gzh_open_id =", value, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdNotEqualTo(String value) {
            addCriterion("gzh_open_id <>", value, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdGreaterThan(String value) {
            addCriterion("gzh_open_id >", value, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("gzh_open_id >=", value, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdLessThan(String value) {
            addCriterion("gzh_open_id <", value, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdLessThanOrEqualTo(String value) {
            addCriterion("gzh_open_id <=", value, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdLike(String value) {
            addCriterion("gzh_open_id like", value, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdNotLike(String value) {
            addCriterion("gzh_open_id not like", value, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdIn(List<String> values) {
            addCriterion("gzh_open_id in", values, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdNotIn(List<String> values) {
            addCriterion("gzh_open_id not in", values, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdBetween(String value1, String value2) {
            addCriterion("gzh_open_id between", value1, value2, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andGzhOpenIdNotBetween(String value1, String value2) {
            addCriterion("gzh_open_id not between", value1, value2, "gzhOpenId");
            return (Criteria) this;
        }

        public Criteria andUnionidIsNull() {
            addCriterion("unionid is null");
            return (Criteria) this;
        }

        public Criteria andUnionidIsNotNull() {
            addCriterion("unionid is not null");
            return (Criteria) this;
        }

        public Criteria andUnionidEqualTo(String value) {
            addCriterion("unionid =", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotEqualTo(String value) {
            addCriterion("unionid <>", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidGreaterThan(String value) {
            addCriterion("unionid >", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidGreaterThanOrEqualTo(String value) {
            addCriterion("unionid >=", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidLessThan(String value) {
            addCriterion("unionid <", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidLessThanOrEqualTo(String value) {
            addCriterion("unionid <=", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidLike(String value) {
            addCriterion("unionid like", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotLike(String value) {
            addCriterion("unionid not like", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidIn(List<String> values) {
            addCriterion("unionid in", values, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotIn(List<String> values) {
            addCriterion("unionid not in", values, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidBetween(String value1, String value2) {
            addCriterion("unionid between", value1, value2, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotBetween(String value1, String value2) {
            addCriterion("unionid not between", value1, value2, "unionid");
            return (Criteria) this;
        }

        public Criteria andHeadImgIsNull() {
            addCriterion("head_img is null");
            return (Criteria) this;
        }

        public Criteria andHeadImgIsNotNull() {
            addCriterion("head_img is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImgEqualTo(String value) {
            addCriterion("head_img =", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotEqualTo(String value) {
            addCriterion("head_img <>", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgGreaterThan(String value) {
            addCriterion("head_img >", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("head_img >=", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgLessThan(String value) {
            addCriterion("head_img <", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgLessThanOrEqualTo(String value) {
            addCriterion("head_img <=", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgLike(String value) {
            addCriterion("head_img like", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotLike(String value) {
            addCriterion("head_img not like", value, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgIn(List<String> values) {
            addCriterion("head_img in", values, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotIn(List<String> values) {
            addCriterion("head_img not in", values, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgBetween(String value1, String value2) {
            addCriterion("head_img between", value1, value2, "headImg");
            return (Criteria) this;
        }

        public Criteria andHeadImgNotBetween(String value1, String value2) {
            addCriterion("head_img not between", value1, value2, "headImg");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNull() {
            addCriterion("weixin is null");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNotNull() {
            addCriterion("weixin is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinEqualTo(String value) {
            addCriterion("weixin =", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotEqualTo(String value) {
            addCriterion("weixin <>", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThan(String value) {
            addCriterion("weixin >", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThanOrEqualTo(String value) {
            addCriterion("weixin >=", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThan(String value) {
            addCriterion("weixin <", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThanOrEqualTo(String value) {
            addCriterion("weixin <=", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLike(String value) {
            addCriterion("weixin like", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotLike(String value) {
            addCriterion("weixin not like", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinIn(List<String> values) {
            addCriterion("weixin in", values, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotIn(List<String> values) {
            addCriterion("weixin not in", values, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinBetween(String value1, String value2) {
            addCriterion("weixin between", value1, value2, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotBetween(String value1, String value2) {
            addCriterion("weixin not between", value1, value2, "weixin");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(Integer value) {
            addCriterion("age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(Integer value) {
            addCriterion("age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(Integer value) {
            addCriterion("age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(Integer value) {
            addCriterion("age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(Integer value) {
            addCriterion("age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<Integer> values) {
            addCriterion("age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<Integer> values) {
            addCriterion("age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(Integer value1, Integer value2) {
            addCriterion("age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNull() {
            addCriterion("pay_password is null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNotNull() {
            addCriterion("pay_password is not null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordEqualTo(String value) {
            addCriterion("pay_password =", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotEqualTo(String value) {
            addCriterion("pay_password <>", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThan(String value) {
            addCriterion("pay_password >", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("pay_password >=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThan(String value) {
            addCriterion("pay_password <", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThanOrEqualTo(String value) {
            addCriterion("pay_password <=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLike(String value) {
            addCriterion("pay_password like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotLike(String value) {
            addCriterion("pay_password not like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIn(List<String> values) {
            addCriterion("pay_password in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotIn(List<String> values) {
            addCriterion("pay_password not in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordBetween(String value1, String value2) {
            addCriterion("pay_password between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotBetween(String value1, String value2) {
            addCriterion("pay_password not between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNull() {
            addCriterion("province_code is null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNotNull() {
            addCriterion("province_code is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeEqualTo(String value) {
            addCriterion("province_code =", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotEqualTo(String value) {
            addCriterion("province_code <>", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThan(String value) {
            addCriterion("province_code >", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("province_code >=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThan(String value) {
            addCriterion("province_code <", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThanOrEqualTo(String value) {
            addCriterion("province_code <=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLike(String value) {
            addCriterion("province_code like", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotLike(String value) {
            addCriterion("province_code not like", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIn(List<String> values) {
            addCriterion("province_code in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotIn(List<String> values) {
            addCriterion("province_code not in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeBetween(String value1, String value2) {
            addCriterion("province_code between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotBetween(String value1, String value2) {
            addCriterion("province_code not between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNull() {
            addCriterion("city_code is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("city_code is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("city_code =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(String value) {
            addCriterion("city_code <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(String value) {
            addCriterion("city_code >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("city_code >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(String value) {
            addCriterion("city_code <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(String value) {
            addCriterion("city_code <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLike(String value) {
            addCriterion("city_code like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotLike(String value) {
            addCriterion("city_code not like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<String> values) {
            addCriterion("city_code in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<String> values) {
            addCriterion("city_code not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(String value1, String value2) {
            addCriterion("city_code between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(String value1, String value2) {
            addCriterion("city_code not between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPersonSignIsNull() {
            addCriterion("person_sign is null");
            return (Criteria) this;
        }

        public Criteria andPersonSignIsNotNull() {
            addCriterion("person_sign is not null");
            return (Criteria) this;
        }

        public Criteria andPersonSignEqualTo(String value) {
            addCriterion("person_sign =", value, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignNotEqualTo(String value) {
            addCriterion("person_sign <>", value, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignGreaterThan(String value) {
            addCriterion("person_sign >", value, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignGreaterThanOrEqualTo(String value) {
            addCriterion("person_sign >=", value, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignLessThan(String value) {
            addCriterion("person_sign <", value, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignLessThanOrEqualTo(String value) {
            addCriterion("person_sign <=", value, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignLike(String value) {
            addCriterion("person_sign like", value, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignNotLike(String value) {
            addCriterion("person_sign not like", value, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignIn(List<String> values) {
            addCriterion("person_sign in", values, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignNotIn(List<String> values) {
            addCriterion("person_sign not in", values, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignBetween(String value1, String value2) {
            addCriterion("person_sign between", value1, value2, "personSign");
            return (Criteria) this;
        }

        public Criteria andPersonSignNotBetween(String value1, String value2) {
            addCriterion("person_sign not between", value1, value2, "personSign");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("method is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(Integer value) {
            addCriterion("method =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(Integer value) {
            addCriterion("method <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(Integer value) {
            addCriterion("method >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(Integer value) {
            addCriterion("method >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(Integer value) {
            addCriterion("method <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(Integer value) {
            addCriterion("method <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<Integer> values) {
            addCriterion("method in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<Integer> values) {
            addCriterion("method not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(Integer value1, Integer value2) {
            addCriterion("method between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(Integer value1, Integer value2) {
            addCriterion("method not between", value1, value2, "method");
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

        public Criteria andPhoneStatusIsNull() {
            addCriterion("phone_status is null");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusIsNotNull() {
            addCriterion("phone_status is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusEqualTo(Integer value) {
            addCriterion("phone_status =", value, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusNotEqualTo(Integer value) {
            addCriterion("phone_status <>", value, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusGreaterThan(Integer value) {
            addCriterion("phone_status >", value, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("phone_status >=", value, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusLessThan(Integer value) {
            addCriterion("phone_status <", value, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusLessThanOrEqualTo(Integer value) {
            addCriterion("phone_status <=", value, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusIn(List<Integer> values) {
            addCriterion("phone_status in", values, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusNotIn(List<Integer> values) {
            addCriterion("phone_status not in", values, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusBetween(Integer value1, Integer value2) {
            addCriterion("phone_status between", value1, value2, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andPhoneStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("phone_status not between", value1, value2, "phoneStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusIsNull() {
            addCriterion("realname_status is null");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusIsNotNull() {
            addCriterion("realname_status is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusEqualTo(Integer value) {
            addCriterion("realname_status =", value, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusNotEqualTo(Integer value) {
            addCriterion("realname_status <>", value, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusGreaterThan(Integer value) {
            addCriterion("realname_status >", value, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("realname_status >=", value, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusLessThan(Integer value) {
            addCriterion("realname_status <", value, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusLessThanOrEqualTo(Integer value) {
            addCriterion("realname_status <=", value, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusIn(List<Integer> values) {
            addCriterion("realname_status in", values, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusNotIn(List<Integer> values) {
            addCriterion("realname_status not in", values, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusBetween(Integer value1, Integer value2) {
            addCriterion("realname_status between", value1, value2, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andRealnameStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("realname_status not between", value1, value2, "realnameStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusIsNull() {
            addCriterion("bank_status is null");
            return (Criteria) this;
        }

        public Criteria andBankStatusIsNotNull() {
            addCriterion("bank_status is not null");
            return (Criteria) this;
        }

        public Criteria andBankStatusEqualTo(Integer value) {
            addCriterion("bank_status =", value, "bankStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusNotEqualTo(Integer value) {
            addCriterion("bank_status <>", value, "bankStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusGreaterThan(Integer value) {
            addCriterion("bank_status >", value, "bankStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_status >=", value, "bankStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusLessThan(Integer value) {
            addCriterion("bank_status <", value, "bankStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusLessThanOrEqualTo(Integer value) {
            addCriterion("bank_status <=", value, "bankStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusIn(List<Integer> values) {
            addCriterion("bank_status in", values, "bankStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusNotIn(List<Integer> values) {
            addCriterion("bank_status not in", values, "bankStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusBetween(Integer value1, Integer value2) {
            addCriterion("bank_status between", value1, value2, "bankStatus");
            return (Criteria) this;
        }

        public Criteria andBankStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_status not between", value1, value2, "bankStatus");
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

        public Criteria andTjrIdIsNull() {
            addCriterion("tjr_id is null");
            return (Criteria) this;
        }

        public Criteria andTjrIdIsNotNull() {
            addCriterion("tjr_id is not null");
            return (Criteria) this;
        }

        public Criteria andTjrIdEqualTo(Integer value) {
            addCriterion("tjr_id =", value, "tjrId");
            return (Criteria) this;
        }

        public Criteria andTjrIdNotEqualTo(Integer value) {
            addCriterion("tjr_id <>", value, "tjrId");
            return (Criteria) this;
        }

        public Criteria andTjrIdGreaterThan(Integer value) {
            addCriterion("tjr_id >", value, "tjrId");
            return (Criteria) this;
        }

        public Criteria andTjrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tjr_id >=", value, "tjrId");
            return (Criteria) this;
        }

        public Criteria andTjrIdLessThan(Integer value) {
            addCriterion("tjr_id <", value, "tjrId");
            return (Criteria) this;
        }

        public Criteria andTjrIdLessThanOrEqualTo(Integer value) {
            addCriterion("tjr_id <=", value, "tjrId");
            return (Criteria) this;
        }

        public Criteria andTjrIdIn(List<Integer> values) {
            addCriterion("tjr_id in", values, "tjrId");
            return (Criteria) this;
        }

        public Criteria andTjrIdNotIn(List<Integer> values) {
            addCriterion("tjr_id not in", values, "tjrId");
            return (Criteria) this;
        }

        public Criteria andTjrIdBetween(Integer value1, Integer value2) {
            addCriterion("tjr_id between", value1, value2, "tjrId");
            return (Criteria) this;
        }

        public Criteria andTjrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tjr_id not between", value1, value2, "tjrId");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIsNull() {
            addCriterion("invite_code is null");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIsNotNull() {
            addCriterion("invite_code is not null");
            return (Criteria) this;
        }

        public Criteria andInviteCodeEqualTo(String value) {
            addCriterion("invite_code =", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotEqualTo(String value) {
            addCriterion("invite_code <>", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeGreaterThan(String value) {
            addCriterion("invite_code >", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invite_code >=", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLessThan(String value) {
            addCriterion("invite_code <", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLessThanOrEqualTo(String value) {
            addCriterion("invite_code <=", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLike(String value) {
            addCriterion("invite_code like", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotLike(String value) {
            addCriterion("invite_code not like", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIn(List<String> values) {
            addCriterion("invite_code in", values, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotIn(List<String> values) {
            addCriterion("invite_code not in", values, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeBetween(String value1, String value2) {
            addCriterion("invite_code between", value1, value2, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotBetween(String value1, String value2) {
            addCriterion("invite_code not between", value1, value2, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andRegisterIpIsNull() {
            addCriterion("register_ip is null");
            return (Criteria) this;
        }

        public Criteria andRegisterIpIsNotNull() {
            addCriterion("register_ip is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterIpEqualTo(String value) {
            addCriterion("register_ip =", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpNotEqualTo(String value) {
            addCriterion("register_ip <>", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpGreaterThan(String value) {
            addCriterion("register_ip >", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpGreaterThanOrEqualTo(String value) {
            addCriterion("register_ip >=", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpLessThan(String value) {
            addCriterion("register_ip <", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpLessThanOrEqualTo(String value) {
            addCriterion("register_ip <=", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpLike(String value) {
            addCriterion("register_ip like", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpNotLike(String value) {
            addCriterion("register_ip not like", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpIn(List<String> values) {
            addCriterion("register_ip in", values, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpNotIn(List<String> values) {
            addCriterion("register_ip not in", values, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpBetween(String value1, String value2) {
            addCriterion("register_ip between", value1, value2, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpNotBetween(String value1, String value2) {
            addCriterion("register_ip not between", value1, value2, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressIsNull() {
            addCriterion("register_ip_address is null");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressIsNotNull() {
            addCriterion("register_ip_address is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressEqualTo(String value) {
            addCriterion("register_ip_address =", value, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressNotEqualTo(String value) {
            addCriterion("register_ip_address <>", value, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressGreaterThan(String value) {
            addCriterion("register_ip_address >", value, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("register_ip_address >=", value, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressLessThan(String value) {
            addCriterion("register_ip_address <", value, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressLessThanOrEqualTo(String value) {
            addCriterion("register_ip_address <=", value, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressLike(String value) {
            addCriterion("register_ip_address like", value, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressNotLike(String value) {
            addCriterion("register_ip_address not like", value, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressIn(List<String> values) {
            addCriterion("register_ip_address in", values, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressNotIn(List<String> values) {
            addCriterion("register_ip_address not in", values, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressBetween(String value1, String value2) {
            addCriterion("register_ip_address between", value1, value2, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterIpAddressNotBetween(String value1, String value2) {
            addCriterion("register_ip_address not between", value1, value2, "registerIpAddress");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlIsNull() {
            addCriterion("register_url is null");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlIsNotNull() {
            addCriterion("register_url is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlEqualTo(String value) {
            addCriterion("register_url =", value, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlNotEqualTo(String value) {
            addCriterion("register_url <>", value, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlGreaterThan(String value) {
            addCriterion("register_url >", value, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlGreaterThanOrEqualTo(String value) {
            addCriterion("register_url >=", value, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlLessThan(String value) {
            addCriterion("register_url <", value, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlLessThanOrEqualTo(String value) {
            addCriterion("register_url <=", value, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlLike(String value) {
            addCriterion("register_url like", value, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlNotLike(String value) {
            addCriterion("register_url not like", value, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlIn(List<String> values) {
            addCriterion("register_url in", values, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlNotIn(List<String> values) {
            addCriterion("register_url not in", values, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlBetween(String value1, String value2) {
            addCriterion("register_url between", value1, value2, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andRegisterUrlNotBetween(String value1, String value2) {
            addCriterion("register_url not between", value1, value2, "registerUrl");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdIsNull() {
            addCriterion("mission_config_id is null");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdIsNotNull() {
            addCriterion("mission_config_id is not null");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdEqualTo(Integer value) {
            addCriterion("mission_config_id =", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdNotEqualTo(Integer value) {
            addCriterion("mission_config_id <>", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdGreaterThan(Integer value) {
            addCriterion("mission_config_id >", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mission_config_id >=", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdLessThan(Integer value) {
            addCriterion("mission_config_id <", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdLessThanOrEqualTo(Integer value) {
            addCriterion("mission_config_id <=", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdIn(List<Integer> values) {
            addCriterion("mission_config_id in", values, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdNotIn(List<Integer> values) {
            addCriterion("mission_config_id not in", values, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdBetween(Integer value1, Integer value2) {
            addCriterion("mission_config_id between", value1, value2, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mission_config_id not between", value1, value2, "missionConfigId");
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

        public Criteria andRegistrationIdIsNull() {
            addCriterion("registration_id is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdIsNotNull() {
            addCriterion("registration_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdEqualTo(String value) {
            addCriterion("registration_id =", value, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdNotEqualTo(String value) {
            addCriterion("registration_id <>", value, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdGreaterThan(String value) {
            addCriterion("registration_id >", value, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdGreaterThanOrEqualTo(String value) {
            addCriterion("registration_id >=", value, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdLessThan(String value) {
            addCriterion("registration_id <", value, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdLessThanOrEqualTo(String value) {
            addCriterion("registration_id <=", value, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdLike(String value) {
            addCriterion("registration_id like", value, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdNotLike(String value) {
            addCriterion("registration_id not like", value, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdIn(List<String> values) {
            addCriterion("registration_id in", values, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdNotIn(List<String> values) {
            addCriterion("registration_id not in", values, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdBetween(String value1, String value2) {
            addCriterion("registration_id between", value1, value2, "registrationId");
            return (Criteria) this;
        }

        public Criteria andRegistrationIdNotBetween(String value1, String value2) {
            addCriterion("registration_id not between", value1, value2, "registrationId");
            return (Criteria) this;
        }

        public Criteria andSecretIsNull() {
            addCriterion("secret is null");
            return (Criteria) this;
        }

        public Criteria andSecretIsNotNull() {
            addCriterion("secret is not null");
            return (Criteria) this;
        }

        public Criteria andSecretEqualTo(String value) {
            addCriterion("secret =", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotEqualTo(String value) {
            addCriterion("secret <>", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretGreaterThan(String value) {
            addCriterion("secret >", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretGreaterThanOrEqualTo(String value) {
            addCriterion("secret >=", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLessThan(String value) {
            addCriterion("secret <", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLessThanOrEqualTo(String value) {
            addCriterion("secret <=", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLike(String value) {
            addCriterion("secret like", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotLike(String value) {
            addCriterion("secret not like", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretIn(List<String> values) {
            addCriterion("secret in", values, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotIn(List<String> values) {
            addCriterion("secret not in", values, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretBetween(String value1, String value2) {
            addCriterion("secret between", value1, value2, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotBetween(String value1, String value2) {
            addCriterion("secret not between", value1, value2, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerIsNull() {
            addCriterion("secret_answer is null");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerIsNotNull() {
            addCriterion("secret_answer is not null");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerEqualTo(String value) {
            addCriterion("secret_answer =", value, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerNotEqualTo(String value) {
            addCriterion("secret_answer <>", value, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerGreaterThan(String value) {
            addCriterion("secret_answer >", value, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("secret_answer >=", value, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerLessThan(String value) {
            addCriterion("secret_answer <", value, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerLessThanOrEqualTo(String value) {
            addCriterion("secret_answer <=", value, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerLike(String value) {
            addCriterion("secret_answer like", value, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerNotLike(String value) {
            addCriterion("secret_answer not like", value, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerIn(List<String> values) {
            addCriterion("secret_answer in", values, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerNotIn(List<String> values) {
            addCriterion("secret_answer not in", values, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerBetween(String value1, String value2) {
            addCriterion("secret_answer between", value1, value2, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andSecretAnswerNotBetween(String value1, String value2) {
            addCriterion("secret_answer not between", value1, value2, "secretAnswer");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusIsNull() {
            addCriterion("punishment_status is null");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusIsNotNull() {
            addCriterion("punishment_status is not null");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusEqualTo(Integer value) {
            addCriterion("punishment_status =", value, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusNotEqualTo(Integer value) {
            addCriterion("punishment_status <>", value, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusGreaterThan(Integer value) {
            addCriterion("punishment_status >", value, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("punishment_status >=", value, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusLessThan(Integer value) {
            addCriterion("punishment_status <", value, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusLessThanOrEqualTo(Integer value) {
            addCriterion("punishment_status <=", value, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusIn(List<Integer> values) {
            addCriterion("punishment_status in", values, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusNotIn(List<Integer> values) {
            addCriterion("punishment_status not in", values, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusBetween(Integer value1, Integer value2) {
            addCriterion("punishment_status between", value1, value2, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andPunishmentStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("punishment_status not between", value1, value2, "punishmentStatus");
            return (Criteria) this;
        }

        public Criteria andTjrUidIsNull() {
            addCriterion("tjr_uid is null");
            return (Criteria) this;
        }

        public Criteria andTjrUidIsNotNull() {
            addCriterion("tjr_uid is not null");
            return (Criteria) this;
        }

        public Criteria andTjrUidEqualTo(Integer value) {
            addCriterion("tjr_uid =", value, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andTjrUidNotEqualTo(Integer value) {
            addCriterion("tjr_uid <>", value, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andTjrUidGreaterThan(Integer value) {
            addCriterion("tjr_uid >", value, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andTjrUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tjr_uid >=", value, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andTjrUidLessThan(Integer value) {
            addCriterion("tjr_uid <", value, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andTjrUidLessThanOrEqualTo(Integer value) {
            addCriterion("tjr_uid <=", value, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andTjrUidIn(List<Integer> values) {
            addCriterion("tjr_uid in", values, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andTjrUidNotIn(List<Integer> values) {
            addCriterion("tjr_uid not in", values, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andTjrUidBetween(Integer value1, Integer value2) {
            addCriterion("tjr_uid between", value1, value2, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andTjrUidNotBetween(Integer value1, Integer value2) {
            addCriterion("tjr_uid not between", value1, value2, "tjrUid");
            return (Criteria) this;
        }

        public Criteria andImeIdIsNull() {
            addCriterion("ime_id is null");
            return (Criteria) this;
        }

        public Criteria andImeIdIsNotNull() {
            addCriterion("ime_id is not null");
            return (Criteria) this;
        }

        public Criteria andImeIdEqualTo(String value) {
            addCriterion("ime_id =", value, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdNotEqualTo(String value) {
            addCriterion("ime_id <>", value, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdGreaterThan(String value) {
            addCriterion("ime_id >", value, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ime_id >=", value, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdLessThan(String value) {
            addCriterion("ime_id <", value, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdLessThanOrEqualTo(String value) {
            addCriterion("ime_id <=", value, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdLike(String value) {
            addCriterion("ime_id like", value, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdNotLike(String value) {
            addCriterion("ime_id not like", value, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdIn(List<String> values) {
            addCriterion("ime_id in", values, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdNotIn(List<String> values) {
            addCriterion("ime_id not in", values, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdBetween(String value1, String value2) {
            addCriterion("ime_id between", value1, value2, "imeId");
            return (Criteria) this;
        }

        public Criteria andImeIdNotBetween(String value1, String value2) {
            addCriterion("ime_id not between", value1, value2, "imeId");
            return (Criteria) this;
        }

        public Criteria andInviteStatusIsNull() {
            addCriterion("invite_status is null");
            return (Criteria) this;
        }

        public Criteria andInviteStatusIsNotNull() {
            addCriterion("invite_status is not null");
            return (Criteria) this;
        }

        public Criteria andInviteStatusEqualTo(Integer value) {
            addCriterion("invite_status =", value, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andInviteStatusNotEqualTo(Integer value) {
            addCriterion("invite_status <>", value, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andInviteStatusGreaterThan(Integer value) {
            addCriterion("invite_status >", value, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andInviteStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("invite_status >=", value, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andInviteStatusLessThan(Integer value) {
            addCriterion("invite_status <", value, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andInviteStatusLessThanOrEqualTo(Integer value) {
            addCriterion("invite_status <=", value, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andInviteStatusIn(List<Integer> values) {
            addCriterion("invite_status in", values, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andInviteStatusNotIn(List<Integer> values) {
            addCriterion("invite_status not in", values, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andInviteStatusBetween(Integer value1, Integer value2) {
            addCriterion("invite_status between", value1, value2, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andInviteStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("invite_status not between", value1, value2, "inviteStatus");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonIsNull() {
            addCriterion("freeze_reason is null");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonIsNotNull() {
            addCriterion("freeze_reason is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonEqualTo(String value) {
            addCriterion("freeze_reason =", value, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonNotEqualTo(String value) {
            addCriterion("freeze_reason <>", value, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonGreaterThan(String value) {
            addCriterion("freeze_reason >", value, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonGreaterThanOrEqualTo(String value) {
            addCriterion("freeze_reason >=", value, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonLessThan(String value) {
            addCriterion("freeze_reason <", value, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonLessThanOrEqualTo(String value) {
            addCriterion("freeze_reason <=", value, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonLike(String value) {
            addCriterion("freeze_reason like", value, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonNotLike(String value) {
            addCriterion("freeze_reason not like", value, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonIn(List<String> values) {
            addCriterion("freeze_reason in", values, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonNotIn(List<String> values) {
            addCriterion("freeze_reason not in", values, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonBetween(String value1, String value2) {
            addCriterion("freeze_reason between", value1, value2, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andFreezeReasonNotBetween(String value1, String value2) {
            addCriterion("freeze_reason not between", value1, value2, "freezeReason");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitIsNull() {
            addCriterion("withdraw_limit is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitIsNotNull() {
            addCriterion("withdraw_limit is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitEqualTo(Integer value) {
            addCriterion("withdraw_limit =", value, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitNotEqualTo(Integer value) {
            addCriterion("withdraw_limit <>", value, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitGreaterThan(Integer value) {
            addCriterion("withdraw_limit >", value, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("withdraw_limit >=", value, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitLessThan(Integer value) {
            addCriterion("withdraw_limit <", value, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitLessThanOrEqualTo(Integer value) {
            addCriterion("withdraw_limit <=", value, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitIn(List<Integer> values) {
            addCriterion("withdraw_limit in", values, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitNotIn(List<Integer> values) {
            addCriterion("withdraw_limit not in", values, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_limit between", value1, value2, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andWithdrawLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_limit not between", value1, value2, "withdrawLimit");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceIsNull() {
            addCriterion("remind_voice is null");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceIsNotNull() {
            addCriterion("remind_voice is not null");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceEqualTo(Integer value) {
            addCriterion("remind_voice =", value, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceNotEqualTo(Integer value) {
            addCriterion("remind_voice <>", value, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceGreaterThan(Integer value) {
            addCriterion("remind_voice >", value, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceGreaterThanOrEqualTo(Integer value) {
            addCriterion("remind_voice >=", value, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceLessThan(Integer value) {
            addCriterion("remind_voice <", value, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceLessThanOrEqualTo(Integer value) {
            addCriterion("remind_voice <=", value, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceIn(List<Integer> values) {
            addCriterion("remind_voice in", values, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceNotIn(List<Integer> values) {
            addCriterion("remind_voice not in", values, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceBetween(Integer value1, Integer value2) {
            addCriterion("remind_voice between", value1, value2, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andRemindVoiceNotBetween(Integer value1, Integer value2) {
            addCriterion("remind_voice not between", value1, value2, "remindVoice");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusIsNull() {
            addCriterion("gzh_focus_status is null");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusIsNotNull() {
            addCriterion("gzh_focus_status is not null");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusEqualTo(Integer value) {
            addCriterion("gzh_focus_status =", value, "gzhFocusStatus");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusNotEqualTo(Integer value) {
            addCriterion("gzh_focus_status <>", value, "gzhFocusStatus");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusGreaterThan(Integer value) {
            addCriterion("gzh_focus_status >", value, "gzhFocusStatus");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("gzh_focus_status >=", value, "gzhFocusStatus");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusLessThan(Integer value) {
            addCriterion("gzh_focus_status <", value, "gzhFocusStatus");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusLessThanOrEqualTo(Integer value) {
            addCriterion("gzh_focus_status <=", value, "gzhFocusStatus");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusIn(List<Integer> values) {
            addCriterion("gzh_focus_status in", values, "gzhFocusStatus");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusNotIn(List<Integer> values) {
            addCriterion("gzh_focus_status not in", values, "gzhFocusStatus");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusBetween(Integer value1, Integer value2) {
            addCriterion("gzh_focus_status between", value1, value2, "gzhFocusStatus");
            return (Criteria) this;
        }

        public Criteria andGzhFocusStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("gzh_focus_status not between", value1, value2, "gzhFocusStatus");
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