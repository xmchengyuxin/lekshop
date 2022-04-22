package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigRealnameExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConfigRealnameExample() {
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

        public Criteria andRealnameNeedPhoneCodeIsNull() {
            addCriterion("realname_need_phone_code is null");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeIsNotNull() {
            addCriterion("realname_need_phone_code is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeEqualTo(Integer value) {
            addCriterion("realname_need_phone_code =", value, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeNotEqualTo(Integer value) {
            addCriterion("realname_need_phone_code <>", value, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeGreaterThan(Integer value) {
            addCriterion("realname_need_phone_code >", value, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("realname_need_phone_code >=", value, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeLessThan(Integer value) {
            addCriterion("realname_need_phone_code <", value, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeLessThanOrEqualTo(Integer value) {
            addCriterion("realname_need_phone_code <=", value, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeIn(List<Integer> values) {
            addCriterion("realname_need_phone_code in", values, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeNotIn(List<Integer> values) {
            addCriterion("realname_need_phone_code not in", values, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeBetween(Integer value1, Integer value2) {
            addCriterion("realname_need_phone_code between", value1, value2, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andRealnameNeedPhoneCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("realname_need_phone_code not between", value1, value2, "realnameNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgIsNull() {
            addCriterion("need_hand_img is null");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgIsNotNull() {
            addCriterion("need_hand_img is not null");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgEqualTo(Integer value) {
            addCriterion("need_hand_img =", value, "needHandImg");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgNotEqualTo(Integer value) {
            addCriterion("need_hand_img <>", value, "needHandImg");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgGreaterThan(Integer value) {
            addCriterion("need_hand_img >", value, "needHandImg");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_hand_img >=", value, "needHandImg");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgLessThan(Integer value) {
            addCriterion("need_hand_img <", value, "needHandImg");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgLessThanOrEqualTo(Integer value) {
            addCriterion("need_hand_img <=", value, "needHandImg");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgIn(List<Integer> values) {
            addCriterion("need_hand_img in", values, "needHandImg");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgNotIn(List<Integer> values) {
            addCriterion("need_hand_img not in", values, "needHandImg");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgBetween(Integer value1, Integer value2) {
            addCriterion("need_hand_img between", value1, value2, "needHandImg");
            return (Criteria) this;
        }

        public Criteria andNeedHandImgNotBetween(Integer value1, Integer value2) {
            addCriterion("need_hand_img not between", value1, value2, "needHandImg");
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

        public Criteria andBankNeedPhoneCodeIsNull() {
            addCriterion("bank_need_phone_code is null");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeIsNotNull() {
            addCriterion("bank_need_phone_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeEqualTo(Integer value) {
            addCriterion("bank_need_phone_code =", value, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeNotEqualTo(Integer value) {
            addCriterion("bank_need_phone_code <>", value, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeGreaterThan(Integer value) {
            addCriterion("bank_need_phone_code >", value, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_need_phone_code >=", value, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeLessThan(Integer value) {
            addCriterion("bank_need_phone_code <", value, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeLessThanOrEqualTo(Integer value) {
            addCriterion("bank_need_phone_code <=", value, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeIn(List<Integer> values) {
            addCriterion("bank_need_phone_code in", values, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeNotIn(List<Integer> values) {
            addCriterion("bank_need_phone_code not in", values, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeBetween(Integer value1, Integer value2) {
            addCriterion("bank_need_phone_code between", value1, value2, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andBankNeedPhoneCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_need_phone_code not between", value1, value2, "bankNeedPhoneCode");
            return (Criteria) this;
        }

        public Criteria andSynRealnameIsNull() {
            addCriterion("syn_realname is null");
            return (Criteria) this;
        }

        public Criteria andSynRealnameIsNotNull() {
            addCriterion("syn_realname is not null");
            return (Criteria) this;
        }

        public Criteria andSynRealnameEqualTo(Integer value) {
            addCriterion("syn_realname =", value, "synRealname");
            return (Criteria) this;
        }

        public Criteria andSynRealnameNotEqualTo(Integer value) {
            addCriterion("syn_realname <>", value, "synRealname");
            return (Criteria) this;
        }

        public Criteria andSynRealnameGreaterThan(Integer value) {
            addCriterion("syn_realname >", value, "synRealname");
            return (Criteria) this;
        }

        public Criteria andSynRealnameGreaterThanOrEqualTo(Integer value) {
            addCriterion("syn_realname >=", value, "synRealname");
            return (Criteria) this;
        }

        public Criteria andSynRealnameLessThan(Integer value) {
            addCriterion("syn_realname <", value, "synRealname");
            return (Criteria) this;
        }

        public Criteria andSynRealnameLessThanOrEqualTo(Integer value) {
            addCriterion("syn_realname <=", value, "synRealname");
            return (Criteria) this;
        }

        public Criteria andSynRealnameIn(List<Integer> values) {
            addCriterion("syn_realname in", values, "synRealname");
            return (Criteria) this;
        }

        public Criteria andSynRealnameNotIn(List<Integer> values) {
            addCriterion("syn_realname not in", values, "synRealname");
            return (Criteria) this;
        }

        public Criteria andSynRealnameBetween(Integer value1, Integer value2) {
            addCriterion("syn_realname between", value1, value2, "synRealname");
            return (Criteria) this;
        }

        public Criteria andSynRealnameNotBetween(Integer value1, Integer value2) {
            addCriterion("syn_realname not between", value1, value2, "synRealname");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusIsNull() {
            addCriterion("third_realname_status is null");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusIsNotNull() {
            addCriterion("third_realname_status is not null");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusEqualTo(Integer value) {
            addCriterion("third_realname_status =", value, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusNotEqualTo(Integer value) {
            addCriterion("third_realname_status <>", value, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusGreaterThan(Integer value) {
            addCriterion("third_realname_status >", value, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("third_realname_status >=", value, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusLessThan(Integer value) {
            addCriterion("third_realname_status <", value, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusLessThanOrEqualTo(Integer value) {
            addCriterion("third_realname_status <=", value, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusIn(List<Integer> values) {
            addCriterion("third_realname_status in", values, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusNotIn(List<Integer> values) {
            addCriterion("third_realname_status not in", values, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusBetween(Integer value1, Integer value2) {
            addCriterion("third_realname_status between", value1, value2, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdRealnameStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("third_realname_status not between", value1, value2, "thirdRealnameStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusIsNull() {
            addCriterion("third_bank_status is null");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusIsNotNull() {
            addCriterion("third_bank_status is not null");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusEqualTo(Integer value) {
            addCriterion("third_bank_status =", value, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusNotEqualTo(Integer value) {
            addCriterion("third_bank_status <>", value, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusGreaterThan(Integer value) {
            addCriterion("third_bank_status >", value, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("third_bank_status >=", value, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusLessThan(Integer value) {
            addCriterion("third_bank_status <", value, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusLessThanOrEqualTo(Integer value) {
            addCriterion("third_bank_status <=", value, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusIn(List<Integer> values) {
            addCriterion("third_bank_status in", values, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusNotIn(List<Integer> values) {
            addCriterion("third_bank_status not in", values, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusBetween(Integer value1, Integer value2) {
            addCriterion("third_bank_status between", value1, value2, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andThirdBankStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("third_bank_status not between", value1, value2, "thirdBankStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusIsNull() {
            addCriterion("bank_phone_status is null");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusIsNotNull() {
            addCriterion("bank_phone_status is not null");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusEqualTo(Integer value) {
            addCriterion("bank_phone_status =", value, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusNotEqualTo(Integer value) {
            addCriterion("bank_phone_status <>", value, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusGreaterThan(Integer value) {
            addCriterion("bank_phone_status >", value, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_phone_status >=", value, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusLessThan(Integer value) {
            addCriterion("bank_phone_status <", value, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusLessThanOrEqualTo(Integer value) {
            addCriterion("bank_phone_status <=", value, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusIn(List<Integer> values) {
            addCriterion("bank_phone_status in", values, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusNotIn(List<Integer> values) {
            addCriterion("bank_phone_status not in", values, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusBetween(Integer value1, Integer value2) {
            addCriterion("bank_phone_status between", value1, value2, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andBankPhoneStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_phone_status not between", value1, value2, "bankPhoneStatus");
            return (Criteria) this;
        }

        public Criteria andChargeNumIsNull() {
            addCriterion("charge_num is null");
            return (Criteria) this;
        }

        public Criteria andChargeNumIsNotNull() {
            addCriterion("charge_num is not null");
            return (Criteria) this;
        }

        public Criteria andChargeNumEqualTo(Integer value) {
            addCriterion("charge_num =", value, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeNumNotEqualTo(Integer value) {
            addCriterion("charge_num <>", value, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeNumGreaterThan(Integer value) {
            addCriterion("charge_num >", value, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("charge_num >=", value, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeNumLessThan(Integer value) {
            addCriterion("charge_num <", value, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeNumLessThanOrEqualTo(Integer value) {
            addCriterion("charge_num <=", value, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeNumIn(List<Integer> values) {
            addCriterion("charge_num in", values, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeNumNotIn(List<Integer> values) {
            addCriterion("charge_num not in", values, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeNumBetween(Integer value1, Integer value2) {
            addCriterion("charge_num between", value1, value2, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeNumNotBetween(Integer value1, Integer value2) {
            addCriterion("charge_num not between", value1, value2, "chargeNum");
            return (Criteria) this;
        }

        public Criteria andChargeFeeIsNull() {
            addCriterion("charge_fee is null");
            return (Criteria) this;
        }

        public Criteria andChargeFeeIsNotNull() {
            addCriterion("charge_fee is not null");
            return (Criteria) this;
        }

        public Criteria andChargeFeeEqualTo(BigDecimal value) {
            addCriterion("charge_fee =", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeNotEqualTo(BigDecimal value) {
            addCriterion("charge_fee <>", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeGreaterThan(BigDecimal value) {
            addCriterion("charge_fee >", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("charge_fee >=", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeLessThan(BigDecimal value) {
            addCriterion("charge_fee <", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("charge_fee <=", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeIn(List<BigDecimal> values) {
            addCriterion("charge_fee in", values, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeNotIn(List<BigDecimal> values) {
            addCriterion("charge_fee not in", values, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charge_fee between", value1, value2, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charge_fee not between", value1, value2, "chargeFee");
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

        public Criteria andWeixinNeedIsNull() {
            addCriterion("weixin_need is null");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedIsNotNull() {
            addCriterion("weixin_need is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedEqualTo(Integer value) {
            addCriterion("weixin_need =", value, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedNotEqualTo(Integer value) {
            addCriterion("weixin_need <>", value, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedGreaterThan(Integer value) {
            addCriterion("weixin_need >", value, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedGreaterThanOrEqualTo(Integer value) {
            addCriterion("weixin_need >=", value, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedLessThan(Integer value) {
            addCriterion("weixin_need <", value, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedLessThanOrEqualTo(Integer value) {
            addCriterion("weixin_need <=", value, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedIn(List<Integer> values) {
            addCriterion("weixin_need in", values, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedNotIn(List<Integer> values) {
            addCriterion("weixin_need not in", values, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedBetween(Integer value1, Integer value2) {
            addCriterion("weixin_need between", value1, value2, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinNeedNotBetween(Integer value1, Integer value2) {
            addCriterion("weixin_need not between", value1, value2, "weixinNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedIsNull() {
            addCriterion("zfb_need is null");
            return (Criteria) this;
        }

        public Criteria andZfbNeedIsNotNull() {
            addCriterion("zfb_need is not null");
            return (Criteria) this;
        }

        public Criteria andZfbNeedEqualTo(Integer value) {
            addCriterion("zfb_need =", value, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedNotEqualTo(Integer value) {
            addCriterion("zfb_need <>", value, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedGreaterThan(Integer value) {
            addCriterion("zfb_need >", value, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedGreaterThanOrEqualTo(Integer value) {
            addCriterion("zfb_need >=", value, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedLessThan(Integer value) {
            addCriterion("zfb_need <", value, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedLessThanOrEqualTo(Integer value) {
            addCriterion("zfb_need <=", value, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedIn(List<Integer> values) {
            addCriterion("zfb_need in", values, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedNotIn(List<Integer> values) {
            addCriterion("zfb_need not in", values, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedBetween(Integer value1, Integer value2) {
            addCriterion("zfb_need between", value1, value2, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andZfbNeedNotBetween(Integer value1, Integer value2) {
            addCriterion("zfb_need not between", value1, value2, "zfbNeed");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameIsNull() {
            addCriterion("realname_bank_same is null");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameIsNotNull() {
            addCriterion("realname_bank_same is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameEqualTo(Integer value) {
            addCriterion("realname_bank_same =", value, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameNotEqualTo(Integer value) {
            addCriterion("realname_bank_same <>", value, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameGreaterThan(Integer value) {
            addCriterion("realname_bank_same >", value, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameGreaterThanOrEqualTo(Integer value) {
            addCriterion("realname_bank_same >=", value, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameLessThan(Integer value) {
            addCriterion("realname_bank_same <", value, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameLessThanOrEqualTo(Integer value) {
            addCriterion("realname_bank_same <=", value, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameIn(List<Integer> values) {
            addCriterion("realname_bank_same in", values, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameNotIn(List<Integer> values) {
            addCriterion("realname_bank_same not in", values, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameBetween(Integer value1, Integer value2) {
            addCriterion("realname_bank_same between", value1, value2, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andRealnameBankSameNotBetween(Integer value1, Integer value2) {
            addCriterion("realname_bank_same not between", value1, value2, "realnameBankSame");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedIsNull() {
            addCriterion("weixin_img_need is null");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedIsNotNull() {
            addCriterion("weixin_img_need is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedEqualTo(Integer value) {
            addCriterion("weixin_img_need =", value, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedNotEqualTo(Integer value) {
            addCriterion("weixin_img_need <>", value, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedGreaterThan(Integer value) {
            addCriterion("weixin_img_need >", value, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedGreaterThanOrEqualTo(Integer value) {
            addCriterion("weixin_img_need >=", value, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedLessThan(Integer value) {
            addCriterion("weixin_img_need <", value, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedLessThanOrEqualTo(Integer value) {
            addCriterion("weixin_img_need <=", value, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedIn(List<Integer> values) {
            addCriterion("weixin_img_need in", values, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedNotIn(List<Integer> values) {
            addCriterion("weixin_img_need not in", values, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedBetween(Integer value1, Integer value2) {
            addCriterion("weixin_img_need between", value1, value2, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andWeixinImgNeedNotBetween(Integer value1, Integer value2) {
            addCriterion("weixin_img_need not between", value1, value2, "weixinImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedIsNull() {
            addCriterion("zfb_img_need is null");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedIsNotNull() {
            addCriterion("zfb_img_need is not null");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedEqualTo(Integer value) {
            addCriterion("zfb_img_need =", value, "zfbImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedNotEqualTo(Integer value) {
            addCriterion("zfb_img_need <>", value, "zfbImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedGreaterThan(Integer value) {
            addCriterion("zfb_img_need >", value, "zfbImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedGreaterThanOrEqualTo(Integer value) {
            addCriterion("zfb_img_need >=", value, "zfbImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedLessThan(Integer value) {
            addCriterion("zfb_img_need <", value, "zfbImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedLessThanOrEqualTo(Integer value) {
            addCriterion("zfb_img_need <=", value, "zfbImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedIn(List<Integer> values) {
            addCriterion("zfb_img_need in", values, "zfbImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedNotIn(List<Integer> values) {
            addCriterion("zfb_img_need not in", values, "zfbImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedBetween(Integer value1, Integer value2) {
            addCriterion("zfb_img_need between", value1, value2, "zfbImgNeed");
            return (Criteria) this;
        }

        public Criteria andZfbImgNeedNotBetween(Integer value1, Integer value2) {
            addCriterion("zfb_img_need not between", value1, value2, "zfbImgNeed");
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