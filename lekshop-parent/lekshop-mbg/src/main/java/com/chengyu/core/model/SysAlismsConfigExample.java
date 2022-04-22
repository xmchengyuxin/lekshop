package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysAlismsConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysAlismsConfigExample() {
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

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
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

        public Criteria andSendUrlIsNull() {
            addCriterion("send_url is null");
            return (Criteria) this;
        }

        public Criteria andSendUrlIsNotNull() {
            addCriterion("send_url is not null");
            return (Criteria) this;
        }

        public Criteria andSendUrlEqualTo(String value) {
            addCriterion("send_url =", value, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlNotEqualTo(String value) {
            addCriterion("send_url <>", value, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlGreaterThan(String value) {
            addCriterion("send_url >", value, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlGreaterThanOrEqualTo(String value) {
            addCriterion("send_url >=", value, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlLessThan(String value) {
            addCriterion("send_url <", value, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlLessThanOrEqualTo(String value) {
            addCriterion("send_url <=", value, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlLike(String value) {
            addCriterion("send_url like", value, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlNotLike(String value) {
            addCriterion("send_url not like", value, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlIn(List<String> values) {
            addCriterion("send_url in", values, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlNotIn(List<String> values) {
            addCriterion("send_url not in", values, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlBetween(String value1, String value2) {
            addCriterion("send_url between", value1, value2, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andSendUrlNotBetween(String value1, String value2) {
            addCriterion("send_url not between", value1, value2, "sendUrl");
            return (Criteria) this;
        }

        public Criteria andServerPortIsNull() {
            addCriterion("server_port is null");
            return (Criteria) this;
        }

        public Criteria andServerPortIsNotNull() {
            addCriterion("server_port is not null");
            return (Criteria) this;
        }

        public Criteria andServerPortEqualTo(String value) {
            addCriterion("server_port =", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotEqualTo(String value) {
            addCriterion("server_port <>", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortGreaterThan(String value) {
            addCriterion("server_port >", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortGreaterThanOrEqualTo(String value) {
            addCriterion("server_port >=", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortLessThan(String value) {
            addCriterion("server_port <", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortLessThanOrEqualTo(String value) {
            addCriterion("server_port <=", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortLike(String value) {
            addCriterion("server_port like", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotLike(String value) {
            addCriterion("server_port not like", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortIn(List<String> values) {
            addCriterion("server_port in", values, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotIn(List<String> values) {
            addCriterion("server_port not in", values, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortBetween(String value1, String value2) {
            addCriterion("server_port between", value1, value2, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotBetween(String value1, String value2) {
            addCriterion("server_port not between", value1, value2, "serverPort");
            return (Criteria) this;
        }

        public Criteria andSignNameIsNull() {
            addCriterion("sign_name is null");
            return (Criteria) this;
        }

        public Criteria andSignNameIsNotNull() {
            addCriterion("sign_name is not null");
            return (Criteria) this;
        }

        public Criteria andSignNameEqualTo(String value) {
            addCriterion("sign_name =", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotEqualTo(String value) {
            addCriterion("sign_name <>", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameGreaterThan(String value) {
            addCriterion("sign_name >", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameGreaterThanOrEqualTo(String value) {
            addCriterion("sign_name >=", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameLessThan(String value) {
            addCriterion("sign_name <", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameLessThanOrEqualTo(String value) {
            addCriterion("sign_name <=", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameLike(String value) {
            addCriterion("sign_name like", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotLike(String value) {
            addCriterion("sign_name not like", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameIn(List<String> values) {
            addCriterion("sign_name in", values, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotIn(List<String> values) {
            addCriterion("sign_name not in", values, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameBetween(String value1, String value2) {
            addCriterion("sign_name between", value1, value2, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotBetween(String value1, String value2) {
            addCriterion("sign_name not between", value1, value2, "signName");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdIsNull() {
            addCriterion("register_template_id is null");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdIsNotNull() {
            addCriterion("register_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdEqualTo(String value) {
            addCriterion("register_template_id =", value, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdNotEqualTo(String value) {
            addCriterion("register_template_id <>", value, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdGreaterThan(String value) {
            addCriterion("register_template_id >", value, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("register_template_id >=", value, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdLessThan(String value) {
            addCriterion("register_template_id <", value, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("register_template_id <=", value, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdLike(String value) {
            addCriterion("register_template_id like", value, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdNotLike(String value) {
            addCriterion("register_template_id not like", value, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdIn(List<String> values) {
            addCriterion("register_template_id in", values, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdNotIn(List<String> values) {
            addCriterion("register_template_id not in", values, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdBetween(String value1, String value2) {
            addCriterion("register_template_id between", value1, value2, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andRegisterTemplateIdNotBetween(String value1, String value2) {
            addCriterion("register_template_id not between", value1, value2, "registerTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdIsNull() {
            addCriterion("bind_template_id is null");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdIsNotNull() {
            addCriterion("bind_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdEqualTo(String value) {
            addCriterion("bind_template_id =", value, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdNotEqualTo(String value) {
            addCriterion("bind_template_id <>", value, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdGreaterThan(String value) {
            addCriterion("bind_template_id >", value, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("bind_template_id >=", value, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdLessThan(String value) {
            addCriterion("bind_template_id <", value, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("bind_template_id <=", value, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdLike(String value) {
            addCriterion("bind_template_id like", value, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdNotLike(String value) {
            addCriterion("bind_template_id not like", value, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdIn(List<String> values) {
            addCriterion("bind_template_id in", values, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdNotIn(List<String> values) {
            addCriterion("bind_template_id not in", values, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdBetween(String value1, String value2) {
            addCriterion("bind_template_id between", value1, value2, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andBindTemplateIdNotBetween(String value1, String value2) {
            addCriterion("bind_template_id not between", value1, value2, "bindTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdIsNull() {
            addCriterion("forget_template_id is null");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdIsNotNull() {
            addCriterion("forget_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdEqualTo(String value) {
            addCriterion("forget_template_id =", value, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdNotEqualTo(String value) {
            addCriterion("forget_template_id <>", value, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdGreaterThan(String value) {
            addCriterion("forget_template_id >", value, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("forget_template_id >=", value, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdLessThan(String value) {
            addCriterion("forget_template_id <", value, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("forget_template_id <=", value, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdLike(String value) {
            addCriterion("forget_template_id like", value, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdNotLike(String value) {
            addCriterion("forget_template_id not like", value, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdIn(List<String> values) {
            addCriterion("forget_template_id in", values, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdNotIn(List<String> values) {
            addCriterion("forget_template_id not in", values, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdBetween(String value1, String value2) {
            addCriterion("forget_template_id between", value1, value2, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andForgetTemplateIdNotBetween(String value1, String value2) {
            addCriterion("forget_template_id not between", value1, value2, "forgetTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdIsNull() {
            addCriterion("common_template_id is null");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdIsNotNull() {
            addCriterion("common_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdEqualTo(String value) {
            addCriterion("common_template_id =", value, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdNotEqualTo(String value) {
            addCriterion("common_template_id <>", value, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdGreaterThan(String value) {
            addCriterion("common_template_id >", value, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("common_template_id >=", value, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdLessThan(String value) {
            addCriterion("common_template_id <", value, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("common_template_id <=", value, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdLike(String value) {
            addCriterion("common_template_id like", value, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdNotLike(String value) {
            addCriterion("common_template_id not like", value, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdIn(List<String> values) {
            addCriterion("common_template_id in", values, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdNotIn(List<String> values) {
            addCriterion("common_template_id not in", values, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdBetween(String value1, String value2) {
            addCriterion("common_template_id between", value1, value2, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andCommonTemplateIdNotBetween(String value1, String value2) {
            addCriterion("common_template_id not between", value1, value2, "commonTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdIsNull() {
            addCriterion("password_template_id is null");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdIsNotNull() {
            addCriterion("password_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdEqualTo(String value) {
            addCriterion("password_template_id =", value, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdNotEqualTo(String value) {
            addCriterion("password_template_id <>", value, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdGreaterThan(String value) {
            addCriterion("password_template_id >", value, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("password_template_id >=", value, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdLessThan(String value) {
            addCriterion("password_template_id <", value, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("password_template_id <=", value, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdLike(String value) {
            addCriterion("password_template_id like", value, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdNotLike(String value) {
            addCriterion("password_template_id not like", value, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdIn(List<String> values) {
            addCriterion("password_template_id in", values, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdNotIn(List<String> values) {
            addCriterion("password_template_id not in", values, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdBetween(String value1, String value2) {
            addCriterion("password_template_id between", value1, value2, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andPasswordTemplateIdNotBetween(String value1, String value2) {
            addCriterion("password_template_id not between", value1, value2, "passwordTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdIsNull() {
            addCriterion("delivery_template_id is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdIsNotNull() {
            addCriterion("delivery_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdEqualTo(String value) {
            addCriterion("delivery_template_id =", value, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdNotEqualTo(String value) {
            addCriterion("delivery_template_id <>", value, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdGreaterThan(String value) {
            addCriterion("delivery_template_id >", value, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_template_id >=", value, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdLessThan(String value) {
            addCriterion("delivery_template_id <", value, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("delivery_template_id <=", value, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdLike(String value) {
            addCriterion("delivery_template_id like", value, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdNotLike(String value) {
            addCriterion("delivery_template_id not like", value, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdIn(List<String> values) {
            addCriterion("delivery_template_id in", values, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdNotIn(List<String> values) {
            addCriterion("delivery_template_id not in", values, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdBetween(String value1, String value2) {
            addCriterion("delivery_template_id between", value1, value2, "deliveryTemplateId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTemplateIdNotBetween(String value1, String value2) {
            addCriterion("delivery_template_id not between", value1, value2, "deliveryTemplateId");
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

        public Criteria andSmsTypeIsNull() {
            addCriterion("sms_type is null");
            return (Criteria) this;
        }

        public Criteria andSmsTypeIsNotNull() {
            addCriterion("sms_type is not null");
            return (Criteria) this;
        }

        public Criteria andSmsTypeEqualTo(String value) {
            addCriterion("sms_type =", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeNotEqualTo(String value) {
            addCriterion("sms_type <>", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeGreaterThan(String value) {
            addCriterion("sms_type >", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sms_type >=", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeLessThan(String value) {
            addCriterion("sms_type <", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeLessThanOrEqualTo(String value) {
            addCriterion("sms_type <=", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeLike(String value) {
            addCriterion("sms_type like", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeNotLike(String value) {
            addCriterion("sms_type not like", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeIn(List<String> values) {
            addCriterion("sms_type in", values, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeNotIn(List<String> values) {
            addCriterion("sms_type not in", values, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeBetween(String value1, String value2) {
            addCriterion("sms_type between", value1, value2, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeNotBetween(String value1, String value2) {
            addCriterion("sms_type not between", value1, value2, "smsType");
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