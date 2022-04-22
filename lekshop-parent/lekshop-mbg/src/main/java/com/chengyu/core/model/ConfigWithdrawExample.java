package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigWithdrawExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConfigWithdrawExample() {
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

        public Criteria andWithdrawStatusIsNull() {
            addCriterion("withdraw_status is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusIsNotNull() {
            addCriterion("withdraw_status is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusEqualTo(Integer value) {
            addCriterion("withdraw_status =", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusNotEqualTo(Integer value) {
            addCriterion("withdraw_status <>", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusGreaterThan(Integer value) {
            addCriterion("withdraw_status >", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("withdraw_status >=", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusLessThan(Integer value) {
            addCriterion("withdraw_status <", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusLessThanOrEqualTo(Integer value) {
            addCriterion("withdraw_status <=", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusIn(List<Integer> values) {
            addCriterion("withdraw_status in", values, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusNotIn(List<Integer> values) {
            addCriterion("withdraw_status not in", values, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_status between", value1, value2, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_status not between", value1, value2, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceIsNull() {
            addCriterion("mission_to_balance is null");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceIsNotNull() {
            addCriterion("mission_to_balance is not null");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceEqualTo(Integer value) {
            addCriterion("mission_to_balance =", value, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceNotEqualTo(Integer value) {
            addCriterion("mission_to_balance <>", value, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceGreaterThan(Integer value) {
            addCriterion("mission_to_balance >", value, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("mission_to_balance >=", value, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceLessThan(Integer value) {
            addCriterion("mission_to_balance <", value, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceLessThanOrEqualTo(Integer value) {
            addCriterion("mission_to_balance <=", value, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceIn(List<Integer> values) {
            addCriterion("mission_to_balance in", values, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceNotIn(List<Integer> values) {
            addCriterion("mission_to_balance not in", values, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceBetween(Integer value1, Integer value2) {
            addCriterion("mission_to_balance between", value1, value2, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andMissionToBalanceNotBetween(Integer value1, Integer value2) {
            addCriterion("mission_to_balance not between", value1, value2, "missionToBalance");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeIsNull() {
            addCriterion("withdraw_type is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeIsNotNull() {
            addCriterion("withdraw_type is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeEqualTo(Integer value) {
            addCriterion("withdraw_type =", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeNotEqualTo(Integer value) {
            addCriterion("withdraw_type <>", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeGreaterThan(Integer value) {
            addCriterion("withdraw_type >", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("withdraw_type >=", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeLessThan(Integer value) {
            addCriterion("withdraw_type <", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeLessThanOrEqualTo(Integer value) {
            addCriterion("withdraw_type <=", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeIn(List<Integer> values) {
            addCriterion("withdraw_type in", values, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeNotIn(List<Integer> values) {
            addCriterion("withdraw_type not in", values, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_type between", value1, value2, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_type not between", value1, value2, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodIsNull() {
            addCriterion("withdraw_method is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodIsNotNull() {
            addCriterion("withdraw_method is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodEqualTo(String value) {
            addCriterion("withdraw_method =", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodNotEqualTo(String value) {
            addCriterion("withdraw_method <>", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodGreaterThan(String value) {
            addCriterion("withdraw_method >", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodGreaterThanOrEqualTo(String value) {
            addCriterion("withdraw_method >=", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodLessThan(String value) {
            addCriterion("withdraw_method <", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodLessThanOrEqualTo(String value) {
            addCriterion("withdraw_method <=", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodLike(String value) {
            addCriterion("withdraw_method like", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodNotLike(String value) {
            addCriterion("withdraw_method not like", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodIn(List<String> values) {
            addCriterion("withdraw_method in", values, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodNotIn(List<String> values) {
            addCriterion("withdraw_method not in", values, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodBetween(String value1, String value2) {
            addCriterion("withdraw_method between", value1, value2, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodNotBetween(String value1, String value2) {
            addCriterion("withdraw_method not between", value1, value2, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameIsNull() {
            addCriterion("need_realname is null");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameIsNotNull() {
            addCriterion("need_realname is not null");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameEqualTo(Integer value) {
            addCriterion("need_realname =", value, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameNotEqualTo(Integer value) {
            addCriterion("need_realname <>", value, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameGreaterThan(Integer value) {
            addCriterion("need_realname >", value, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_realname >=", value, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameLessThan(Integer value) {
            addCriterion("need_realname <", value, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameLessThanOrEqualTo(Integer value) {
            addCriterion("need_realname <=", value, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameIn(List<Integer> values) {
            addCriterion("need_realname in", values, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameNotIn(List<Integer> values) {
            addCriterion("need_realname not in", values, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameBetween(Integer value1, Integer value2) {
            addCriterion("need_realname between", value1, value2, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedRealnameNotBetween(Integer value1, Integer value2) {
            addCriterion("need_realname not between", value1, value2, "needRealname");
            return (Criteria) this;
        }

        public Criteria andNeedBankIsNull() {
            addCriterion("need_bank is null");
            return (Criteria) this;
        }

        public Criteria andNeedBankIsNotNull() {
            addCriterion("need_bank is not null");
            return (Criteria) this;
        }

        public Criteria andNeedBankEqualTo(Integer value) {
            addCriterion("need_bank =", value, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedBankNotEqualTo(Integer value) {
            addCriterion("need_bank <>", value, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedBankGreaterThan(Integer value) {
            addCriterion("need_bank >", value, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedBankGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_bank >=", value, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedBankLessThan(Integer value) {
            addCriterion("need_bank <", value, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedBankLessThanOrEqualTo(Integer value) {
            addCriterion("need_bank <=", value, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedBankIn(List<Integer> values) {
            addCriterion("need_bank in", values, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedBankNotIn(List<Integer> values) {
            addCriterion("need_bank not in", values, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedBankBetween(Integer value1, Integer value2) {
            addCriterion("need_bank between", value1, value2, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedBankNotBetween(Integer value1, Integer value2) {
            addCriterion("need_bank not between", value1, value2, "needBank");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeIsNull() {
            addCriterion("need_phone_code is null");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeIsNotNull() {
            addCriterion("need_phone_code is not null");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeEqualTo(Integer value) {
            addCriterion("need_phone_code =", value, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeNotEqualTo(Integer value) {
            addCriterion("need_phone_code <>", value, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeGreaterThan(Integer value) {
            addCriterion("need_phone_code >", value, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_phone_code >=", value, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeLessThan(Integer value) {
            addCriterion("need_phone_code <", value, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeLessThanOrEqualTo(Integer value) {
            addCriterion("need_phone_code <=", value, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeIn(List<Integer> values) {
            addCriterion("need_phone_code in", values, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeNotIn(List<Integer> values) {
            addCriterion("need_phone_code not in", values, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeBetween(Integer value1, Integer value2) {
            addCriterion("need_phone_code between", value1, value2, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andNeedPhoneCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("need_phone_code not between", value1, value2, "needPhoneCode");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountIsNull() {
            addCriterion("min_balance_withdraw_amount is null");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountIsNotNull() {
            addCriterion("min_balance_withdraw_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountEqualTo(BigDecimal value) {
            addCriterion("min_balance_withdraw_amount =", value, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountNotEqualTo(BigDecimal value) {
            addCriterion("min_balance_withdraw_amount <>", value, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountGreaterThan(BigDecimal value) {
            addCriterion("min_balance_withdraw_amount >", value, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_balance_withdraw_amount >=", value, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountLessThan(BigDecimal value) {
            addCriterion("min_balance_withdraw_amount <", value, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_balance_withdraw_amount <=", value, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountIn(List<BigDecimal> values) {
            addCriterion("min_balance_withdraw_amount in", values, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountNotIn(List<BigDecimal> values) {
            addCriterion("min_balance_withdraw_amount not in", values, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_balance_withdraw_amount between", value1, value2, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinBalanceWithdrawAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_balance_withdraw_amount not between", value1, value2, "minBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountIsNull() {
            addCriterion("max_balance_withdraw_amount is null");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountIsNotNull() {
            addCriterion("max_balance_withdraw_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountEqualTo(BigDecimal value) {
            addCriterion("max_balance_withdraw_amount =", value, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountNotEqualTo(BigDecimal value) {
            addCriterion("max_balance_withdraw_amount <>", value, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountGreaterThan(BigDecimal value) {
            addCriterion("max_balance_withdraw_amount >", value, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max_balance_withdraw_amount >=", value, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountLessThan(BigDecimal value) {
            addCriterion("max_balance_withdraw_amount <", value, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max_balance_withdraw_amount <=", value, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountIn(List<BigDecimal> values) {
            addCriterion("max_balance_withdraw_amount in", values, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountNotIn(List<BigDecimal> values) {
            addCriterion("max_balance_withdraw_amount not in", values, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_balance_withdraw_amount between", value1, value2, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxBalanceWithdrawAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_balance_withdraw_amount not between", value1, value2, "maxBalanceWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountIsNull() {
            addCriterion("min_mission_withdraw_amount is null");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountIsNotNull() {
            addCriterion("min_mission_withdraw_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountEqualTo(BigDecimal value) {
            addCriterion("min_mission_withdraw_amount =", value, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountNotEqualTo(BigDecimal value) {
            addCriterion("min_mission_withdraw_amount <>", value, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountGreaterThan(BigDecimal value) {
            addCriterion("min_mission_withdraw_amount >", value, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_mission_withdraw_amount >=", value, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountLessThan(BigDecimal value) {
            addCriterion("min_mission_withdraw_amount <", value, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_mission_withdraw_amount <=", value, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountIn(List<BigDecimal> values) {
            addCriterion("min_mission_withdraw_amount in", values, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountNotIn(List<BigDecimal> values) {
            addCriterion("min_mission_withdraw_amount not in", values, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_mission_withdraw_amount between", value1, value2, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMinMissionWithdrawAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_mission_withdraw_amount not between", value1, value2, "minMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountIsNull() {
            addCriterion("max_mission_withdraw_amount is null");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountIsNotNull() {
            addCriterion("max_mission_withdraw_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountEqualTo(BigDecimal value) {
            addCriterion("max_mission_withdraw_amount =", value, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountNotEqualTo(BigDecimal value) {
            addCriterion("max_mission_withdraw_amount <>", value, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountGreaterThan(BigDecimal value) {
            addCriterion("max_mission_withdraw_amount >", value, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max_mission_withdraw_amount >=", value, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountLessThan(BigDecimal value) {
            addCriterion("max_mission_withdraw_amount <", value, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max_mission_withdraw_amount <=", value, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountIn(List<BigDecimal> values) {
            addCriterion("max_mission_withdraw_amount in", values, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountNotIn(List<BigDecimal> values) {
            addCriterion("max_mission_withdraw_amount not in", values, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_mission_withdraw_amount between", value1, value2, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andMaxMissionWithdrawAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_mission_withdraw_amount not between", value1, value2, "maxMissionWithdrawAmount");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumIsNull() {
            addCriterion("withdraw_day_num is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumIsNotNull() {
            addCriterion("withdraw_day_num is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumEqualTo(Integer value) {
            addCriterion("withdraw_day_num =", value, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumNotEqualTo(Integer value) {
            addCriterion("withdraw_day_num <>", value, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumGreaterThan(Integer value) {
            addCriterion("withdraw_day_num >", value, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("withdraw_day_num >=", value, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumLessThan(Integer value) {
            addCriterion("withdraw_day_num <", value, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumLessThanOrEqualTo(Integer value) {
            addCriterion("withdraw_day_num <=", value, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumIn(List<Integer> values) {
            addCriterion("withdraw_day_num in", values, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumNotIn(List<Integer> values) {
            addCriterion("withdraw_day_num not in", values, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_day_num between", value1, value2, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andWithdrawDayNumNotBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_day_num not between", value1, value2, "withdrawDayNum");
            return (Criteria) this;
        }

        public Criteria andZfbAccountIsNull() {
            addCriterion("zfb_account is null");
            return (Criteria) this;
        }

        public Criteria andZfbAccountIsNotNull() {
            addCriterion("zfb_account is not null");
            return (Criteria) this;
        }

        public Criteria andZfbAccountEqualTo(String value) {
            addCriterion("zfb_account =", value, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountNotEqualTo(String value) {
            addCriterion("zfb_account <>", value, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountGreaterThan(String value) {
            addCriterion("zfb_account >", value, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountGreaterThanOrEqualTo(String value) {
            addCriterion("zfb_account >=", value, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountLessThan(String value) {
            addCriterion("zfb_account <", value, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountLessThanOrEqualTo(String value) {
            addCriterion("zfb_account <=", value, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountLike(String value) {
            addCriterion("zfb_account like", value, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountNotLike(String value) {
            addCriterion("zfb_account not like", value, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountIn(List<String> values) {
            addCriterion("zfb_account in", values, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountNotIn(List<String> values) {
            addCriterion("zfb_account not in", values, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountBetween(String value1, String value2) {
            addCriterion("zfb_account between", value1, value2, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbAccountNotBetween(String value1, String value2) {
            addCriterion("zfb_account not between", value1, value2, "zfbAccount");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameIsNull() {
            addCriterion("zfb_realname is null");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameIsNotNull() {
            addCriterion("zfb_realname is not null");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameEqualTo(String value) {
            addCriterion("zfb_realname =", value, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameNotEqualTo(String value) {
            addCriterion("zfb_realname <>", value, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameGreaterThan(String value) {
            addCriterion("zfb_realname >", value, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("zfb_realname >=", value, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameLessThan(String value) {
            addCriterion("zfb_realname <", value, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameLessThanOrEqualTo(String value) {
            addCriterion("zfb_realname <=", value, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameLike(String value) {
            addCriterion("zfb_realname like", value, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameNotLike(String value) {
            addCriterion("zfb_realname not like", value, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameIn(List<String> values) {
            addCriterion("zfb_realname in", values, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameNotIn(List<String> values) {
            addCriterion("zfb_realname not in", values, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameBetween(String value1, String value2) {
            addCriterion("zfb_realname between", value1, value2, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRealnameNotBetween(String value1, String value2) {
            addCriterion("zfb_realname not between", value1, value2, "zfbRealname");
            return (Criteria) this;
        }

        public Criteria andZfbRateIsNull() {
            addCriterion("zfb_rate is null");
            return (Criteria) this;
        }

        public Criteria andZfbRateIsNotNull() {
            addCriterion("zfb_rate is not null");
            return (Criteria) this;
        }

        public Criteria andZfbRateEqualTo(BigDecimal value) {
            addCriterion("zfb_rate =", value, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbRateNotEqualTo(BigDecimal value) {
            addCriterion("zfb_rate <>", value, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbRateGreaterThan(BigDecimal value) {
            addCriterion("zfb_rate >", value, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("zfb_rate >=", value, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbRateLessThan(BigDecimal value) {
            addCriterion("zfb_rate <", value, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("zfb_rate <=", value, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbRateIn(List<BigDecimal> values) {
            addCriterion("zfb_rate in", values, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbRateNotIn(List<BigDecimal> values) {
            addCriterion("zfb_rate not in", values, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zfb_rate between", value1, value2, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zfb_rate not between", value1, value2, "zfbRate");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeIsNull() {
            addCriterion("zfb_min_fee is null");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeIsNotNull() {
            addCriterion("zfb_min_fee is not null");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeEqualTo(BigDecimal value) {
            addCriterion("zfb_min_fee =", value, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeNotEqualTo(BigDecimal value) {
            addCriterion("zfb_min_fee <>", value, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeGreaterThan(BigDecimal value) {
            addCriterion("zfb_min_fee >", value, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("zfb_min_fee >=", value, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeLessThan(BigDecimal value) {
            addCriterion("zfb_min_fee <", value, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("zfb_min_fee <=", value, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeIn(List<BigDecimal> values) {
            addCriterion("zfb_min_fee in", values, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeNotIn(List<BigDecimal> values) {
            addCriterion("zfb_min_fee not in", values, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zfb_min_fee between", value1, value2, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andZfbMinFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zfb_min_fee not between", value1, value2, "zfbMinFee");
            return (Criteria) this;
        }

        public Criteria andWxRateIsNull() {
            addCriterion("wx_rate is null");
            return (Criteria) this;
        }

        public Criteria andWxRateIsNotNull() {
            addCriterion("wx_rate is not null");
            return (Criteria) this;
        }

        public Criteria andWxRateEqualTo(BigDecimal value) {
            addCriterion("wx_rate =", value, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxRateNotEqualTo(BigDecimal value) {
            addCriterion("wx_rate <>", value, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxRateGreaterThan(BigDecimal value) {
            addCriterion("wx_rate >", value, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wx_rate >=", value, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxRateLessThan(BigDecimal value) {
            addCriterion("wx_rate <", value, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wx_rate <=", value, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxRateIn(List<BigDecimal> values) {
            addCriterion("wx_rate in", values, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxRateNotIn(List<BigDecimal> values) {
            addCriterion("wx_rate not in", values, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wx_rate between", value1, value2, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wx_rate not between", value1, value2, "wxRate");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeIsNull() {
            addCriterion("wx_min_fee is null");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeIsNotNull() {
            addCriterion("wx_min_fee is not null");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeEqualTo(BigDecimal value) {
            addCriterion("wx_min_fee =", value, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeNotEqualTo(BigDecimal value) {
            addCriterion("wx_min_fee <>", value, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeGreaterThan(BigDecimal value) {
            addCriterion("wx_min_fee >", value, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wx_min_fee >=", value, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeLessThan(BigDecimal value) {
            addCriterion("wx_min_fee <", value, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wx_min_fee <=", value, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeIn(List<BigDecimal> values) {
            addCriterion("wx_min_fee in", values, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeNotIn(List<BigDecimal> values) {
            addCriterion("wx_min_fee not in", values, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wx_min_fee between", value1, value2, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andWxMinFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wx_min_fee not between", value1, value2, "wxMinFee");
            return (Criteria) this;
        }

        public Criteria andBankRateIsNull() {
            addCriterion("bank_rate is null");
            return (Criteria) this;
        }

        public Criteria andBankRateIsNotNull() {
            addCriterion("bank_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBankRateEqualTo(BigDecimal value) {
            addCriterion("bank_rate =", value, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankRateNotEqualTo(BigDecimal value) {
            addCriterion("bank_rate <>", value, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankRateGreaterThan(BigDecimal value) {
            addCriterion("bank_rate >", value, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bank_rate >=", value, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankRateLessThan(BigDecimal value) {
            addCriterion("bank_rate <", value, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bank_rate <=", value, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankRateIn(List<BigDecimal> values) {
            addCriterion("bank_rate in", values, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankRateNotIn(List<BigDecimal> values) {
            addCriterion("bank_rate not in", values, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bank_rate between", value1, value2, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bank_rate not between", value1, value2, "bankRate");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeIsNull() {
            addCriterion("bank_min_fee is null");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeIsNotNull() {
            addCriterion("bank_min_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeEqualTo(BigDecimal value) {
            addCriterion("bank_min_fee =", value, "bankMinFee");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeNotEqualTo(BigDecimal value) {
            addCriterion("bank_min_fee <>", value, "bankMinFee");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeGreaterThan(BigDecimal value) {
            addCriterion("bank_min_fee >", value, "bankMinFee");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bank_min_fee >=", value, "bankMinFee");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeLessThan(BigDecimal value) {
            addCriterion("bank_min_fee <", value, "bankMinFee");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bank_min_fee <=", value, "bankMinFee");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeIn(List<BigDecimal> values) {
            addCriterion("bank_min_fee in", values, "bankMinFee");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeNotIn(List<BigDecimal> values) {
            addCriterion("bank_min_fee not in", values, "bankMinFee");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bank_min_fee between", value1, value2, "bankMinFee");
            return (Criteria) this;
        }

        public Criteria andBankMinFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bank_min_fee not between", value1, value2, "bankMinFee");
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